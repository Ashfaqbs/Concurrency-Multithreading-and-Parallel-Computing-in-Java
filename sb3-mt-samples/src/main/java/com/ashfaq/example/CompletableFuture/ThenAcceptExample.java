package com.ashfaq.example.CompletableFuture;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {

	public static void main(String[] args) {
		System.out.println("Starting async task...");

		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Running async task...");
			try {
				Thread.sleep(2000); // Simulate some work
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Task completed";
		});

		// Attach thenAccept to handle the result when it's available, non-blocking
		future.thenAccept(result -> System.out.println("Result: " + result));

//		future.join();// not to use join , as this would make the thread blocking  commenting out

		System.out.println("End of main method.");

	}

}
