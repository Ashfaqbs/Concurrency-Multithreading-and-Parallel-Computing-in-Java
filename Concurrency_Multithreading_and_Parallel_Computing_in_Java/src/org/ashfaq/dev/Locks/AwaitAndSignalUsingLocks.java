package org.ashfaq.dev.Locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pr2 {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void producer() throws InterruptedException {

		lock.lock();

		try {
			System.out.println("Starting the producer ....");
			System.out.println("Sending  the data from  producer to consumer ");
			condition.await();

			System.out.println("Back in producer");
			System.out.println("producer is completed");

		} finally

		{
			// TODO: handle finally clause
			lock.unlock();

		}

	}

	public void consumer() throws InterruptedException {

		lock.lock();

		try {
			System.out.println("Starting the consumer ....");

			System.out.println("processing  the data from  producer to consumer ");
			Thread.sleep(2000);
			System.out.println("Processing the data is completed  in consumer");

			condition.signal();

			System.out.println("consumer is completed");

		} finally

		{
			// TODO: handle finally clause
			lock.unlock();

		}

	}
	
}

	public class AwaitAndSignalUsingLocks {

		public static void main(String[] args) throws InterruptedException {

			System.out.println("Starting the producer and consumer ");
			Pr2 pp = new Pr2();

			Thread t1 = new Thread(() -> {
				try {
					pp.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			Thread t2 = new Thread(() -> {
				try {
					pp.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			t1.start();
			t2.start();

			t1.join();
			t2.join();

			System.out.println(" the producer and consumer  is completed");

		}

	}


