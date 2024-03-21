package org.ashfaq.dev.waitAndNotify;

import java.util.ArrayList;
import java.util.List;

class Process1 {
	private List<Integer> list = new ArrayList<Integer>();
	// Defining the max or upper limit of arraylist
	private static final int UPPER_LIMIT = 5;
	// min limit of arralist
	private static final int LOWER_LIMIT = 0;

	private int value = 0;

//	the working will be like the producer will add 1 2 3 4 5 in a arraylist and consumer once the arraylist has 5 data in it will remove it till
//	empty and this will go on infinitely 
	// buy this we are low level synchronization

	public void producer() throws InterruptedException {

		synchronized (this) {

			while (true) {

				if (list.size() == UPPER_LIMIT) {
					value = 0;
					System.out.println("Waiting for the removing the items ");
					this.wait();

				} else {

					System.out.println(" Adding : " + value);
					list.add(value);
					value++;

					notify();

				}
				Thread.sleep(500);

			}

		}

	}

	public void consumer() throws InterruptedException {
		synchronized (this) {

			while (true) {

				if (list.size() == LOWER_LIMIT) {
					System.out.println("Waiting for the adding the items ");
					this.wait();

				} else {

					System.out.println(" remove : " + list.remove(list.size() - 1));

					notify();

				}
				Thread.sleep(500);
			}

		}
	}

}

public class Producer$Consumer_Pattern {

	public static void main(String[] args) {

		Process1 pprocess = new Process1();

		Thread t1 = new Thread(() -> {
			try {
				pprocess.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				pprocess.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();
	}

}
