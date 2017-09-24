package com.current.thread.pool;

/**
 * Author Mr.Pro
 * Date   9/24/17 = 3:02 PM
 */
public class WaiterThread<T extends Runnable> extends Thread {

    private String name;
    private boolean canRun;
    private T t;

    public WaiterThread(String name){
        super(name);
    }

    public void setRunnable(T t){
        this.t = t;
    }

    public void waiterStop(){
        this.canRun = false;
    }

    public void run(){
        while (canRun){
            t.run();
        }
    }
}
