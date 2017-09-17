package com.current.entity;


/**
 * Author Mr.Pro
 * Date   9/10/17 = 5:20 PM
 */
public class LockCondition implements Condition {

    public static LockCondition newInstance() {
        return new LockCondition();
    }

    @Override
    public void await() throws InterruptedException {

    }

    @Override
    public void await(long time) throws InterruptedException {

    }

    @Override
    public void signal() throws InterruptedException {

    }

    @Override
    public void signalAll() throws InterruptedException {

    }
}
