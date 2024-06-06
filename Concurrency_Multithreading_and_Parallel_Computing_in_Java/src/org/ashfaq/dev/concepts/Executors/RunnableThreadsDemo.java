package org.ashfaq.dev.concepts.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class tasks implements Runnable {
	int count;

	public tasks(int count) {
		this.count = count;
	}

	@Override
	public void run() {
		System.out.println("The count value is : " + count);
	}

}

public class RunnableThreadsDemo {

	public static void main(String[] args) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		for (int i = 0; i < 10; i++) {
			
//			execute is made for runnable as it wont return any value but submit can take both callable and runnable and but when we use callable it can return a value i
//					warpped in future object
			newFixedThreadPool.execute(new tasks(i + 1));
//OR
//			newFixedThreadPool.submit(new tasks(i + 1));
		}

		newFixedThreadPool.shutdown();

		try {

			if (newFixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
				newFixedThreadPool.shutdownNow();
			}

		} catch (Exception e) {
			// TODO: handle exception
			newFixedThreadPool.shutdownNow();

		}

	}

}
