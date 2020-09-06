package com.geekagain.threadproperty;

import java.util.concurrent.CountDownLatch;

/**
 * @author 邱润泽 bullock
 */
public class ThreadProperty {
    public static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        System.out.println("主线程的ID:"+Thread.currentThread().getId());
        System.out.println("子线程ID:"+thread.getId());

        System.out.println("子线程的name:"+thread.getName());

        thread.setName("我是线程 name");

        System.out.println("子线程的name:"+thread.getName());

        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        latch.countDown();
        System.out.println(thread.getState());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());

    }
}
