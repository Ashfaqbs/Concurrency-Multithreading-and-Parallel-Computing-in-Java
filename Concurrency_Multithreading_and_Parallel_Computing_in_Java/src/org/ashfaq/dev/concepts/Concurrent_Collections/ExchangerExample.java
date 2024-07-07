package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.concurrent.Exchanger;

class FirstThread implements Runnable {

	int counter;

	private Exchanger<Integer> exchanger;

	public FirstThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (true) {
			counter++;
			System.out.println("FirstThread incremented the counter :  " + counter);

			try {
				counter = exchanger.exchange(counter);
				System.out.println("FirstThread get the counter :  " + counter);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

class SecondThread implements Runnable {

	int counter;

	private Exchanger<Integer> exchanger;

	public SecondThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while (true) {
			counter--;
			System.out.println("SecondThread deccremented the counter :  " + counter);

			try {
				counter = exchanger.exchange(counter);
				System.out.println("SecondThread get the counter :  " + counter);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}

public class ExchangerExample {

	public static void main(String[] args) {

		Exchanger<Integer> exchanger = new Exchanger<Integer>();

		Thread t1 = new Thread(new FirstThread(exchanger));

		Thread t2 = new Thread(new SecondThread(exchanger));

		t1.start();

		t2.start();

	}
}
