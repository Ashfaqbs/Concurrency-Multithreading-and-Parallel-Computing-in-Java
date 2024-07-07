package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorker implements Runnable {

	private BlockingQueue<String> queue;

	public FirstWorker(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {

			queue.put("A");
			queue.put("B");
			queue.put("C");
			Thread.sleep(2000);
			queue.put("D");
			Thread.sleep(2000);
			queue.put("E");
			queue.put("F");

		} catch (InterruptedException e) {
		}
	}
}

class SecondWorker implements Runnable {

	private BlockingQueue<String> queue;

	public SecondWorker(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {

			Thread.sleep(5000);
			System.out.println(queue.take());
			Thread.sleep(2000);
			System.out.println(queue.take());
			Thread.sleep(2000);
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());

		} catch (InterruptedException e) {
		}
	}
}


public class PriorityQueue {

	public static void main(String[] args) {

		BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);

		Thread t1 = new Thread(firstWorker);
		Thread t2 = new Thread(secondWorker);

		t1.start();
		t2.start();
		
		
		
		
		
		

	}

}
