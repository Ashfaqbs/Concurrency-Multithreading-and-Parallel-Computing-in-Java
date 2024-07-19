package org.ashfaq.dev.virtualthreads;

import java.time.Duration;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class platformVsVirtualThreads {

	public static void main(String[] args) {

		// Platform threads
		// Creating large number and executing will result in OOM exception
//		for (int i = 0; i < 200000; i++) {
//
//			Thread.ofPlatform().start(() -> {
//				System.out.println("Started " + Thread.currentThread());
//				try {
//					Thread.sleep(Duration.ofSeconds(2));
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}); //2 mins to run 
//		}
		// OOM Error expected

		// Virtual threads
		// Creating large number and executing it

		// to create a large number of Virtual threads we have to use executor Service

		ExecutorService service = Executors.newVirtualThreadPerTaskExecutor();

		for (int i = 0; i < 200000; i++) {

			service.submit(() -> {
				System.out.println("Started " + Thread.currentThread());
				try {
					Thread.sleep(Duration.ofSeconds(2));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//2 secs to run
			});
		}
	}

}
