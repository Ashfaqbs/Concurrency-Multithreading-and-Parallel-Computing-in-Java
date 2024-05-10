package org.ashfaq.dev.concepts;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

	public AtomicInteger counterATM = new AtomicInteger(0);

	// no need to add synchronize or reentrantLocks as AtomicInteger use lowlevel
	// synchronization
	// this is how we can use AtomicIntegers , similarly we have AtomicBoolean ,
	// AtomiLong
	// AtomicIntegerArray ....
	public void incrementer() {
		for (int i = 1; i <= 10000; i++) {
			counterATM.getAndIncrement();
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
			e.printStackTrace();
		}

		System.out.println("The Count is : " + counterATM);

	}

	public static void main(String[] args) {

		AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();

		for (int i = 0; i < 10; i++) {
			atomicIntegerDemo.process();
			atomicIntegerDemo.counterATM.set(0);
		}
	}

}
