package org.ashfaq.dev.concepts;

public class DeadlockExample {
//	In this example:
//
//		Thread 1 locks resource1 and then tries to lock resource2.
//		Meanwhile, Thread 2 locks resource2 and then tries to lock resource1.
//		Both threads will be waiting indefinitely for the other to release the resource they need, causing a deadlock.
//	
	
	public static void main(String[] args) {
		// Two resource objects
		Object resource1 = new Object();
		Object resource2 = new Object();

		// Thread 1 acquires resource1, then tries to acquire resource2
		Thread thread1 = new Thread(() -> {
			synchronized (resource1) {
				System.out.println("Thread 1: Acquired resource1");
				try {
					Thread.sleep(100); // Introducing delay to simulate some work
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (resource2) {
					System.out.println("Thread 1: Acquired resource2");
				}
			}
		});

		// Thread 2 acquires resource2, then tries to acquire resource1
		Thread thread2 = new Thread(() -> {
			synchronized (resource2) {
				System.out.println("Thread 2: Acquired resource2");
				try {
					Thread.sleep(100); // Introducing delay to simulate some work
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (resource1) {
					System.out.println("Thread 2: Acquired resource1");
				}
			}
		});

		// Start both threads
		thread1.start();
		thread2.start();

		// Wait for threads to complete
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Execution complete");
	}
}
