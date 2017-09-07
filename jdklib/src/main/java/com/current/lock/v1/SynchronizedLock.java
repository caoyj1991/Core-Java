package com.current.lock.v1;

import com.current.lock.Lock;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:32 PM
 */
public class SynchronizedLock implements Lock {

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
                obj.wait();
            }
            hasLock = true;
            if(Thread.currentThread().getName().equals("thread-55")) {
                Thread.currentThread().interrupt();
                System.out.println("sleep");
                wait();
                System.out.println(Thread.currentThread().getName() + " interrupted=" + Thread.currentThread().isInterrupted());
            }

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
