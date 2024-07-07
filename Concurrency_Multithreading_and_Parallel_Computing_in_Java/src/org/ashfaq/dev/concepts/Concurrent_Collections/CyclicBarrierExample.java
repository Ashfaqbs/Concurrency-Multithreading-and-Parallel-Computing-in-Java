package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private final CyclicBarrier barrier;
    private final int id;

    public Task(CyclicBarrier barrier, int id) {
        this.barrier = barrier;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("Task " + id + " is performing part 1.");
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println("Task " + id + " reached the barrier.");
            barrier.await(); // Wait for other threads
            System.out.println("Task " + id + " is performing part 2.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // Create a CyclicBarrier for 3 threads with a barrier action
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All tasks have reached the barrier. Proceeding..."));
        

        // Create and start three tasks
//        Thread t1 = new Thread(new Task(barrier, 1));
//        Thread t2 = new Thread(new Task(barrier, 2));
//        Thread t3 = new Thread(new Task(barrier, 3));
//
//        t1.start();
//        t2.start();
//        t3.start();
//        OP
        //Task 2 is performing part 1.
//        Task 1 is performing part 1.
//        Task 3 is performing part 1.
//        Task 2 reached the barrier.
//        Task 1 reached the barrier.
//        Task 3 reached the barrier.
//        All tasks have reached the barrier. Proceeding...
//        Task 2 is performing part 2.
//        Task 1 is performing part 2.
//        Task 3 is performing part 2.
        
        
//        Example Using Executors and CyclicBarrier

        
        int numberOfThreads = 3;
        // Create an ExecutorService with a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // Submit tasks to the executor service
        for (int i = 1; i <= numberOfThreads; i++) {
            executorService.submit(new Task(barrier, i));
        }

        // Shutdown the executor service
        executorService.shutdown();
        
//        OP
//        Task 2 is performing part 1.
//        Task 3 is performing part 1.
//        Task 1 is performing part 1.
//        Task 1 reached the barrier.
//        Task 3 reached the barrier.
//        Task 2 reached the barrier.
//        All tasks have reached the barrier. Proceeding...
//        Task 3 is performing part 2.
//        Task 2 is performing part 2.
//        Task 1 is performing part 2.
    }
}
