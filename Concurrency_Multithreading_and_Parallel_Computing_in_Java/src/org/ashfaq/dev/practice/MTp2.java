package org.ashfaq.dev.practice;

public class MTp2 {
	public int count = 0;
	public int count2 = 0;

	private final Object l1 = new Object();
	private final Object l2 = new Object();

	public void incrementer() {
//		synchronized

//		synchronized (this) {
		synchronized (l1) {
			for (int i = 1; i <= 100; i++) {
				count++;
			}
		}

//		}

	}

	public void process() {

		Thread t1 = new Thread(() -> {
			incrementer();
		});

		Thread t2 = new Thread(() -> {

			incrementer2();
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

	private void incrementer2() {
		synchronized (l2) {
			for (int i = 1; i <= 100; i++) {
				count2++;
			}
		}

	}

	public static void main(String[] args) {

		MTp2 mTp2 = new MTp2();

		for (int i = 0; i <= 9; i++) {
			mTp2.process();
			mTp2.count = 0;

		}

	}

}
