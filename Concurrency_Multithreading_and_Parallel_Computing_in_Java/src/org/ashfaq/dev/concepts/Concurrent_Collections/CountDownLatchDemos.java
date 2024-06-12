package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class WorkerTask implements Runnable {

	int count;
	private CountDownLatch latch;

	public WorkerTask(int count, CountDownLatch latch) {
		this.count = count;
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println(count + " and Thread + " + Thread.currentThread().getName() + "is running");
		doWork();
		latch.countDown();
	}

	public void doWork() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Task Execution is completed");

	}

}

public class CountDownLatchDemos {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(5);

		CountDownLatch countDownlatch = new CountDownLatch(5);

		for (int i = 0; i < 5; i++) {
			executor.submit(new WorkerTask(i + 1, countDownlatch));
		}

		try {
			countDownlatch.await();// Main thread waits until latch count reaches zero
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Initiate an orderly shutdown in which previously submitted tasks are
		// executed, but no new tasks will be accepted.
		executor.shutdown();
		try {
			if (executor.awaitTermination(2, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}

		} catch (Exception e) {
			executor.shutdownNow();
		}

		System.out.println("All the tasks are compeleted");

	}

}

//Using a CountDownLatch is somewhat similar to using thread.join(), but there are key differences that make CountDownLatch more flexible in certain situations.
//thread.join():
//
//It's
//
//used to wait for
//a specific
//thread to
//complete its
//execution.
//If you
//have multiple threads,
//you need
//to call
//
//join() on each thread individually.
//It is more straightforward for waiting on a single thread or a known set of threads.
//CountDownLatch:
//
//It allows one or more threads to wait for a set
//
//of operations (performed by multiple threads) to complete.
//You initialize it with a
//
//count (number of threads or tasks).
//Each thread
//
//calls countDown() when it finishes its task, decrementing the count.
//The waiting thread
//
//calls await() once, and it will block until the count reaches zero.
//CountDownLatch is more flexible because it can handle scenarios where multiple threads perform work that needs to be synchronized, and you don't need to join each thread individually. Instead, you just wait for the latch to count down to zero.



 class JoinExample extends Thread {
    private int id;

    public JoinExample(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Thread " + id + " is working.");
        try {
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Thread " + id + " has finished.");
    }

    public static void main(String[] args) {
        Thread t1 = new JoinExample(1);
        Thread t2 = new JoinExample(2);
        Thread t3 = new JoinExample(3);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All threads have finished. Main thread continues.");
    }
}

 

  class CountdownLatchExample implements Runnable {
     private int id;
     private CountDownLatch latch;

     public CountdownLatchExample(int id, CountDownLatch latch) {
         this.id = id;
         this.latch = latch;
     }

     @Override
     public void run() {
         System.out.println("Thread " + id + " is working.");
         try {
             Thread.sleep(1000); // Simulate work
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }
         System.out.println("Thread " + id + " has finished.");
         latch.countDown();
     }

     public static void main(String[] args) {
         int numberOfThreads = 3;
         CountDownLatch latch = new CountDownLatch(numberOfThreads);

         for (int i = 0; i < numberOfThreads; i++) {
             new Thread(new CountdownLatchExample(i, latch)).start();
         }
         
//         for every task done we have called latch.countDown(); so the count is decremented by 1 and if we see
//        		 we have 5 tasks and 5 countdowns and once the task is done in run we can see we are calling it 
//        		 and one all the task is done the count will be zero and we have to call .await() and next is
//        		 the main thread will continue once this is completed this is similar to the join method but more 
//        		 flexible.

         try {
             latch.await();
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }

         System.out.println("All threads have finished. Main thread continues.");
     }
 }
//  while both thread.join() and CountDownLatch can be used to wait for threads to complete, CountDownLatch offers a more scalable and flexible approach for managing multiple threads and their completion synchronization.