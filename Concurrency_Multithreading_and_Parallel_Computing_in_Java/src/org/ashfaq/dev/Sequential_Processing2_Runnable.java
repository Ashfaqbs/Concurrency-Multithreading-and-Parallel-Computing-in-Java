package org.ashfaq.dev;

class Runner1 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 10; i++) {
			System.out.println("Runner 1 : " + i);
		}

	}
//	public void execute() {
//
//		for (int i = 0; i <= 10; i++) {
//
//			System.out.println("Runner 1 : " + i);
//		}
//	}

}

class Runner2 implements Runnable {
//	public void execute() {
//		for (int i = 0; i <= 10; i++) {
//
//			System.out.println("Runner 2 : " + i);
//		}
//
//	}

	@Override
	public void run() {
		// Passing the task
		for (int i = 0; i <= 10; i++) {
			System.out.println("Runner 2 : " + i);
		}

	}
}

public class Sequential_Processing2_Runnable {

	public static void main(String[] args) {

//		Runner1 runner1 = new Runner1();
//		Runner2 runner2 = new Runner2();
//		runner1.execute();
//		runner2.execute();
		Thread t1 = new Thread(new Runner1());

		
//		Passing Logic as 
		// anonymous class
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <= 10; i++) {
					System.out.println("Runner 1 : " + i);
				}
			}
		});

		// Passing function as lambas
		Thread t4 = new Thread(() -> {
			for (int i = 0; i <= 10; i++) {
				System.out.println("Runner 2 : " + i);
			}
		});
		Thread t2 = new Thread(new Runner2());

		t3.start();
		t4.start();
	}

}
