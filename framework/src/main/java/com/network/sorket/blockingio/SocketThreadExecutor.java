package com.network.sorket.blockingio;

import com.exception.ServerException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author Jun
 * Email
 * Date   6/24/17
 * Time   3:48 PM
 */
public class SocketThreadExecutor {

    private static volatile SocketThreadExecutor instance;
    private Executor executor;

    private final int THREAD_CORE_COUNT = 4;
    private final int THREAD_MAX_COUNT = 8;
    private final long THREAD_KEEP_ALIVE = 500;
    private final int BLOCKING_MAX_COUNT = 1000;

    public SocketThreadExecutor(){
        executor= new ThreadPoolExecutor(THREAD_CORE_COUNT, THREAD_MAX_COUNT, THREAD_KEEP_ALIVE, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(BLOCKING_MAX_COUNT));
    }

    public static SocketThreadExecutor getInstance(){
        if(instance == null){
            synchronized (SocketThreadExecutor.class){
                if(instance == null){
                    instance = new SocketThreadExecutor();
                }
            }
        }
        return instance;
    }

    public void execute(Runnable runnable) throws ServerException {
        try {
            executor.execute(runnable);
        }catch (Exception e){
            throw new ServerException(e.getMessage(), e);
        }
    }
}
