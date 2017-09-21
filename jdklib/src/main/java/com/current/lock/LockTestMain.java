package com.current.lock;

import com.current.lock.v3.SynchronizedShareLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:55 PM
 */
public class LockTestMain {
    static int sum = 0;
    static SynchronizedShareLock lock = new SynchronizedShareLock();
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i=0;i<100;i++){
            String threadName ="";
            if (new Random().nextBoolean()&&i<50){
                threadName =  "WRITE=>thread-"+ i;
            }else {
                threadName =  "READ**>thread-"+ i;
            }
            Thread thread = new Thread(() -> {
                try {
                    if(Thread.currentThread().getName().startsWith("READ")){
                        lock.readLock();
                    }else {
                        lock.writeLock();
                    }
                    System.out.println(Thread.currentThread().getName()+" running");
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+" finish");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }, threadName);
            threads.add(thread);
        }
        for (Thread t : threads){
            t.start();
        }
        Thread.sleep(100);
        threads.get(new Random().nextInt(100)).interrupt();
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
