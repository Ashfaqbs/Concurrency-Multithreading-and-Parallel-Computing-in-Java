package org.ashfaq.dev.StreamAPI;

import java.util.stream.IntStream;

public class SequentialVsParallelStream {

	public static boolean isPrime(int n) {

		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;

		long maxDivisor = (long) Math.sqrt(n);
		for (int i = 3; i <= maxDivisor; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {

		// sequential

		long timeMillis = System.currentTimeMillis();

		long count = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100).filter(SequentialVsParallelStream::isPrime)
				.count();

		System.out.println("Serial Count: " + count);

		System.out.println("Serial Time: " + (System.currentTimeMillis() - timeMillis));

		// parallel
		timeMillis = System.currentTimeMillis();

		count = IntStream.rangeClosed(2, Integer.MAX_VALUE / 100).parallel().filter(SequentialVsParallelStream::isPrime)
				.count();

		System.out.println("Parallel Count: " + count);

		System.out.println("Parallel Time: " + (System.currentTimeMillis() - timeMillis));

		// OP
//		Serial Count: 1358124
//		Serial Time: 3678
//		Parallel Count: 1358124
//		Parallel Time: 538
	}
}
