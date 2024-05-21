package org.ashfaq.dev.concepts;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaPhore2 {
	
	
	/**
	 * 
	 * IT is used to control access to a shared resource 
	 * that uses a counter variable
	 * Semaphore maintains a set of permits
	 * -acquire() ..if permits is available then takes it
	 * -release() ..adds a permit
	 * Semaphore just keeps a count of the number of permits available
	 * new Semaphore(int permits, boolean fairness )
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public enum Downloader {
		INSTANCE;

		private Semaphore semaphore = new Semaphore(3, true);

		public void download() {
			try {
				semaphore.acquire();

				downloadData();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// TODO: handle finally clause
				semaphore.release();
			}

		}

		void downloadData() {

			try {
				System.out.println("Downloading the data from web ....." + Thread.currentThread().getName());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		//create multiple threads - executor
		ExecutorService executor = Executors.newCachedThreadPool();

		for (int i = 0; i < 19; i++) {
			executor.submit(new Thread(() -> {

				Downloader.INSTANCE.download();

			}), "Thread : " + i);
		}

	}

}
