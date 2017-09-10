package com.current.queue;

import com.current.queue.jdkAPI.ArraysSynchronizedQueue;
import com.current.queue.projectAPI.ArraysSynchronizedLockQueue;
import com.io.bio.file.FileUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Author Mr.Pro
 * Date   9/9/17 = 1:07 PM
 */
public class QueueTestMain {

    public static void main(String[] arg){
        analyzeFunction();
        /*
        BlockingQueue<String> queue = new ArraysSynchronizedLockQueue<>();

        for (int i=0;i<500;i++){
            new Thread(()->{
                try {
                    queue.put(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "thread-"+i).start();
        }

        for (int i=0;i<500;i++){
            new Thread(()->{
                try {
                    System.out.println("output: "+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        */
    }

    public static void analyzeFunction(){
        try {
            String text =  FileUtils.readFile("test.txt");
            String[] lines = text.split("\n");
            Set<String> temp = new HashSet<>();
            for (String line : lines){
                line = line.replace("output: ","");
                temp.add(line);
            }

            for (int i=0;i<500;i++){
                if(!temp.contains("thread-"+i)){
                    System.out.print("thread-"+i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
