package test;

import Shared.MenuItem;
import org.junit.*;
import static org.junit.Assert.*;

public class MenuItemTest {
	private MenuItem item;

	@Before
	public void setUp() {
		item = new MenuItem();

		item.id = 12;
		item.description = "food";
		item.name = "tomato";
		item.isAvailable = true;
	}

	// Serialization does not corrupt the object.
	@Test
	public void test() {
		MenuItem item2 = MenuItem.fromString(item.toString());

		assertEquals(item.toString(), item2.toString());

		item.id = 10;

		assertNotEquals(item.toString(), item2.toString());

	}

}
