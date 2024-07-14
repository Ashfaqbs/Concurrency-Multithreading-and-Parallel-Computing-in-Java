package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App5 {

	public static void main(String[] args) {
		
		List<Book> books = new ArrayList<>();
		books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
		books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
		books.add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
		books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
		books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
		books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
		books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

		// total number of books
		System.out.println(books.stream().count());
		
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		// we do not want to get a NullPointerException
		//nums.stream().reduce(Integer::max).ifPresent(System.out::println);
		
		// the maximum number of pages
		books.stream().map(Book::getPages).reduce(Integer::max).ifPresent(System.out::println);
	
		// we want to get the longest book
		Optional<Book> longest = books.stream().reduce((b1, b2) -> 
				b1.getPages() > b2.getPages() ? b1 : b2);
		
		longest.ifPresent(System.out::println);
		
		// sum all pages of all books
		// we have to transform Stream<Integer> into an integer stream "list of integers"
		IntStream s = books.stream().mapToInt(Book::getPages);
		Stream<Integer> ss = s.boxed();
		
		// [book1, book2, book3, book4] -> mapToInt -> [1, 1, 1, 1].sum() =  4
	}

}
