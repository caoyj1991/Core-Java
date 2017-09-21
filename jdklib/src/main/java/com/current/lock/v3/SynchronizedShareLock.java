package com.current.lock.v3;

import com.current.lock.Lock;
import com.current.lock.Node;
import com.current.lock.Node.Type;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Author Mr.Pro
 * Date   9/10/17 = 6:10 PM
 */
public class SynchronizedShareLock implements Lock {

    private Logger logger = Logger.getLogger(SynchronizedShareLock.class.getName());

    private volatile Type nodeType = null;
    private AtomicInteger count;
    private Node headNode, tailNode;
    private Byte[] obj;

    public SynchronizedShareLock(){
        obj = new Byte[0];
        headNode = new Node();
        tailNode = new Node();
        headNode.setNextNode(tailNode);
        count = new AtomicInteger(0);
    }

    @Override
    public void lock() throws InterruptedException {
        writeLock();
    }

    @Override
    public void unlock() {
        synchronized (obj){
            acquire(-1);
            if(count.get() == 0){
                nodeType = null;
            }
            obj.notifyAll();
        }
    }

    public void readLock() throws InterruptedException {
        Node.Type type = Node.Type.SHARE;
        synchronized (obj){
            tryLock(type);
        }
    }

    public void writeLock() throws InterruptedException{
        Node.Type type = Node.Type.SINGLE;
        synchronized (obj){
            tryLock(type);
        }
    }

    public void tryLock(Type type) throws InterruptedException{
        addWaiter(type);
        for (;;){
            try {
                Node node = getFirstNode();
                if(!node.getThread().isAlive()){
                    logger.warning(node.getThread().getName()+" dead");
                    moveToNextNode(node);
                    continue;
                }
                if(Thread.currentThread().equals(node.getThread())){
                    if(nodeType == null) {
                        nodeType = node.getType();
                        moveToNextNode(node);
                        acquire(1);
                        break;
                    }else if(isShare()
                            && Node.Type.SHARE.equals(node.getType())){
                        moveToNextNode(node);
                        acquire(1);
                        break;
                    }
                }
                obj.wait();
            }catch (InterruptedException exception){
                logger.warning(Thread.currentThread().getName()+" has been interrupted");
                acquire(1);
                throw new InterruptedException(Thread.currentThread().getName()+" has been interrupted");
            }
        }
    }

    private void addWaiter(Node.Type type){
        tailNode.setThread(Thread.currentThread());
        tailNode.setType(type);
        Node newTailNode = new Node();
        tailNode.setNextNode(newTailNode);
        tailNode = newTailNode;
    }

    private void moveToNextNode(Node node){
        headNode.setNextNode(node.getNextNode());
    }

    private Node getFirstNode(){
        return headNode.getNextNode();
    }

    private boolean isShare(){
        return Node.Type.SHARE.equals(nodeType);
    }

    private void acquire(int i){
        for (;;){
            int c = count.get();
            if(count.compareAndSet(c, c+i)){
                break;
            }
        }
    }
}

