package org.ashfaq.dev.parallelcomputing;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinPool.ForkJoinWorkerThreadFactory;
import java.util.concurrent.ForkJoinWorkerThread;

public class CustomForkJoinWorkerThread extends ForkJoinWorkerThread {

	protected CustomForkJoinWorkerThread(ForkJoinPool pool) {
		super(pool);
	}

	// Custom behavior can be added here
}

class CustomForkJoinPool {

	public static void main(String[] args) {
		ForkJoinWorkerThreadFactory factory = pool -> new CustomForkJoinWorkerThread(pool);
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors(), factory, null, false);

		// Use the custom pool as usual
	}
}

//Summary
//The Fork/Join Framework is a powerful tool for parallel processing in Java. By understanding and utilizing RecursiveTask, 
//RecursiveAction, and ForkJoinPool, you can efficiently handle tasks that can be divided into smaller subtasks. Remember to follow best practices for optimal performance.