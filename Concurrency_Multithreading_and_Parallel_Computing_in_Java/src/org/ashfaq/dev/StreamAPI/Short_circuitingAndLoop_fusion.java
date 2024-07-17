package org.ashfaq.dev.StreamAPI;

import java.util.List;
import java.util.stream.Collectors;

public class Short_circuitingAndLoop_fusion {
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

		// Loop Fusion

		List<String> collect = bookList.stream().filter(d -> {

			System.out.println("Filtering " + d.getTitle());
			return d.getPageNo() > 500;
		}).map(x -> {

			System.out.println("Mapping " + x.getTitle());
			return x.getTitle();
		}).limit(2).collect(Collectors.toList());

		// OP
//		Filtering The Great Gatsby
//		Filtering 1984
//		Filtering Sapiens
//		Filtering The Da Vinci Code
//		Filtering Meditations
//		Filtering To Kill a Mockingbird
//		Filtering Brave New World
//		Filtering The Art of War
//		Filtering The Girl with the Dragon Tattoo
//		Filtering Beyond Good and Evil
//		Filtering Pride and Prejudice
//		Filtering Dune
//		Filtering Guns, Germs, and Steel
//		Mapping Guns, Germs, and Steel
//		Filtering Gone Girl
//		Filtering The Republic

		// LOOP FUSION - diff operation are executed in same pass
//		So Filtering and Mapping are different but if we see o/p  they are happening at same time , 
		// Java is doing this same time not one by one even they are diff task, this is
		// optimization

		
		//Short-Circuting some operations dont need the whole stream to produce the result 
		//codes terminati ng after finding the 2 items when we use limit(2);
		
		
		// and Streams are Lazy operations i.e if we see the above code , and if we dont
		// collect it to a list the logic wont execute but
		// as soon as you add toList(); or any other terminal operation it will start to
		// execute

	}

}
