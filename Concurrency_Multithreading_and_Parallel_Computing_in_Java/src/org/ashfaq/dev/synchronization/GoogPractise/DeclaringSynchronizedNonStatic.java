package org.ashfaq.dev.synchronization.GoogPractise;

public class DeclaringSynchronizedNonStatic {
	public int counter = 0;

//	it is not good to add syncronized keyword to method we have to use synchronized blocks
//	public static synchronized void incrementer() {
	public void incrementer() {

		synchronized (this) {
			counter++;
		}

	}

	public void process() {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 100; i++)
					incrementer();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 1; i <= 100; i++)
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

		System.out.println("Counter value is : " + counter);

	}

	public static void main(String[] args) {
		new DeclaringSynchronizedNonStatic().process();
	}
}
