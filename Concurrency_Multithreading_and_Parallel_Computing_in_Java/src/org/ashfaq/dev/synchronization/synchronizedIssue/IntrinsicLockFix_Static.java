package org.ashfaq.dev.synchronization.synchronizedIssue;

public class IntrinsicLockFix_Static {

	public static int counter1 = 0;
	public static int counter2 = 0;

	private static final Object lock1 = new Object();
	private static final Object lock2 = new Object();

	public static void incrementer1() {

		synchronized (lock1) {
			counter1++;
		}
	}

	public static synchronized void incrementer2() {
		synchronized (lock2) {
			counter2++;
		}
	}

	public static void process() throws InterruptedException {

		Thread t1 = new Thread(() -> {
			for (int i = 0; i <= 100; i++)
				incrementer1();
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i <= 100; i++)
				incrementer2();
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		System.out.println("Counter 1 : " + counter1);

		System.out.println("Counter 2 : " + counter2);

	}

	public static void main(String[] args) throws InterruptedException {
		process();
	}

}
