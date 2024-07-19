package org.ashfaq.dev.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

class DbQuery {

	public static String run() {
		System.out.println("DB operation started...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("DB operation finished...");
		return "Adam";
	}
}

class RestQuery {

	public static String run() {
		System.out.println("REST operation started...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("REST operation finished...");
		return " 23";
	}
}

public class CompletableFuture2 {
	public static void main(String[] args) {

//		CompletableFuture.supplyAsync(() -> "Hello")
//				.thenCombine(CompletableFuture.supplyAsync(() -> " World"), (s1, s2) -> s1 + s2)
//				.thenAccept(System.out::println);

		// we convert the above code into single line by two CompletableFuture

		try (var service = Executors.newVirtualThreadPerTaskExecutor();) {

//			CompletableFuture.supplyAsync(DbQuery::run, service)
//					.thenCombine(CompletableFuture.supplyAsync(RestQuery::run, service), (s1, s2) -> {
//						return s1 + s2;
//					}).thenAccept(System.out::println);

			// join
			String result = CompletableFuture.supplyAsync(DbQuery::run, service)
					.thenCombine(CompletableFuture.supplyAsync(RestQuery::run, service), (s1, s2) -> {
						return s1 + s2;
					}).join();

			System.out.println(result);

			// try with resource automatically closes threads
			// the threads will be executed in (we will wait for them to finish)
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
