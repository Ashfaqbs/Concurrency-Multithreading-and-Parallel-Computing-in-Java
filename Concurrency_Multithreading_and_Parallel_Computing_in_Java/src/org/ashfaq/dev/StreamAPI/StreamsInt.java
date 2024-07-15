package org.ashfaq.dev.StreamAPI;

import java.util.stream.IntStream;

public class StreamsInt {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		int sum = 0;
//		for (int i : arr) {
//			sum += i;
//		}

//		System.out.println(sum);

//		Arrays.stream(arr).forEach(System.out::println);

		// using Streams
//		int reduce = Arrays.stream(arr).reduce(0, Integer::sum);
//		or
//		int reduce = Arrays.stream(arr).sum();
//		System.out.println(reduce);

		IntStream.range(1, 10).filter(data -> data > 4).forEach(System.out::println); // using Streams
	}
}
