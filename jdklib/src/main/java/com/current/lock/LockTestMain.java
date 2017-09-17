package com.current.lock;

import com.current.lock.v3.SynchronizedShareLock;

import java.util.Random;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:55 PM
 */
public class LockTestMain {
    static int sum = 0;
    static SynchronizedShareLock lock = new SynchronizedShareLock();
    public static void main(String[] args){

        for (int i=0;i<15;i++){
            final int finalI = i;
            new Thread(() -> {
                try {
                    if (new Random().nextBoolean()){
                        lock.writeLock();
                        Thread.currentThread().setName("WRITE=>thread-"+ finalI);
                    }else {
                        lock.readLock();
                        Thread.currentThread().setName("READ=>thread-"+ finalI);
                    }
                    System.out.println(Thread.currentThread().getName()+" running");
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+" finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }).start();
        }

//        for (int i=0;i<10;i++){
//            new Thread(() -> {
//                try {
//                    lock.readLock();
//                    System.out.println(Thread.currentThread().getName()+" running");
//                    Thread.sleep(100);
//                    System.out.println(Thread.currentThread().getName()+" finish");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    lock.unlock();
//                }
//            }, "READ=>thread-"+i).start();
//        }
    }
}
