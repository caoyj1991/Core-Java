package com.current.queue;

/**
 * Author Mr.Pro
 * Date   9/9/17 = 12:40 PM
 */
public interface BlockingQueue<T> {

    T take() throws InterruptedException;

    void put(T object) throws InterruptedException;
}
