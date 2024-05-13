package org.ashfaq.dev.concepts;

import java.util.concurrent.Semaphore;

class ConnectionPool {
	private static final int MAX_CONNECTIONS = 5;
	private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS, true);

	public void useConnection() {
		try {
			semaphore.acquire();
			System.out.println("Connection Acquired by " + Thread.currentThread().getName());
			// Simulate using a connection
			Thread.sleep(1000); // Represents work with the connection
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} finally {
			semaphore.release();
			System.out.println("Connection Released by " + Thread.currentThread().getName());
		}
	}

}

//	Explanation:
//
//	Semaphore Initialization:
//	The semaphore
//	is initialized
//
//	to MAX_CONNECTIONS (5 in this case), which means up to 5 threads can acquire it concurrently.
//	Acquire and Release: Each thread tries to acquire the semaphore before using a connection and releases it afterward. If no connections are available (i.e., semaphore count is 0), the thread will block until a connection is released.
//	Fairness: Optionally, a semaphore can be made fair by passing true to the second argument of the Semaphore constructor, which makes sure that threads acquire the semaphore in an approximately first-come-first-serve order.
//	Using semaphores like this can help manage limited resources in multithreaded environments efficiently, preventing resource starvation and ensuring that no single thread monopolizes access to shared resources.

public class SemaphoresConcept {
	public static void main(String[] args) {
		ConnectionPool pool = new ConnectionPool();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(pool::useConnection);
			t.start();
		}
	}
}
