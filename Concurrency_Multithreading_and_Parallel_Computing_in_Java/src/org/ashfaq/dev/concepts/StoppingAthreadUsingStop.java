package org.ashfaq.dev.concepts;

public class StoppingAthreadUsingStop {

	//

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {

			System.out.println("Action");

		});

		t1.start();
		t1.stop();// stop method is deprecated as its unsafe

		// so if we want to stop a given thread from running we have to setup a boolean
		// flag like how
		// we have used in volatile concept so we will follow that approach itself by
		// using of volatile keyword to stop a thread

	}

}
