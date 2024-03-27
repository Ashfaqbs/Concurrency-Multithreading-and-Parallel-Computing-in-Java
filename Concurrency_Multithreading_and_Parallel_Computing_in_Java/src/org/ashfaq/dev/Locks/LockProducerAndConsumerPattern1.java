package org.ashfaq.dev.Locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Pr1 {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private static final int UL = 5;
	private static final int LL = 0;
	private List<Integer> list = new ArrayList<Integer>();
	private int value = 0;

	public void producer() throws InterruptedException {

		lock.lock();

		try {
			while (true) {

				if (list.size() == UL) {

					condition.await();
					System.out.println("Waiting the data to be removed : " + value);

				} else {

					System.out.println("Adding the data in list : " + value);
					list.add(value);
					value++;

					condition.signal();

				}
				Thread.sleep(1000);

			}

		} finally {
			// TODO: handle finally clause
			lock.unlock();

		}

	}

	public void consumer() throws InterruptedException {

		lock.lock();
		try {
			while (true) {

				if (list.size() == LL) {

					condition.await();
					System.out.println("Waiting the data to be added : " + value);

				} else {

					System.out.println("removing the data in list : " + list.remove(list.size() - 1));
					value = 0;

					condition.signal();

				}
				Thread.sleep(1000);

			}
		} finally {
			// TODO: handle finally clause
			lock.unlock();

		}

	}

}

public class LockProducerAndConsumerPattern1 {

	public static void main(String[] args) {

		Pr1 pp = new Pr1();

		Thread t1 = new Thread(() -> {
			try {
				pp.producer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Thread t2 = new Thread(() -> {
			try {
				pp.consumer();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		t1.start();
		t2.start();

	}

}
