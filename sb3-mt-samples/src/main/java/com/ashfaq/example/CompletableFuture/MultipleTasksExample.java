package com.ashfaq.example.CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MultipleTasksExample {

	public static void main(String[] args) {
		// Create a list of CompletableFuture tasks
		List<CompletableFuture<String>> tasks = List.of(CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000); // Simulate work for Task 1
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Task 1 completed";
		}), CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(1000); // Simulate work for Task 2
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Task 2 completed";
		}), CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(3000); // Simulate work for Task 3
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Task 3 completed";
		}));

		// Block to retrieve each result and print it
		tasks.forEach(task -> {
			try {
				System.out.println(task.get()); // Retrieve and print result
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		});
	}
}
