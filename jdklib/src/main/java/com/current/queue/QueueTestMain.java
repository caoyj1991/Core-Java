package com.current.queue;

import com.current.queue.jdkAPI.ArraysSynchronizedQueue;

/**
 * Author Mr.Pro
 * Date   9/9/17 = 1:07 PM
 */
public class QueueTestMain {

    public static void main(String[] arg){
        BlockingQueue<String> queue = new ArraysSynchronizedQueue<>();

        for (int i=0;i<500;i++){
            new Thread(()->{
                try {
                    queue.put(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread-"+i).start();
        }

        for (int i=0;i<500;i++){
            new Thread(()->{
                try {
                    System.out.println("output: "+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
