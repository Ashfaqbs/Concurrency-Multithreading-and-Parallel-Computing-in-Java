package org.ashfaq.dev.parallelcomputing;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
//a. RecursiveAction
//If you don't need a return value from your task, use RecursiveAction.

class IncrementTask extends RecursiveAction {
	private static final int THRESHOLD = 10;
	private int[] arr;
	private int start;
	private int end;

	public IncrementTask(int[] arr, int start, int end) {
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if (end - start < THRESHOLD) {
			for (int i = start; i < end; i++) {
				arr[i]++;
			}
		} else {
			int mid = (start + end) / 2;
			IncrementTask leftTask = new IncrementTask(arr, start, mid);
			IncrementTask rightTask = new IncrementTask(arr, mid, end);
			invokeAll(leftTask, rightTask);
		}
	}
}

//b. Using ForkJoinPool with RecursiveAction

class ForkJoin_Intro2 {
	public static void main(String[] args) {
		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i; // Fill the array with values 0 to 99
		}

		ForkJoinPool pool = new ForkJoinPool();
		IncrementTask task = new IncrementTask(arr, 0, arr.length);
		pool.invoke(task);

		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}


//Best Practices
//Threshold Selection: Choosing the right threshold is critical for performance. It should be neither too small nor too large.
//Avoid Synchronous Blocking: Avoid blocking operations in the compute method.
//Monitor Performance: Use profiling tools to monitor the performance of your tasks.