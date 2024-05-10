package org.ashfaq.dev.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MTp3 {
	public int count = 0;

	private Lock lock = new ReentrantLock();

	public void incrementer() {
			lock.lock();
			try {
				for (int i = 1; i <= 100; i++) {
					count++;
				}
			} finally {
				lock.unlock();
			}

	}

	public void process() {

		Thread t1 = new Thread(() -> {
			incrementer();
		});

		Thread t2 = new Thread(() -> {

			incrementer();
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("the value of the counter is  : " + count);
	}

	public static void main(String[] args) {
		MTp3 mTp2 = new MTp3();

		for (int i = 0; i <= 9; i++) {
			mTp2.process();
			mTp2.count = 0;

		}
	}

}
