package com.globalsoftwaresupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App2 {

	public static void main(String[] args) {
		
		List<Book> books = new ArrayList<>();
		books.add(new Book("Being and Time", "Heidegger", 560, Type.PHILOSOPHY));
		books.add(new Book("The Trial", "Franz Kafka", 240, Type.NOVEL));
		books.add(new Book("Death on The Nile", "Agatha Christie", 370, Type.THRILLER));
		books.add(new Book("Ancient Greece", "Robert F.", 435, Type.HISTORY));
		books.add(new Book("Ancient Rome", "Robert F.", 860, Type.HISTORY));
		books.add(new Book("Death of Virgil", "Hermann Broch", 590, Type.NOVEL));
		books.add(new Book("The Stranger", "Albert Camus", 560, Type.NOVEL));

		// external iteration (collections)
		List<String> titles = new ArrayList<>();
		
		Iterator<Book> iterator = books.iterator();
		
		// inherently sequential
		// [item1, item2, item3, item4]
		// no parallelization
		while(iterator.hasNext()) {
			titles.add(iterator.next().getTitle());
		}
		
		// stream API - internal iteration
		// parallel quite easily
		List<String> titles2 = books.stream().map(Book::getTitle).collect(Collectors.toList());
		
		titles2.forEach(e -> System.out.println(e));
		
	}

}
