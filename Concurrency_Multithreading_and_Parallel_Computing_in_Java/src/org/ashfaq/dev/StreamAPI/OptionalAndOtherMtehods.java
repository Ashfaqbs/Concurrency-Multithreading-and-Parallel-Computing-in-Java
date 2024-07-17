package org.ashfaq.dev.StreamAPI;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class OptionalAndOtherMtehods {

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
		bookList.add(new Book("Dune", "Frank Herbert", Type.SCIENCE_FICTION, 10.99, 412));
		bookList.add(new Book("Guns, Germs, and Steel", "Jared Diamond", Type.History, 15.99, 528));
		bookList.add(new Book("Gone Girl", "Gillian Flynn", Type.Thriller, 9.99, 432));
		bookList.add(new Book("The Republic", "Plato", Type.Philosophy, 10.49, 300));

		long bookCount = bookList.stream().count();

		Optional<Integer> maxPageNO = bookList.stream().map(Book::getPageNo).reduce(Integer::max);
		Optional<Integer> sumPageNO = bookList.stream().map(Book::getPageNo).reduce(Integer::sum);

		// Optional performs null checks , here get will give the data or
		// runtimeExeception NosuchElement present
		System.out.println(sumPageNO.get());

		sumPageNO.ifPresent(System.out::println); // advantage of using Optional

//		 OR
		OptionalLong max = bookList.stream().mapToLong(Book::getPageNo).max();

		long sum = bookList.stream().mapToLong(Book::getPageNo).sum();

		// optional float , double , int
		OptionalInt max2 = bookList.stream().mapToInt(Book::getPageNo).max();
//	......

		
	}

}
