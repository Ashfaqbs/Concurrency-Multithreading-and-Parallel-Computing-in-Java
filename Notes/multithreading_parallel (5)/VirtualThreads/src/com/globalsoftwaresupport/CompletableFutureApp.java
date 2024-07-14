package com.globalsoftwaresupport;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureApp {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		
		ExecutorService cpuExecutor = Executors.newFixedThreadPool(5);
		ExecutorService ioExecutor = Executors.newCachedThreadPool();
		
		CompletableFuture.supplyAsync(() -> "Hello world!", cpuExecutor)
						.thenApplyAsync(s -> s.toUpperCase(), ioExecutor)
						.thenApply(s -> s + " something")
						.thenAccept(System.out::println);							
	}
}
