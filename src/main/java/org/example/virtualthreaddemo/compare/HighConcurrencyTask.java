package org.example.virtualthreaddemo.compare;

import java.util.concurrent.Executors;

public class HighConcurrencyTask {

    public static void main(String[] args) {

        int taskCount = 1000000;
        System.out.println("Testing the normal thread for high concurrency task....");
        long start = System.currentTimeMillis();
        var  normalExecutor = Executors.newThreadPerTaskExecutor(Thread.ofPlatform().factory());
        for(int  i = 0; i < taskCount; i++) {
         normalExecutor.submit(()->simulateTask());
        }
        long end  = System.currentTimeMillis();
        System.out.println("Total execution time taken by normal thread " + (end - start) + " ms");

        System.out.println("Testing the virtual thread for high concurrency task " );
       long vStart = System.currentTimeMillis();
       var virtualExecutor = Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory());
        for(int  i = 0; i < taskCount; i++) {
            virtualExecutor.submit(()->simulateTask());
        }
        long vEnd  = System.currentTimeMillis();
        System.out.println("Total execution time taken by virtual thread " + (vEnd - vStart) + " ms");


    }

    private static void simulateTask() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
