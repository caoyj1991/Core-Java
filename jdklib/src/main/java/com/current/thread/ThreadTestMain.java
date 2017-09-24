package com.current.thread;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author Mr.Pro
 * Date   9/21/17 = 11:38 PM
 */
public class ThreadTestMain {

    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {

                        System.out.println("run");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread.currentThread().yield();
                    if(i++ == 5){
                        break;
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){
                    try {

                        System.out.println("run2");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i++ == 10){
                        break;
                    }
                }
            }
        });
        thread2.start();
//        Thread.sleep(1000);
        thread.start();

    }
}
