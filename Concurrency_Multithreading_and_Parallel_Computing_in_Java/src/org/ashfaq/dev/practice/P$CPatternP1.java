package org.ashfaq.dev.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class PP {

	private List<Integer> arrayList = new ArrayList<Integer>();
	Logger logger = Logger.getLogger(getClass().getName());

	private static final int UL = 5;

	private static final int LL = 0;

	private int value = 0;

	private final Object lock = new Object();

	public void producer() {

//		synchronized (this) {
//		OR
		synchronized (lock) {// here a custom object lock can be used as well

			while (true) {
				if (UL == arrayList.size()) {

					logger.info("Waiting the data to be removed");
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					logger.info("Adding  the data : " + value);
					arrayList.add(value);
					value++;
					lock.notify();
				}

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void consumer() {

//		synchronized (this) {
		// OR
		synchronized (lock) {

			while (true) {
				if (LL == arrayList.size()) {

					logger.info("Waiting the data to be removed");
					try {
//						wait();
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					logger.info("Removing number : " + arrayList.remove(arrayList.size() - 1));
					value = 0;
//					notify();
					lock.notify();
				}

				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}
	}

}

public class P$CPatternP1 {

	public static void main(String[] args) {

		PP pp = new PP();

		Thread t1 = new Thread(() -> {

			pp.producer();
		});

		Thread t2 = new Thread(() -> {

			pp.consumer();
		});

		t1.start();
		t2.start();

	}

}
