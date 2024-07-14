package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App4 {

	public static void main(String[] args) {
		
		List<Book> books = new ArrayList<>();
		books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
		books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
		books.add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
		books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
		books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
		books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
		books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

		// map() and flatMap() are similar to selecting a column in SQL
		// transform the original values
		// number of characters in every word
		List<String> words = Arrays.asList("Adam", "Ana", "Daniel");
		
		List<Integer> lengths = words.stream().map(String::length).collect(Collectors.toList());
		// lengths.stream().forEach(System.out::println);
		
		// create a list containing the squared values
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		
		nums.stream().map(x -> x * x).collect(Collectors.toList())
			.forEach(System.out::println);
		
		// flatMap() - mapping each array not with a stream but with the
		// contents of that stream
		// [[1, 3, 5], [5, 13]] -> [1, 3, 5, 5, 13]
		// "hello" "shell" - get all the unique characters (h,e,l,o,s)
		String[] array = {"hello", "shell"};
		List<String> unique = Arrays.stream(array).map(w -> w.split(""))
				.flatMap(Arrays::stream).distinct().collect(Collectors.toList());
		
		unique.stream().forEach(System.out::println);
	}

}
