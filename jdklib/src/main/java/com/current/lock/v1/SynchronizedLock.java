package com.current.lock.v1;

import com.current.lock.Lock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:32 PM
 */
public class SynchronizedLock implements Lock {

    private Logger logger = Logger.getLogger(SynchronizedLock.class.getName());

    private Byte[] obj;
    private volatile boolean hasLock;

    public SynchronizedLock(){
        obj = new Byte[0];
        hasLock = false;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (obj){
            while (hasLock){
                try{
                    obj.wait();
                }catch (InterruptedException exception){
                    logger.log(Level.WARNING, Thread.currentThread().getState().toString());
                    throw new InterruptedException(exception.getMessage());
                }
            }
            hasLock = true;
        }
    }

    @Override
    public void unlock() {
        synchronized (obj){
            hasLock = false;
            obj.notifyAll();
        }
    }
}
