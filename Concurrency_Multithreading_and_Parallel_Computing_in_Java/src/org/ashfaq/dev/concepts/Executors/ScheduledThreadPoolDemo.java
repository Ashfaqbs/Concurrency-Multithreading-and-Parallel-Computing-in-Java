package org.ashfaq.dev.concepts.Executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class StockMarketUpdater implements Runnable {

	public StockMarketUpdater() {
	}

	@Override
	public void run() {
		System.out.println("Downloading and Updating the stock related data from Web");
	}

}

public class ScheduledThreadPoolDemo {

	public static void main(String[] args) {
		// This creates a scheduled thread pool with 1 thread.
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);

		// here we can schedule a thread Initial Delay: 1 (1 second). The task will
		// first run after 1 second. Period: 2 (2 seconds). The task will then run every
		// 2 seconds.
		// Defining the task
		newScheduledThreadPool.scheduleAtFixedRate(new StockMarketUpdater(), 1, 2, TimeUnit.SECONDS);

		// here we don't have to deal with synchronization , timers , frequency so using
		// Executors and ExecutorsService is convenient
		// Schedule the shutdown of the executor service after a certain period (e.g., 10 seconds)
        newScheduledThreadPool.schedule(() -> {
            System.out.println("Shutting down the executor service...");
            newScheduledThreadPool.shutdown();
            try {
                // Wait for all tasks to finish within 5 seconds
                if (!newScheduledThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
                    System.out.println("Forcibly shutting down remaining tasks...");
                    newScheduledThreadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                newScheduledThreadPool.shutdownNow();
            }
        }, 10, TimeUnit.SECONDS);
	}
}
