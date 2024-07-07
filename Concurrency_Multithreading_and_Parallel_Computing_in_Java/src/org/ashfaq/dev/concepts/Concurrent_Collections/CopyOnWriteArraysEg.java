package org.ashfaq.dev.concepts.Concurrent_Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

class ReaderW implements Runnable {

	private List<Integer> list;

	public ReaderW(List<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(list);

		}
	}

}

class WriterW implements Runnable {

	private List<Integer> list;
	Random random;

	public WriterW(List<Integer> list) {
		this.list = list;
		this.random = new Random();
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list.set(random.nextInt(list.size()), random.nextInt(10));
		}
	}

}

public class CopyOnWriteArraysEg {
//as arrayslist are not synchronized we can use copyonwriteArrays as this is synchronized 
	// we can make the arraylist synchronized by using
	// Collections.synchronizedList(arrayList) but that is not a efficient way

	private CopyOnWriteArrayList<Integer> arryArrayList;

	public CopyOnWriteArraysEg() {
		this.arryArrayList = new CopyOnWriteArrayList<Integer>();
		this.arryArrayList.addAll(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
	}

	public void stimulate() {
		Thread t1 = new Thread(new ReaderW(arryArrayList));
		Thread t3 = new Thread(new WriterW(arryArrayList));
		Thread t4 = new Thread(new WriterW(arryArrayList));
		Thread t5 = new Thread(new WriterW(arryArrayList));

		t1.start();

		t3.start();
		t4.start();
		t5.start();

	}

	public static void main(String[] args) {

		CopyOnWriteArraysEg arraysEg = new CopyOnWriteArraysEg();

		arraysEg.stimulate();

	}
}
