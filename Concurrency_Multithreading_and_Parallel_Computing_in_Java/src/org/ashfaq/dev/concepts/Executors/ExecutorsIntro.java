package org.ashfaq.dev.concepts.Executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsIntro {

	public static void main(String[] args) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);

		for (int i = 0; i <= 99; i++) {
			newFixedThreadPool.submit(() -> {

				System.out.println("Executing by a thread : " + Thread.currentThread().getName());

			});
		}
		
		
		
		
		
		
		

	}
}
