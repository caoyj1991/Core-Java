package com.thread;

import java.io.PrintStream;

/**
 * Author Mr.Pro
 * Date   10/4/17 = 2:53 PM
 */
public class ThreadUncaughtExceptionTestMain {

    private static PrintStream out = System.out;
    public static void main(String[] args){
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer integer = null;
            if(integer.intValue() == 1){

            }
        });
        thread.setName("uncaughtException-thread");
        thread.setUncaughtExceptionHandler((t, e) ->
            out.println(t.getName()+" has "+e.getCause().toString())
        );

        thread.start();
    }
}
