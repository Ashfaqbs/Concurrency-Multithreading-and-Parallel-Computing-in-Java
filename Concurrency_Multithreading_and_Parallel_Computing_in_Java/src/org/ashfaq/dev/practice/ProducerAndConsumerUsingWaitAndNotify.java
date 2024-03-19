package org.ashfaq.dev.practice;

public class ProducerAndConsumerUsingWaitAndNotify {

	private void producer() {

		synchronized (this) {

			System.out.println("starting the producer");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Sending the data to consumer");

			try {
				wait();
				System.out.println("Data is sent to the consumer ");
				System.out.println("Stoping the producer");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Producer failed to run");
				e.printStackTrace();
			}

		}
		;

	}

	private void consumer() {

		synchronized (this) {

			System.out.println("starting the Consumer");

			System.out.println("Receiving the data from producer");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Data transfer is completed to the consumer ");

			System.out.println("Stoping the consumer");
			notify();

		}
	}

	public static void main(String[] args) {
		ProducerAndConsumerUsingWaitAndNotify producerAndConsumerUsingWaitAndNotify = new ProducerAndConsumerUsingWaitAndNotify();
		Thread t1 = new Thread(() -> {
			producerAndConsumerUsingWaitAndNotify.producer();
		});

		Thread t2 = new Thread(() -> {
			producerAndConsumerUsingWaitAndNotify.consumer();
		});

		t1.start();
		t2.start();

	}

}
