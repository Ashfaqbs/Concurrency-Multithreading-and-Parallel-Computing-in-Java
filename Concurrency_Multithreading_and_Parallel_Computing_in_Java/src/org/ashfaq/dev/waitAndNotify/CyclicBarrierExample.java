package org.ashfaq.dev.waitAndNotify;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


//CyclicBarrier is a synchronization aid that allows a set of threads to all wait for each other to reach a common barrier point. It is useful
//in programs involving a fixed-sized party of threads that must occasionally wait for each other.

public class CyclicBarrierExample {
    private static class Task implements Runnable {
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try { 
                System.out.println(Thread.currentThread().getName() + " is waiting at the barrier.");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + " has crossed the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final int totalThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(totalThreads, () -> System.out.println("All threads have reached the barrier. They now proceed together."));

        Thread t1 = new Thread(new Task(barrier), "Thread 1");
        Thread t2 = new Thread(new Task(barrier), "Thread 2");
        Thread t3 = new Thread(new Task(barrier), "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
