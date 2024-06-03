package org.ashfaq.dev.concepts.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task1 implements Runnable {
	int i;

	public Task1(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("Count of the i is " + i + " the Thread name is " + Thread.currentThread().getId());

		long time = (long) (Math.random() * 5);

//		Thread.sleep(time);
//		OR 
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class FixedThreadExecutorsDemo {

	public static void main(String[] args) {
		// Creates a thread pool with 2 threads. This pool will manage the execution of
		// tasks.
		ExecutorService newFixedThreadExecutor = Executors.newFixedThreadPool(2);

		// there is a thread pool and we have 2 threads and they will take up 10 tasks
		// and will do the tasks sequentially or parallely based on the cores available
		// as soon as the thread is done executing the task it will take the available
		// task and complete and .... same for the other thread and both will execute
		// the task till
		// all the task is finshed
		for (int i = 0; i < 10; i++)
			newFixedThreadExecutor.submit(new Task1(i));
		// Submitting 10 tasks to the thread pool. These tasks will be executed by the 2
		// threads in the pool.
		// The threads will take tasks from the queue as soon as they finish their
		// current task.

		// we have not yet shut down the threads

		// The ExecutorService needs to be shut down after tasks are submitted to ensure
		// that no new tasks
		// are accepted and the existing tasks are completed. This can be done with a
		// shutdown method.

//		Shutting Down the ExecutorService
//		To shut down the ExecutorService, you should follow these steps:
//
//		Initiate Shutdown:
//
//		Call shutdown() to initiate an orderly shutdown. This method will not accept new tasks but will allow already submitted tasks to complete.
//		Await Termination:
//
//		Use awaitTermination to wait for the executor service to finish executing all tasks or time out.
//		Force Shutdown if Necessary:
//
//		If awaitTermination times out, you can call shutdownNow() to forcefully terminate all running tasks.

		// Initiating an orderly shutdown
//		newFixedThreadExecutor.shutdown();
//
//		try {
//			// Waiting for the executor service to terminate
//			if (!newFixedThreadExecutor.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
//				// Forcefully shutting down if not terminated within the timeout
//				newFixedThreadExecutor.shutdownNow();
//			}
//		} catch (InterruptedException e) {
//			// Handle interruption during awaitTermination
//			newFixedThreadExecutor.shutdownNow();
//		}
//
//		System.out.println("All tasks completed.");
		
		
		
		
		
		
		
		
		
		
		

	}

}
