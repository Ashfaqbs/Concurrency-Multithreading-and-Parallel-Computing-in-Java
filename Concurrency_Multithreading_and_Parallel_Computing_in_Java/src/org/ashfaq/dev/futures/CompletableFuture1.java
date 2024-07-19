
package org.ashfaq.dev.futures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuture1 {

	public static void main(String[] args) {

		ExecutorService cpuService = Executors.newFixedThreadPool(5);
		ExecutorService ioService = Executors.newFixedThreadPool(5);


		CompletableFuture.supplyAsync(() -> "Hello world",cpuService)//diff thread
		.thenApplyAsync(s -> s.toUpperCase(),ioService)//diff thread and //we have thenApplyAsync which will run as thread asynchronously
		.thenApply(s -> s + "!!!")
		.thenAccept((s) -> System.out.println(s));// thenaccept is
		// used when we dont
		// want to return
		// anything

	}

}
