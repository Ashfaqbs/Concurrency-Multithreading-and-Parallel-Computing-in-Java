package com.globalsoftwaresupport;

public class Book {

	private String author;
	private String title;
	private int pages;
	private Type type;
	
	public Book(String title, String author, int pages, Type type) {
		this.author = author;
		this.title = title;
		this.pages = pages;
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", title=" + title + ", pages=" + pages + ", type=" + type + "]";
	}
}
