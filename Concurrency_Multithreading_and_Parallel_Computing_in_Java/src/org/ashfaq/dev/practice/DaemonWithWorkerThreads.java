package org.ashfaq.dev.practice;

public class DaemonWithWorkerThreads {

	public static void main(String[] args) {

		Thread wthread1 = new Thread(() -> {
			for (int i = 0; i <= 20; i++)
				System.out.println("Worker Thread 1 : " + i);

		});

		Thread wthread2 = new Thread(() -> {

			for (int i = 0; i <= 20; i++)
				System.out.println("Worker Thread 2 : " + i);

		});
		Thread daemonthread = new Thread(() -> {
			for (int i = 0; i <= 1000; i++)
				System.out.println("Daemon Thread : " + i);

		});

		daemonthread.setDaemon(true);
		daemonthread.start();
		wthread1.start();
		wthread2.start();
		
		

	}

}
