package org.ashfaq.dev.practice;

class T1 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i <= 99; i++)
			System.out.println("Thread 1 : " + i);

	}

}

class T2 extends Thread {

	@Override
	public void run() {
		for (int i = 0; i <= 99; i++)
			System.out.println("Thread 2 : " + i);

	}

}

public class ThreadClass {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Starting the thread");

		Thread t1 = new T1();
		Thread t2 = new T2();
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i <= 99; i++)
					System.out.println("Thread 3 : " + i);

			}
		});

		Thread t4 = new Thread(() -> {
			for (int i = 0; i <= 99; i++)
				System.out.println("Thread 4 : " + i);

		});

		t4.setPriority(1);
		t1.start();
		t2.start();

		t3.start();

		t4.start();

		t4.join();
		t2.join();
		t3.join();
		t1.join();

		System.out.println("Threads are completed");

	}

}
