package Tests;

import Shared.MenuItem;
import Shared.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;

public class ClientEngine {
	String[] ing1 = new String[] {
			"flour",
			"sugar",
			"water",
			"salt",
			"yeast",
			"pepper"
	};

	String[] ing2 = new String[] {
			"flour",
			"yeast",
			"water"
	};

	MenuItem[] testItems = new MenuItem[] {
			new MenuItem(
					1,
					"cheese",
					"yellow",
					ing1,
					true,
					50
			),
			new MenuItem(
					2,
					"sausage",
					"green",
					ing1,
					true,
					150
			),
			new MenuItem(
					5,
					"something",
					"black",
					ing2,
					true,
					200

			),
			new MenuItem(
					8,
					"thing",
					"white",
					ing2,
					false,
					300
			),
			new MenuItem(
					10,
					"asdf",
					"pink",
					ing1,
					false,
					100
			),
			new MenuItem(
					11,
					"qwerty",
					"keyboard",
					ing2,
					true,
					130
			),
			new MenuItem(
					33,
					"cow",
					"animal",
					ing1,
					true,
					230
			),
			new MenuItem(
					7,
					"pizza",
					"blue",
					new String[0],
					true,
					100
			)
	};

	@Test
	public void searchMenuItems() {
		//Search for description
		MenuItem[] result = Client.ClientEngine.getInstance().searchMenuItems(testItems, "keyboard");
		assertEquals(1, result.length);
		assertEquals(testItems[5], result[0]);

		//Search with varying case
		result = Client.ClientEngine.getInstance().searchMenuItems(testItems, "iMa");
		assertEquals(1, result.length);
		assertEquals(testItems[6], result[0]);

		//Search with fragment
		result = Client.ClientEngine.getInstance().searchMenuItems(testItems, "ee");
		assertEquals(2, result.length);
		boolean b = false;
		if(testItems[1].equals(result[0])) {
			b = testItems[0].equals(result[1]);
		}
		else if(testItems[0].equals(result[0])) {
			b = testItems[1].equals(result[1]);
		}
		assertTrue(b);

		//Search ingredient with varying case
		result = Client.ClientEngine.getInstance().searchMenuItems(testItems, "SaLt");
		assertEquals(4, result.length);

		//Search invalid term close to valid term
		result = Client.ClientEngine.getInstance().searchMenuItems(testItems, "pizzxa");
		assertEquals(0, result.length);

		//Search name
		result = Client.ClientEngine.getInstance().searchMenuItems(testItems, "cow");
		assertEquals(1, result.length);
		assertEquals(testItems[6], result[0]);
	}

	@Test
	public void getMenu() {
		MenuItem[] items = Client.ClientEngine.getInstance().getMenu();
		assertTrue(items != null);

		//Check that the items have unique ids
		boolean b = true;
		HashSet<Integer> ids = new HashSet<>();
		for(MenuItem m : items) {
			if(ids.contains(m.id)) {
				b = false;
				break;
			}
			ids.add(m.id);
		}
		assertTrue(b);
	}

	@Test
	public void getAllOrders() {
		Order[] orders = Client.ClientEngine.getInstance().getAllOrders();
		assertTrue(orders != null);

		boolean b = true;
		HashSet<Integer> ids = new HashSet<>();
		for(Order o : orders) {
			if(ids.contains(o.id)) {
				b = false;
				break;
			}
			ids.add(o.id);
		}
		assertTrue(b);
	}

	@Test
	public void changeOrderStatus() {
		Order[] orders = Client.ClientEngine.getInstance().getAllOrders();
		String status = orders[0].status;
		int id = orders[0].id;
		if(status.equals(Order.STATUS.PENDING)) {
			Client.ClientEngine.getInstance().changeOrderStatus(id, Order.STATUS.DELIVERED);
		}
		else {
			Client.ClientEngine.getInstance().changeOrderStatus(id, Order.STATUS.PENDING);
		}
		String nStatus = status;
		orders = Client.ClientEngine.getInstance().getAllOrders();
		for(Order o : orders) {
			if(o.id == id) {
				nStatus = o.status;
			}
		}

		assertNotEquals(status, nStatus);
	}
}
