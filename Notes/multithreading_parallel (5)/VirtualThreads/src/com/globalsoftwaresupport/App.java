package com.globalsoftwaresupport;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException, ExecutionException {


		
		try(var service = Executors.newVirtualThreadPerTaskExecutor()) {	
			service.submit(VirtualTask::run); 
			service.submit(VirtualTask::run);
			service.submit(VirtualTask::run);
			
			// wait
		}
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		Future<String> result = executor.submit(new FutureTask());
			
		while(!result.isDone()) {
			System.out.println("Main thread is waiting for the result ...");
		}
		
		// blocks the main thread !!!
		String res = result.get();
		
		System.out.println(res);
		
		*/
		
		
		
		
		
		
		

		/*
		 * // try with resource approach try(var executor =
		 * Executors.newVirtualThreadPerTaskExecutor()) {
		 * executor.submit(VirtualTask::run); executor.submit(VirtualTask::run);
		 * executor.submit(VirtualTask::run); }
		 * 
		 * 
		 * 
		 * /* // approach #2 var factory = Thread.ofVirtual().name("virtual-",
		 * 0).factory();
		 * 
		 * var t1 = factory.newThread(VirtualTask::run); var t2 =
		 * factory.newThread(VirtualTask::run);
		 * 
		 * t1.start(); t2.start();
		 * 
		 * // all virtual threads are daemon threads !!! t1.join(); t2.join();
		 * 
		 * // approach #1 var builder = Thread.ofVirtual().name("virtual-", 0);
		 * 
		 * var t1 = builder.start(new VirtualTask()); var t2 = builder.start(new
		 * VirtualTask());
		 * 
		 * // all virtual threads are daemon threads !!! t1.join(); t2.join();
		 * 
		 */
	}

	private static String externalCall() {
		System.out.println("External call started...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("External call finished...");
		return "External result";
	}
}
