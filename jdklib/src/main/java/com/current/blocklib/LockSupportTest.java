package com.current.blocklib;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport class is created for make synchronized class or lock.
 */
public class LockSupportTest {

    public static Integer object = 1;

    public static void main(String arg[]) throws InterruptedException {

        Thread thread2 = new Thread(()->{
            synchronized (object){
                System.out.println("thread2 sleep");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 finish");
            }
        });
        thread2.start();

        Thread thread = new Thread(()->{
            System.out.println("thread1 park");
            LockSupport.park(object);
            System.out.println("thread1 unpark");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(1000);
        LockSupport.unpark(thread);
    }
}
