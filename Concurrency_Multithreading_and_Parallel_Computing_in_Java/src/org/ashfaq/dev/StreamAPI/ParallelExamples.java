package org.ashfaq.dev.StreamAPI;

import java.util.stream.LongStream;

public class ParallelExamples {

	private static long sum(long n) {

		return LongStream.rangeClosed(0L, n).reduce(0L, Long::sum);

	}

	private static long parallelSum(long n) {

		return LongStream.rangeClosed(0L, n).parallel().reduce(0L, Long::sum);

	}

	public static void main(String[] args) {

		// parallel() - because we have to make sure the given stream can be
		// parallelized
		// under the hood, it is going to use the ForkJoinPool framework

		long timeMillis = System.currentTimeMillis();
		System.out.println("Serial Sum: " + sum(100_000_000L));
		System.out.println("Serial Time: " + (System.currentTimeMillis() - timeMillis));

		timeMillis = System.currentTimeMillis();
		System.out.println("Parallel Sum: " + parallelSum(100_000_000L));
		System.out.println("Parallel Time: " + (System.currentTimeMillis() - timeMillis));
		
		
//		OP
		
		//Serial Sum: 5000000050000000
//		Serial Time: 180
//		Parallel Sum: 5000000050000000
//		Parallel Time: 34


	}
}
