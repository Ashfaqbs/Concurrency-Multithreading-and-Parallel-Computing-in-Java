package org.ashfaq.dev.synchronization.synchronizedIssue;

public class SyncIssueFixForInd2Threads {

	private final Object object1 = new Object();
	private final Object object2 = new Object();

	public int counter1 = 0;
	public int counter2 = 0;

	public void incrementer1() {
		synchronized (object1) {
			counter1++;

		}
	}

	public synchronized void incrementer2() {
		synchronized (object2) {
			counter2++;

		}
	}

	public void process() throws InterruptedException {

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
		new SynchronizedIssueFixForInd2Threads().process();
	}

}
