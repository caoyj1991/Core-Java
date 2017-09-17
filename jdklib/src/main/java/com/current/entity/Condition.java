package com.current.entity;

/**
 * Author Mr.Pro
 * Date   9/10/17 = 5:22 PM
 */
public interface Condition {

    void await() throws InterruptedException;

    void await(long time) throws InterruptedException;

    void signal() throws InterruptedException;

    void signalAll() throws InterruptedException;
}
