package org.ashfaq.dev.StreamAPI;

import java.io.Serializable;

public class Person  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;

	public Person(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
