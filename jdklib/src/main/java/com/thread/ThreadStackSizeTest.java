package com.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Author Mr.Pro
 * Date   10/4/17 = 4:34 PM
 */
public class ThreadStackSizeTest {
    static AtomicLong stackSize = new AtomicLong(0);

    public static void main(String[] args){

//        while (true){
            createThread();
//        }
    }

    public static void createThread(){

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread thread = new Thread(threadGroup, ()->{
            System.out.println(Thread.currentThread().getName()+" - run");
            Object[] objects = new Object[1];
            for (Object ob : objects){
                ob = (Object) new Object[500000000];
            }
        }, "thread-stack-size"+1,10000000000000000L);
        thread.start();
    }
}
