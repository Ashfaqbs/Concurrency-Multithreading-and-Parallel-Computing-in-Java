package org.ashfaq.dev.Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class worker {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();// Returns a new Condition instance that is bound to this Lock
														// instance.
//and we cannot call wait and notify  and in synchronization blocks  we have used wait and notify and whats crucial is we are not able 
	// to call this types of methods and this is why we need an independent
	// condition

	public void producer() throws InterruptedException {

		lock.lock();

		System.out.println("Starting the producer");

		System.out.println("Sending the data to consumer");

		condition.await();
		System.out.println("back in Producer");
		lock.unlock();

	}

	public void consumer() throws InterruptedException {

		lock.lock();

		System.out.println("Starting the consumer");

		System.out.println("processing the data in consumer");
		Thread.sleep(3000);
		condition.signal();

		System.out.println("consumer is completed");

		lock.unlock();
	}
}

public class ConsumerAndProducerBasicPatternUsingLock {

	public static void main(String[] args) {

		worker wk = new worker();
		Thread t1 = new Thread(() -> {
			try {
				wk.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				wk.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t1.start();

		t2.start();
	}

}
