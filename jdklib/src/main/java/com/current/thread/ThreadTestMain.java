package com.current.thread;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author Mr.Pro
 * Date   9/21/17 = 11:38 PM
 */
public class ThreadTestMain {

    public static void main(String[] args) throws InterruptedException {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000);
            return "Done";
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(2000);
    }
}
