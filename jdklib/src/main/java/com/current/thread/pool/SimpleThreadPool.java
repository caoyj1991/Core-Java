package com.current.thread.pool;

import com.current.queue.BlockingQueue;
import com.current.queue.projectAPI.ArraysSynchronizedLockQueue;

/**
 * Author Mr.Pro
 * Date   9/23/17 = 5:05 PM
 */
public class SimpleThreadPool {
    private static final int DEFAULT_MIN_CORE_COUNT = 2;
    private static final int DEFAULT_MAX_CORE_COUNT = 4;

    private int mnCore;
    private int mxCore;
    private Thread[] waiters;
    private BlockingQueue<? extends Runnable> blockingQueue;

    public SimpleThreadPool(){
        this(DEFAULT_MIN_CORE_COUNT, DEFAULT_MAX_CORE_COUNT, new ArraysSynchronizedLockQueue<Runnable>());
    }

    public SimpleThreadPool(int mnCore, int mxCore, BlockingQueue< ? extends Runnable> blockingQueue){
        this.mnCore = mnCore;
        this.mxCore = mxCore;
        this.waiters = new Thread[mnCore];
        this.blockingQueue = blockingQueue;

    }

    private void initializeWaiter(int count){
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
        for (int i = 0; i< waiters.length; i++){
            waiters[i] = new Thread();
        }
    }

}
