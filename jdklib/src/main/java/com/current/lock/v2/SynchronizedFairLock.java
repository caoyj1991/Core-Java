package com.current.lock.v2;

import com.current.lock.Lock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author Mr.Pro
 * Date   9/7/17 = 1:34 PM
 */
public class SynchronizedFairLock implements Lock{

    private Logger logger = Logger.getLogger(SynchronizedFairLock.class.getName());

    private Byte[] obj;
    private volatile Boolean hasLock;
    private Node head;
    private Node tail;

    public SynchronizedFairLock(){
        obj = new Byte[0];
        hasLock = false;
        head = new Node();
        tail = head;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (obj){
            addNode();
            while (true){
                if(!hasLock && isFirstNode()){
                    hasLock = true;
                    break;
                }else{
                    try {
                        obj.wait();
                    }catch (InterruptedException exception){
                        logger.log(Level.WARNING, Thread.currentThread().getName()+" is interrupted");
                        throw new InterruptedException(exception.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (obj){
            if(isFirstNode()){
                head.next = getFirstNode().next;
                hasLock = false;
            }
            obj.notifyAll();
        }
    }

    private boolean isFirstNode(){
        Node node = getFirstNode();
       return Thread.currentThread().equals(node.threadFlag);
    }

    private void addNode(){
        Node node = new Node();
        node.threadFlag = Thread.currentThread();
        tail.next = node;
        tail = node;
    }

    private Node getFirstNode(){
        while (true){
            Node firstNode = head.next;
            if(firstNode.threadFlag.isAlive())
                return firstNode;
            head.next = head.next.next;
            logger.log(Level.WARNING, firstNode.threadFlag.getName()+" has been stopped");
        }
    }

    class Node{
        Thread threadFlag;
        Node next = null;
    }
}
