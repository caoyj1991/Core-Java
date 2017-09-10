package com.current.entity;

/**
 * Author Mr.Pro
 * Date   9/10/17 = 5:22 PM
 */
public interface Condition {

    void await();

    void await(long time);

    void signal();

    void signalAll();
}
