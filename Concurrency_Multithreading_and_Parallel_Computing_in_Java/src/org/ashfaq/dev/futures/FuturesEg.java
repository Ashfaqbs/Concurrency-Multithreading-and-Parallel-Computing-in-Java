package org.ashfaq.dev.futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class FutureTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		System.out.println("Starting callable ");

		Thread.sleep(3000);

		System.out.println("Ending callable ");

		return "Callable Finished";
	}
}

public class FuturesEg {
//basically futures , when you call the get() it will block the main thread so we have to use Callable Futures
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		var executorService = Executors.newFixedThreadPool(5);

		Future<String> result = executorService.submit(new FutureTask());
		// When called it will block the main threads

		while (!result.isDone()) {
			System.out.println("The Main thread is waiting for the result");
		}

		System.out.println(result.get());

	}

}
