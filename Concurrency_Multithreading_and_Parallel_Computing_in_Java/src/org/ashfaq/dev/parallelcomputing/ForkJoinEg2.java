package org.ashfaq.dev.parallelcomputing;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SimpleRecursiveAction1 extends RecursiveTask<Integer> {

	int num;

	public SimpleRecursiveAction1(int num) {
		this.num = num;
	}

	@Override
	protected Integer compute() {

		if (num > 100) {
			System.out.println("Parallel execution and split the tasks ..." + num);

			SimpleRecursiveAction1 action1 = new SimpleRecursiveAction1(num / 2);
			SimpleRecursiveAction1 action2 = new SimpleRecursiveAction1(num / 2);
			action1.fork();
			action2.fork();

			int subsolution = 0;
			subsolution += action1.join();
			subsolution += action2.join();
			return subsolution;
		} else {
			System.out.println("The task is rather small so sequential execution is fine ...");
			return 2 * num;
		}

	}
}

public class ForkJoinEg2 {

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		SimpleRecursiveAction1 simpleRecursiveAction1 = new SimpleRecursiveAction1(50);
		System.out.println(forkJoinPool.invoke(simpleRecursiveAction1));

	}

}
