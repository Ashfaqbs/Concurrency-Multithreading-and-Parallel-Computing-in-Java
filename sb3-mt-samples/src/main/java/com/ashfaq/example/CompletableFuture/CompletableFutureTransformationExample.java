package com.ashfaq.example.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTransformationExample {
	public static void main(String[] args) {
		CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
			sleep(1000);
			return "Task 1 result";
		});

		CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
			sleep(1500);
			return "Task 2 result";
		});

		CompletableFuture<String> combinedFuture = task1.thenCombine(task2,
				(result1, result2) -> result1 + " + " + result2);

		combinedFuture.thenAccept(result -> System.out.println("Combined Result: " + result)); // Output: Combined
																								// Result: Task 1 result
																								// + Task 2 result

		// Ensure the main thread waits for combinedFuture to complete
		combinedFuture.join();
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
