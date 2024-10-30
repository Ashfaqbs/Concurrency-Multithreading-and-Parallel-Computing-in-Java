package com.ashfaq.example.future;

import java.util.concurrent.*;

class SimpleCallable implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(2000); // Simulate some work with a 2-second delay
		return "Task completed!";
	}
}

public class CallableFutureExample {

	public static void main(String[] args) {
		// Create an ExecutorService with a single thread
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		// Create an instance of our Callable task
		Callable<String> task = new SimpleCallable();

		// Submit the task to the executor and receive a Future object
		Future<String> future = executorService.submit(task);

		// Use Future to retrieve the result
		try {
			// Perform other work here if needed while waiting for the result
			System.out.println("Waiting for the task to complete...");

			// Call get() to block until the task is complete and retrieve the result
			String result = future.get();
			System.out.println("Result from Callable: " + result);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			// Shut down the executor
			executorService.shutdown();
		}
	}
}


//SimpleCallable class: Implements Callable<String> and returns a string message after a delay.
//ExecutorService: Manages the task asynchronously. We use submit() to execute the Callable and immediately receive a Future.
//Future’s get(): Waits until the Callable finishes and returns the result.