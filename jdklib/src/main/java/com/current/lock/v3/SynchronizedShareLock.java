package com.current.lock.v3;

import com.current.lock.Lock;
import com.current.lock.Node;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Author Mr.Pro
 * Date   9/10/17 = 6:10 PM
 */
public class SynchronizedShareLock implements Lock {

    private Logger logger = Logger.getLogger(SynchronizedShareLock.class.getName());

    private volatile Node.Type nodeType = null;
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

    public void readLock() throws InterruptedException {
        Node.Type type = Node.Type.SHARE;
        synchronized (obj){
            addWaiter(type);
            lock();
        }
    }

    public void writeLock() throws InterruptedException{
        Node.Type type = Node.Type.SINGLE;
        synchronized (obj){
            addWaiter(type);
            lock();
        }
    }

    @Override
    public void lock() throws InterruptedException {
        while (true){
            if(count.get() != 0 && !isShare()){
                obj.wait();
            }
            Node node = getFirstNode();
            if(!Thread.currentThread().equals(node.getCurrentThread())){
                obj.wait();
                continue;
            }
            if(nodeType == null) {
                nodeType = node.getType();
            }
            if(isShare() && !node.getType().equals(Node.Type.SHARE)){
                obj.wait();
                continue;
            }
            acquire(1);
            headNode.setNextNode(node.getNextNode());
            break;
        }
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

    private void addWaiter(Node.Type type){
        tailNode.setCurrentThread(Thread.currentThread());
        tailNode.setType(type);
        Node newTailNode = new Node();
        tailNode.setNextNode(newTailNode);
        tailNode = newTailNode;
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

