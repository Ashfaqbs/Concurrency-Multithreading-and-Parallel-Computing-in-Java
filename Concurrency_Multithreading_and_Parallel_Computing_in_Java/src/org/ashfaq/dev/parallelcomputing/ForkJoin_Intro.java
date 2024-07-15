package org.ashfaq.dev.parallelcomputing;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Ashfaq
 *
 */

//The Fork/Join Framework in Java is a powerful tool for parallel processing and is particularly useful for tasks that can be broken down into smaller subtasks./
//1. Introduction to Fork/Join Framework
//The Fork/Join Framework is designed for tasks that can be recursively divided into smaller tasks. It utilizes a work-stealing algorithm to balance the load among worker threads.
//
//2. Key Concepts
//RecursiveTask: A task that returns a result.
//RecursiveAction: A task that does not return a result.
//ForkJoinPool: A specialized implementation of ExecutorService that facilitates the execution of ForkJoinTasks.
//3. Setting Up a Fork/Join Task
//a. Basic Example: Summing a Large Array
//Create a RecursiveTask:

class SumTask extends RecursiveTask<Integer> {
	private static final int THRESHOLD = 10;
	private int[] arr;
	private int start;
	private int end;

	public SumTask(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if (end - start < THRESHOLD) {
			int sum = 0;
			for (int i = start; i < end; i++) {
				sum += arr[i];
			}
			return sum;
		} else {
			int mid = (start + end) / 2;
			SumTask leftTask = new SumTask(arr, start, mid);
			SumTask rightTask = new SumTask(arr, mid, end);
			leftTask.fork(); // Asynchronously execute the left task
			int rightResult = rightTask.compute(); // Synchronously execute the right task
			int leftResult = leftTask.join(); // Wait for the left task to complete and get its result
			return leftResult + rightResult;
		}
	}}

//	Using the ForkJoinPool:

public class ForkJoin_Intro {

	public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1; // Fill the array with values 1 to 100
        }

        try (ForkJoinPool pool = new ForkJoinPool()) {
			SumTask task = new SumTask(arr, 0, arr.length);
			int result = pool.invoke(task);

			System.out.println("Sum: " + result);
		}
    }
	
	
}
