package com.globalsoftwaresupport;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureApp2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		try(var service = Executors.newVirtualThreadPerTaskExecutor()) {
			
			String res = CompletableFuture
				.supplyAsync(DbQuery::run, service)
				.thenCombine(CompletableFuture
						.supplyAsync(RestQuery::run, service),
							(res1, res2) -> {
									return res1 + res2;
							})
				.join();
			
			System.out.println(res);
			
			
			// try with resources closes (shuts down the threads) automatically
			// the threads will be executed (we will wait for them to finish)
		}
		
		
		
		
		/*
		CompletableFuture.supplyAsync(() -> "Hello")
						.thenCombine(CompletableFuture
								.supplyAsync(() -> "World"), (s1, s2) -> s1 + " - " + s2)
						.thenApply(s -> s.toUpperCase())
						.thenAccept(System.out::println);
		*/
		
		
		
	}
}
