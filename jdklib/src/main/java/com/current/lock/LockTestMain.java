package com.current.lock;

import com.current.lock.v1.SynchronizedLock;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:55 PM
 */
public class LockTestMain {
    static int sum = 0;
    public static void main(String[] args){
        SynchronizedLock lock = new SynchronizedLock();

        for (int i=0;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        String name = Thread.currentThread().getName();
                        sum += Integer.valueOf(name.replace("thread-",""));
                        System.out.println(name+" sum = "+sum);
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "thread-"+i).start();
        }
//        System.out.println("==================\n================\n===========");
    }
}
