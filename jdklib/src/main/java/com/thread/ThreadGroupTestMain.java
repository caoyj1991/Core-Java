package com.thread;

import java.util.Random;

/**
 * Author Mr.Pro
 * Date   10/2/17 = 4:24 PM
 */
public class ThreadGroupTestMain {

    public static void main(String[] args) throws InterruptedException {
        ThreadMonitoring monitoring = new ThreadMonitoring();

        Thread thread[] = new Thread[4];
        for (Thread t : thread){
            t = new Thread(()->{
                System.out.println("children thread is running");
                try {
                    Thread.sleep(1000*(new Random().nextInt(2)+1));
                    if(new Random().nextBoolean()){
                        Object object = new Object();
                        synchronized (object){
                            object.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("children thread is destroyed");
            });
            t.start();
        }
        monitoring.start();

    }

    public static class ThreadMonitoring extends Thread{
        int count = 4;
        public ThreadMonitoring(){
            super("daemon");
            this.setDaemon(true);
        }

        @Override
        public void run(){
            while (true){
                while (Thread.currentThread().isAlive()){
                    System.out.println("monitoring is alive");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
