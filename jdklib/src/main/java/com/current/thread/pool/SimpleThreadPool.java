package com.current.thread.pool;

import com.current.queue.BlockingQueue;
import com.current.queue.jdkAPI.ArraysSynchronizedQueue;

/**
 * Author Mr.Pro
 * Date   9/23/17 = 5:05 PM
 */
public class SimpleThreadPool<T extends Runnable> {
    private static final int DEFAULT_MIN_CORE_COUNT = 2;
    private static final int DEFAULT_MAX_CORE_COUNT = 4;

    private int mnCore;
    private int mxCore;
    private Thread[] waiters;
    private BlockingQueue<T> blockingQueue;

    public SimpleThreadPool(){
        this(DEFAULT_MIN_CORE_COUNT, DEFAULT_MAX_CORE_COUNT, new ArraysSynchronizedQueue<T>());
    }

    public SimpleThreadPool(int mnCore, int mxCore, BlockingQueue<T> blockingQueue){
        this.mnCore = mnCore;
        this.mxCore = mxCore;
        this.waiters = new Thread[mnCore];
        this.blockingQueue = blockingQueue;
        createWaiters();
    }

    public void execute(T task) throws InterruptedException {
        blockingQueue.put(task);
    }

    private void createWaiters(){
        for (int i = 0; i< waiters.length; i++){
            if (waiters[i] == null){
                waiters[i] = new Thread();
            }
            waiters[i].setName(getClass().getName()+"-pool-thread-"+(i+1));
        }
    }

    private void initializeWaiter(int count, boolean is){
        if(waiters.length<count){
            Thread[] newWaiters = new Thread[count];
            System.arraycopy(waiters, 0, newWaiters, 0, waiters.length);
            waiters = newWaiters;
        }else if(waiters.length > count){
            int workingIndex = 0;
            Thread[] working = new Thread[waiters.length];
            for (Thread waiter : waiters){
                if(waiter.isAlive()){
                    working[workingIndex++] = waiter;
                }
            }
            Thread[] newWaiters = new Thread[count];
            System.arraycopy(waiters, 0, newWaiters, 0, count);
            waiters = newWaiters;
        }
        createWaiters();
    }

    class MonitorThread extends Thread{

        public MonitorThread(){
            super();
            initialize();
        }

        private void initialize(){
            this.setDaemon(true);
            this.setName(SimpleThreadPool.class.getName()+"-monitor-thread");
        }

        @Override
        public void run() {
            while (true){

            }
        }
    }
}


