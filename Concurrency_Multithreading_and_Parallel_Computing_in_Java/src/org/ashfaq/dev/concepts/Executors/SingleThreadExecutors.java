package org.ashfaq.dev.concepts.Executors;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
	int i;

	public Task(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		System.out.println("Count of the i is " + i + " the Thread name is " + Thread.currentThread().getName());

		long time = (long) (Math.random() * 5);

//		Thread.sleep(time);
//		OR 
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

public class SingleThreadExecutors {

	public static void main(String[] args) {

		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		
		//only one thread will take this and execute sequentely 
		for(int i =1;i<=20;i++)
			newSingleThreadExecutor.submit(new Task(i));
		
		
		
		
	}

}
