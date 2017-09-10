package com.current.queue.projectAPI;

import com.current.lock.Lock;
import com.current.lock.v1.SynchronizedLock;
import com.current.queue.BlockingQueue;

import java.util.logging.Logger;

/**
 * Author Mr.Pro
 * Date   9/10/17 = 3:48 PM
 */
public class ArraysSynchronizedLockQueue<T> implements BlockingQueue<T> {
    private Logger logger = Logger.getLogger(ArraysSynchronizedLockQueue.class.getName());
    private static int DEFAULT_SIZE = 100;
    private int arraySize;
    private int putIndex;
    private int takeIndex;
    private int size;
    private Lock lock;
    private Object[] obj;

    public ArraysSynchronizedLockQueue(){
        this(DEFAULT_SIZE);
    }

    public ArraysSynchronizedLockQueue(int arraySize){
        this.arraySize = arraySize;
        initialize();
    }

    private void initialize(){
        lock = new SynchronizedLock();
        obj = new Object[arraySize];
        putIndex = 0;
        takeIndex = 0;
        size = 0;
    }

    @Override
    public T take() throws InterruptedException{
        T result = null;
        while (true){
            try {
                lock.lock();
                if(size == 0){
                    continue;
                }
                result = (T) obj[takeIndex++];
                size--;
                takeIndex = setIndex(takeIndex);
                break;
            }catch (InterruptedException exception) {
                logger.warning(exception.getMessage());
            }finally {
                lock.unlock();
            }
        }
        return result;
    }

    @Override
    public void put(T object) throws InterruptedException{
        while (true){
            try {
                lock.lock();
                if(size == arraySize){
                    continue;
                }
                obj[putIndex++] = object;
                size++;
                putIndex = setIndex(putIndex);
                break;
            }catch (InterruptedException exception) {
                logger.warning(exception.getMessage());
            }finally {
                lock.unlock();
            }
        }
    }

    private int setIndex(int index){
        if(index >= arraySize){
            return 0;
        }
        return index;
    }

}