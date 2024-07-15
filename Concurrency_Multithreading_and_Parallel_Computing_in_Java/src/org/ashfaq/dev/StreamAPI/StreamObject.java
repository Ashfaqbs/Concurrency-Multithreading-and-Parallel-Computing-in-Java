package org.ashfaq.dev.StreamAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

class Book {

	private String title;
	private String author;
	private Type type;
	private double price;
	private int pageNo;

	public Book(String title, String author, Type type, double price, int pageNo) {
		super();
		this.title = title;
		this.author = author;
		this.type = type;
		this.price = price;
		this.pageNo = pageNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public double getPrice() {
		return price;

	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", type=" + type + ", price=" + price + ", pageNo="
				+ pageNo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, pageNo, price, title, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && pageNo == other.pageNo
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price)
				&& Objects.equals(title, other.title) && type == other.type;
	}

}

enum Type {

	Novel, SCIENCE_FICTION, History, Thriller, Philosophy
}

public class StreamObject {

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

//		bookList.stream().filter(data -> data.getType().equals(Type.Novel)).forEach(System.out::println);

//		bookList.stream().sorted(Comparator.comparing(Book::getAuthor)).forEach(System.out::println);

//		bookList.stream().sorted(Comparator.comparing(Book::getTitle)).forEach(System.out::println);

//		bookList.stream().map(Book::getPrice).forEach(System.out::println);

		Map<Type, List<Book>> collect = bookList.stream().collect(Collectors.groupingBy(Book::getType));
//		System.out.println(collect);

		Map<Book, Long> collect2 = bookList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		System.out.println(collect2);

		bookList.stream().filter(d -> d.getPageNo() > 500).map(Book::getTitle).forEach(System.out::println);

	}

}
