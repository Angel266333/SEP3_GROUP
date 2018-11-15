package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Shared.MenuItem;
import Shared.Order;

public class OrderTest {
	private Order order;

	@Before
	public void setUp() {
		order = new Order();

		order.id = 46;
		order.status = "Finalized";
		order.comment = "Without extra sauce";
		order.receipt = "11/11/18 -- TOTAL: 286 DKK -- Receipt Number: ABBA69DDR1BCD";
		order.idTable = 4;
	}

	// Serialization does not corrupt the object.
	@Test
	public void test() {
		Order order2 = Order.fromString(order.toString());

		assertEquals(order.toString(), order2.toString());

		order.id = 42;

		assertNotEquals(order.toString(), order2.toString());

	}

}
