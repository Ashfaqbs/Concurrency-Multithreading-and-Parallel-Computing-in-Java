package org.ashfaq.dev.practice;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DLp1 {

	private final Lock lock1 = new ReentrantLock();
	private final Lock lock2 = new ReentrantLock();

	public void process1() {
		System.out.println("Starting the process one ");

		lock1.lock();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Locking with lock one ");

		lock2.lock();

		System.out.println("Locking with lock two ");

		lock1.unlock();
		System.out.println("un Locking with lock one ");

		lock2.unlock();
		System.out.println("un Locking with lock two ");
		System.out.println("Ending  the process one ");

	}

	public void process2() {

		System.out.println("Starting the process two ");

		lock1.lock();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Locking with lock one ");

		lock2.lock();

		System.out.println("Locking with lock two ");

		lock1.unlock();
		System.out.println("un Locking with lock one ");

		lock2.unlock();
		System.out.println("un Locking with lock two ");
		System.out.println("Ending  the process two ");

	}

	public DLp1() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DLp1 dLp1 = new DLp1();

		new Thread(() -> {
			dLp1.process1();
		}).start();
		new Thread(() -> {
			dLp1.process2();
		}).start();

	}

}
