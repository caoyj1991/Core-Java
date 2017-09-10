package com.current.queue.jdkAPI;

import com.current.queue.BlockingQueue;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:32 PM
 */
public class ArraysSynchronizedQueue<T> implements BlockingQueue<T>{
    private Logger logger = Logger.getLogger(ArraysSynchronizedQueue.class.getName());
    private static int DEFAULT_SIZE = 100;
    private int arraySize;
    private int putIndex;
    private int takeIndex;
    private int size;
    private Byte[] lock;
    private Object[] obj;

    public ArraysSynchronizedQueue(){
        this(DEFAULT_SIZE);
    }

    public ArraysSynchronizedQueue(int arraySize){
        this.arraySize = arraySize;
        initialize();
    }

    private void initialize(){
        lock = new Byte[0];
        obj = new Object[arraySize];
        putIndex = 0;
        takeIndex = 0;
        size = 0;
    }

    @Override
    public T take() throws InterruptedException{
        synchronized (lock){
            while (size == 0){
                logger.log(Level.INFO, "queue is empty");
                lock.wait();
            }
            T result = (T)obj[takeIndex++];
            size --;
            takeIndex = setIndex(takeIndex);
            lock.notifyAll();
            return result;
        }
    }

    @Override
    public void put(T object) throws InterruptedException{
        synchronized (lock){
            while (size == arraySize){
                logger.log(Level.INFO, "queue is full");
                lock.wait();
            }
            obj[putIndex++] = object;
            size++;
            putIndex = setIndex(putIndex);
            lock.notifyAll();
        }
    }

    private int setIndex(int index){
        if(index >= arraySize){
            return 0;
        }
        return index;
    }

}
