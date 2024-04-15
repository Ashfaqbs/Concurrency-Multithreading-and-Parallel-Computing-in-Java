package org.ashfaq.dev.concepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Sample {

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	void process1() {

		lock1.lock();
		System.out.println("Locking the process one with lock 1");

		lock2.lock();
		System.out.println("Locking the process one with lock 2");

		lock1.unlock();
		System.out.println("UnLocking the process one with lock 1");

		lock2.unlock();
		System.out.println("UnLocking the process one with lock 2");

	}

	void process2() {

		lock2.lock();
		System.out.println("Locking the process one with lock 1");

		lock1.lock();
		System.out.println("Locking the process one with lock 2");

		lock2.unlock();
		System.out.println("UnLocking the process one with lock 2");

		lock1.unlock();
		System.out.println("UnLocking the process one with lock 1");
	}

}

public class ClassicDeadLockExample {

	public static void main(String[] args) {

		Sample sample = new Sample();

		Thread t1 = new Thread(() -> {
			sample.process1();
		});

		Thread t2 = new Thread(() -> {
			sample.process2();

		});

		t1.start();
		t2.start();

		//		2 threads are getting called and they are calling 2 functions where 2 functions the order of acquiring the locks 
		//		is not same order L1 L2 , and then L2 L1 which is not  good and dead lock may arise if this functions are called concurrently
		// by 2 different threads

		// . Each method tries to acquire two locks in a different order.
		// This can lead to a classic deadlock scenario if these methods are called
		// concurrently by two different threads.

		// therefore we need to acquire locks in same order

		// Here's where the deadlock occurs:
		//
		// Thread t1 holds lock1 and is waiting for lock2.
		// Thread t2 holds lock2 and is waiting for lock1.
		// Both threads are waiting for a lock held by the other thread,
		// resulting in a deadlock situation. Neither thread can proceed, leading to a
		// program hang.
		//
		// To avoid deadlock, it's essential to acquire locks in a consistent order
		// across all threads.
		// If locks need to be acquired in different orders, additional strategies such
		// as lock ordering,
		// timeout mechanisms, or using tryLock() with a timeout can be employed to
		// prevent deadlock scenarios.
	}
}
