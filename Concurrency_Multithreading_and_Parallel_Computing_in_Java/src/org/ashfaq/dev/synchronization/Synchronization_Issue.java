package org.ashfaq.dev.synchronization;

public class Synchronization_Issue {

	public static int counter = 0;

	public  static void process() {
 
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 1; i <= 100; i++)
					counter++;
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				for (int i = 1; i <= 100; i++)
					counter++;
			}
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

		System.out.println("Counter  : " + counter);

	}

	public static void main(String[] args) {

		process();
	}

}
