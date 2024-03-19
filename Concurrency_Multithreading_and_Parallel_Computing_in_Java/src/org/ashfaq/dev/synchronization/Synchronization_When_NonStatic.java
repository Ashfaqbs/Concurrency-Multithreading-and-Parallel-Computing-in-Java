package org.ashfaq.dev.synchronization;

public class Synchronization_When_NonStatic {
	public int counter = 0;

	// we have to make sure this method is executed only by a single thread at a
	// given
	// time
	public void increment() {
		synchronized (this) {
			counter++;

		}

	}

	public void process() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 100; i++)
					increment();
			}
		});

		Thread t2 = new Thread(() -> {

			for (int i = 1; i <= 100; i++)
				increment();

		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Counter : " + counter);

	}

	public static void main(String[] args) {

		new Synchronization_When_NonStatic().process();
	}

}
