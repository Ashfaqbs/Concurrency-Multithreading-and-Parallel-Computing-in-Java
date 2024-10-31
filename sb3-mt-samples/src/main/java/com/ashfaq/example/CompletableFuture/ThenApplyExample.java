package com.ashfaq.example.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
	public static void main(String[] args) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			sleep(1000);
			return "Task completed";
		});

		// thenApply will transform the result and return a new CompletableFuture
		CompletableFuture<String> transformedFuture = future.thenApply(result -> result + " - transformed");

		transformedFuture.thenAccept(finalResult -> System.out.println("Transformed Result: " + finalResult));
		// Output: Transformed Result: Task completed - transformed

		transformedFuture.join(); // Wait for all to complete
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
