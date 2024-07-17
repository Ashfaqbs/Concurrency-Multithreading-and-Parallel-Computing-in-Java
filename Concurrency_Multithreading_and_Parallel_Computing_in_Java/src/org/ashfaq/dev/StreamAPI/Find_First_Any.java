package org.ashfaq.dev.StreamAPI;

import java.util.List;

public class Find_First_Any {

	public static void main(String[] args) {
		List<Book> bookList = new java.util.ArrayList<>();
		bookList.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", Type.Novel, 10.99, 180));
		bookList.add(new Book("1984", "George Orwell", Type.SCIENCE_FICTION, 8.99, 328));
		bookList.add(new Book("Sapiens", "Yuval Noah Harari", Type.History, 12.99, 443));
		bookList.add(new Book("The Da Vinci Code", "Dan Brown", Type.Thriller, 9.99, 489));
		bookList.add(new Book("Meditations", "Marcus Aurelius", Type.Philosophy, 7.99, 304));
		bookList.add(new Book("To Kill a Mockingbird", "Harper Lee", Type.Novel, 14.99, 281));
		bookList.add(new Book("Brave New World", "Aldous Huxley", Type.SCIENCE_FICTION, 9.49, 311));
		bookList.add(new Book("The Art of War", "Sun Tzu", Type.History, 6.99, 273));
		bookList.add(new Book("The Girl with the Dragon Tattoo", "Stieg Larsson", Type.Thriller, 11.99, 465));
		bookList.add(new Book("Beyond Good and Evil", "Friedrich Nietzsche", Type.Philosophy, 8.49, 240));
		bookList.add(new Book("Pride and Prejudice", "Jane Austen", Type.Novel, 7.99, 279));

		// findFirst() sequential and findAny() parallel
		bookList.stream().findFirst().ifPresent(System.out::println);
		bookList.stream().findAny().ifPresent(System.out::println);
	}

}
