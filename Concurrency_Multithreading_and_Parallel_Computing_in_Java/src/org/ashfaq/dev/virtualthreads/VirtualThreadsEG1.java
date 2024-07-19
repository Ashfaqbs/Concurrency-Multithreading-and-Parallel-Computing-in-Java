package org.ashfaq.dev.virtualthreads;

import java.lang.Thread.Builder.OfPlatform;
import java.lang.Thread.Builder.OfVirtual;
import java.util.concurrent.ThreadFactory;

class VirtualTask implements Runnable {

	@Override
	public void run() {

		System.out.println("Starting " + Thread.currentThread().getName());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending " + Thread.currentThread().getName());

	}

	public static void runnerSample() {

		System.out.println("Starting " + Thread.currentThread().getName());

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ending " + Thread.currentThread().getName());

	}
}

public class VirtualThreadsEG1 {

	public static void main(String[] args) throws InterruptedException {

		// Java standard threads
//		ThreadFactory factory = Thread.ofPlatform().factory();
//		factory.newThread(new Runnable ())

		// Java Virtual Threads

////		#Approach 1 
//	
//		var builder = Thread.ofVirtual().name("Virtual ", 0); // providing the name and index
//
//		Thread start = builder.start(new VirtualTask()); // builder returns a Thread itself and not need to make setDeamon and no need to start as they will star then you did builder.start()
//
//		Thread start2 = builder.start(new VirtualTask());
//
//		// All Virtual Threads are Deamon threads
//
//		start.join();
//		start2.join(); 
//		
//		//once you add join it starts to run as the main thread have to wait for this to complete inorder to continue

//		#Approach 2

		var builder = Thread.ofVirtual().name("Virtual ", 0).factory();// providing the name and starting index, so next
																		// created virtual threads
		// will be incremented

		Thread start = builder.newThread(new VirtualTask());

		Thread start2 = builder.newThread(new VirtualTask());

		Thread start3 = builder.newThread(VirtualTask::runnerSample);

		start.start();
		start2.start();
		start3.start();

		start.join();
		start2.join();
		start3.join();
		// once you add join it starts to run as the main thread have to wait for this
		// to complete inorder to continue

	}

}
