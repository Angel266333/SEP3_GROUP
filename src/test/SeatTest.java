package test;

import Shared.Seat;
import org.junit.*;
import static org.junit.Assert.*;

public class SeatTest {
	private Seat seat;

	@Before
	public void setUp() {
		seat = new Seat();

		seat.id = 1;
		seat.isOccupied = true;
	}

	// Serialization does not corrupt the object.
	@Test
	public void test() {
		Seat seat2 = Seat.fromString(seat.toString());

		assertEquals(seat.toString(), seat2.toString());

		seat.id = 10;

		assertNotEquals(seat.toString(), seat2.toString());

	}

}