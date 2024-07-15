package org.ashfaq.dev.StreamAPI;

import java.util.stream.Stream;

public class StreamsString {

	public static void main(String[] args) {
		String[] nameStrings = { "Ashfaq", "Hassan", "Ahmed", "Sony", "Tom", "Jerry", "Tony" };

//		Stream.of(nameStrings).sorted().forEach(System.out::println);

		Stream.of(nameStrings).filter(data -> data.startsWith("A")).forEach(System.out::println);

	}

}
