package org.ashfaq.dev.parallelcomputing;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class SimpleRecursiveAction extends RecursiveAction {
	int simulateWork;

	public SimpleRecursiveAction(int simulateWork) {
		this.simulateWork = simulateWork;
	}

	@Override
	protected void compute() {

		if (simulateWork > 100) {

			System.out.println("Parallel Execution and split the tasks ..." + simulateWork);
			SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulateWork / 2);
			SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulateWork / 2);

			invokeAll(action1, action2);

		} else {

			System.out.println("The task is rather  small so sequential execution is fine ...");
			System.out.println("The size of the task " + simulateWork);
		}
	}

}

public class ForkJoinEg1 {

	public static void main(String[] args) {

		ForkJoinPool forkJoinPool = new ForkJoinPool();

//		System.out.println(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction action = new SimpleRecursiveAction(200);
		action.invoke();

	}

}
