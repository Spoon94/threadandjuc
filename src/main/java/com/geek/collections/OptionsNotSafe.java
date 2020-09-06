package com.geek.collections;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OptionsNotSafe implements Runnable {

    private static ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<String, Integer>();
    private static boolean[] marked = new boolean[100000];
    private static Object mutex = new Object();
    AtomicInteger wrong = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        scores.put("小明", 0);
//        Thread t1 = new Thread(new OptionsNotSafe());
//        Thread t2 = new Thread(new OptionsNotSafe());
//        Thread t3 = new Thread(new OptionsNotSafe());
//        t1.start();
//        t2.start();
//        t3.start();
//        t1.join();
//        t2.join();
//        t3.join();
        ArrayList<Thread> threads = new ArrayList<>();

        for(int i=0;i<10;i++){
            Thread thread = new Thread(new OptionsNotSafe());
            threads.add(thread);
            thread.start();
        }
        for(int i=0;i<10;i++){
            threads.get(i).join();
        }
        System.out.println(scores);
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            while (true) {
                Integer score = scores.get("小明");
                synchronized (mutex){
                    if(marked[score]){
                        System.out.println("sorce wrong"+score);
                    }
                    marked[score]=true;
                }
                Integer newScore = score + 1;
                boolean b = scores.replace("小明", score, newScore);
                if (b) {
                    break;
                }
            }
        }

    }
}
