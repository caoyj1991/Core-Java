package com.current.lock;

import com.current.lock.v1.SynchronizedLock;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:55 PM
 */
public class LockTestMain {
    static int sum = 0;
    static SynchronizedLock lock = new SynchronizedLock();
    public static void main(String[] args){

        for (int i=0;i<1;i++){
            Thread thread =  new Thread(runnable , "thread-1");
            Thread thread2 =  new Thread(runnable, "thread-2");
            Thread thread3 =  new Thread(runnable , "thread-3");
            thread.start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread2.start();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread2.stop();
        }
    }

    public static Runnable runnable = ()->{
        try {
            lock.lock();
            String name = Thread.currentThread().getName();
            sum += Integer.valueOf(name.replace("thread-",""));
            System.out.println(name+" sum = "+sum);
//            Thread.currentThread().interrupt();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    };
}
