package org.ashfaq.dev.concepts.Executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
	int i;

	public Task(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("Count of the i is " + i + " the Thread name is " + Thread.currentThread().getName());

		long time = (long) (Math.random() * 5);

		// Thread.sleep(time);
		// OR
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class SingleThreadExecutors {

	public static void main(String[] args) {

		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();// Tasks are executed
																						// sequentially, one after
																						// another.

		// only one thread will take this and execute sequentially
		for (int i = 1; i <= 20; i++)
			newSingleThreadExecutor.submit(new Task(i));
//		Submits 20 tasks to the single-thread executor.
//		Each task will be executed sequentially by the single worker thread.

		// Shutting down process

		// We prevent the executors to execute any further tasks
		newSingleThreadExecutor.shutdown();// Initiates an orderly shutdown in which previously submitted tasks are
											// executed, but no new tasks will be accepted.

		// Terminate actual (running tasks)

		try {

			if (newSingleThreadExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
				// Waits for 1 second for the executor to finish executing the existing tasks
                // If tasks are not completed within 1 second, forcefully shutdown remaining tasks

				newSingleThreadExecutor.shutdownNow();

			}

		} catch (Exception e) {
			// If current thread is interrupted, forcefully shutdown remaining tasks
			newSingleThreadExecutor.shutdownNow();
		}

	}

}
//here after 5 seconds all the threads will be forcefully shutdown
