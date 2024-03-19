package org.ashfaq.dev.waitAndNotify;

public class ProducerConsumerproblem {

	private void producer() {
		// TODO Auto-generated method stub

		synchronized (this) {

			System.out.println("starting the producer");

			System.out.println("Sendind data to consumer");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Data is sent is completed");
			System.out.println("Closing the producer");

		}

	}

	private void consumer() {
		synchronized (this) {

			System.out.println("starting the consumer");

			System.out.println("Receiving data from producer consumer");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			notify();

			System.out.println("Data process is completed");
			System.out.println("Closing the consumer");

		}

	}

	public static void main(String[] args) {

		ProducerConsumerproblem consumerproblem = new ProducerConsumerproblem();

		Thread t1 = new Thread(() -> {
			consumerproblem.producer();
		});

		Thread t2 = new Thread(() -> {
			consumerproblem.consumer();
		});

		t1.start();
		t2.start();

	}

}
