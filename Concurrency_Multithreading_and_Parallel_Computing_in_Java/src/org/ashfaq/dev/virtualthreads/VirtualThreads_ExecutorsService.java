package org.ashfaq.dev.virtualthreads;

import java.util.concurrent.Executors;

public class VirtualThreads_ExecutorsService {

	public static void main(String[] args) {

		// there are no pools for Virtual threads ,
		// But we have to make sure that we have to wait for them to finish as virtual
		// threads are deamon threads
		// so we have to use try with resources as we dont have to call the join()
		// method

		try (var executorService = Executors.newVirtualThreadPerTaskExecutor()) {

			for (int i = 0; i < 1000; i++) {
				executorService.submit(() -> {
					System.out.println(Thread.currentThread());
				});
			}
		}

	}

}
