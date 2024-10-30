package com.ashfaq.example.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultipleCallablesExample {

	public static void main(String[] args) {
		// Create an ExecutorService with a fixed thread pool
		ExecutorService executorService = Executors.newFixedThreadPool(3);

//		() -> {
//			Thread.sleep(1000); // Simulate work
//			return "Task 1 completed";
//		 this is a callable object returning a string

		// Create a list of Callable tasks
		List<Callable<String>> taskList = new ArrayList<>();
		taskList.add(() -> {
			Thread.sleep(1000); // Simulate work
			return "Task 1 completed";
		});
		taskList.add(() -> {
			Thread.sleep(2000); // Simulate work
			return "Task 2 completed";
		});
		taskList.add(() -> {
			Thread.sleep(3000); // Simulate work
			return "Task 3 completed";
		});

		try {
			// Execute all tasks and wait for them to complete
			List<Future<String>> futures = executorService.invokeAll(taskList);

			// Loop through each Future to get the results
			for (Future<String> future : futures) {
				// Future.get() will block until the task completes
				System.out.println("Result: " + future.get());
			}

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			// Shut down the executor
			executorService.shutdown();
		}
	}
}



//Parallel Execution: With invokeAll, tasks run concurrently (up to the pool size), improving efficiency.
//Blocking Until Completion: invokeAll waits until all tasks finish before returning the list of Future objects.
//Result Gathering: Accessing each task’s result is straightforward by iterating through the Future list and calling get().