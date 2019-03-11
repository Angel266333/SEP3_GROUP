package Shared;

import java.io.Serializable;

public class Seat implements Serializable {
	public int id;
	public boolean isOccupied;

	public Seat() {

	}

	public Seat(int id, boolean isOccupied) {
		this.id = id;
		this.isOccupied = isOccupied;
	}
}
