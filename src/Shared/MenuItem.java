package Shared;

import java.io.Serializable;

public class MenuItem implements Serializable {

	// private static final long serialVersionUID = 5362694205303093435L;

	public int id;
	public String name;
	public String description;
	public boolean isAvailable;
	public int price;

	public MenuItem() {
	}

	public MenuItem(int id, String name, String description, boolean isAvailable, int price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isAvailable = isAvailable;
		this.price = price;
	}
	
	
}
