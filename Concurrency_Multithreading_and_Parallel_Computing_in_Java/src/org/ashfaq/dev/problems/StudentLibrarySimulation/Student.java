package org.ashfaq.dev.problems.StudentLibrarySimulation;

import java.util.Random;

public class Student implements Runnable {
	private int id;
	private Book[] books;

	private Random random;

	public Student(int id, Book[] books) {
		this.id = id;
		this.books = books;
		this.random = new Random();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book[] getBooks() {
		return books;
	}

	@Override
	public void run() {
		while (true) {
			int bookId = random.nextInt(Constants.NUM_OF_BOOKS);
			try {
				books[bookId].read(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	

}