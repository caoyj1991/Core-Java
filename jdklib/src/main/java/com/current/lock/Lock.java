package com.current.lock;

/**
 * Author Mr.Pro
 * Date   9/6/17 = 8:42 PM
 */
public interface Lock {

    void lock() throws InterruptedException;
    void unlock();
}
