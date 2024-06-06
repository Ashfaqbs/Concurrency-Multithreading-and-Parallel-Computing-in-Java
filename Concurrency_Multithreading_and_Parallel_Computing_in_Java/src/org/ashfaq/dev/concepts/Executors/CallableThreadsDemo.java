package org.ashfaq.dev.concepts.Executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class tasks1 implements Callable<String> {
	int count;

	public tasks1(int count) {
		this.count = count;
	}

	@Override
	public String call() {
//		System.out.println("The count value is : " + count);
		return "The count value is : " + count;
	}

}

public class CallableThreadsDemo {
	public static void main(String[] args) {

		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		List<Future<String>> list = new ArrayList();

		for (int i = 0; i < 10; i++) {

//			execute is made for runnable as it wont return any value but submit can take both callable and runnable and but when we use callable it can return a value i
//					warpped in future object

			Future<String> submit = newFixedThreadPool.submit(new tasks1(i + 1));
			list.add(submit);
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

		list.forEach(data -> {
			try {
				System.out.println(data.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
}
