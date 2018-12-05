package Shared;

import java.io.Serializable;

public class Seat implements Serializable {
	// fields are public so you don't need getters and setters;
	public int id;
	public boolean isOccupied;

	public Seat() {

	}

	public Seat(int id, boolean isOccupied) {
		this.id = id;
		this.isOccupied = isOccupied;
	}
}
