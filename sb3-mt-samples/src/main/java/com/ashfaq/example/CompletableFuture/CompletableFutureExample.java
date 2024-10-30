package com.ashfaq.example.CompletableFuture;

import java.util.concurrent.CompletableFuture;

//public class CompletableFutureExample {

//	public static void main(String[] args) {

//		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//			try {
//				Thread.sleep(2000); // Simulate some work
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			return "Task completed";
//		});
//
//		// Block the main thread until the CompletableFuture is complete
//		String result = future.join(); // Blocks and retrieves the result
//		System.out.println(result); // Output: Task completed
//	}
//}

//OR 

import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
	public static void main(String[] args) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000); // Simulate some work
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Task completed";
		});

		try {
			// Wait for the CompletableFuture to complete
			System.out.println(future.get()); // Output: Task completed
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}

//A CompletableFuture can be created without an ExecutorService

//join() is similar to get() but doesn’t require handling checked exceptions.

//get() is a standard way to retrieve the result and wait for completion but requires handling exceptions.
