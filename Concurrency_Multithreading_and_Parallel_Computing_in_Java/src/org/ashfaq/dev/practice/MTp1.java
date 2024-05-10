package org.ashfaq.dev.practice;

class ThreadS1 extends Thread {
	@Override
	public void run() {

		for (int i = 0; i <= 10; i++) {
			System.out.println("Running Thread : I " + i);
		}
	}

}

class ThreadS2 implements Runnable {
	@Override
	public void run() {

		for (int i = 0; i <= 10; i++) {
			System.out.println("Running Thread Runnable : I " + i);
		}
	}

}

public class MTp1 {

	public MTp1() {

		// TODO Auto-generated constructor stub
	}

	void subber() {
		for (int i = 0; i <= 10; i++) {
			System.out.println("Running Thread : I " + i);
		}
	}

	public static void main(String[] args) {
		System.out.println("Running all the threads from " + Thread.currentThread().getName()
				+ "  and its priority  is : " + Thread.currentThread().getPriority());

		ThreadS2 s1 = new ThreadS2();
		Thread t2 = new Thread(s1, "Runnable Thread");
		Thread s2 = new ThreadS1();
		Thread t5 = new Thread(new MTp1()::subber, "one more way Thread ");
		Thread t3 = new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				System.out.println("Running Lambda : I " + i);
			}
		}, "Lambda Thread");

		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <= 10; i++) {
					System.out.println("Running Anon : I " + i);
				}
			}
		}, "Anon thread");

		t2.start();
		s2.start();
		t3.start();
		t4.start();
		t5.start();
		try {
			t2.join();
			s2.join();
			t3.join();
			t4.join();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("All the threads are completed");

	}
}
