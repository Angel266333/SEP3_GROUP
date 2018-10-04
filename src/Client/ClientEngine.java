package Client;

import Commons.Order;

import java.util.ArrayList;

//Singleton class that contains all the functionality of the client
public class ClientEngine {
	private static ClientEngine me = null;
	private IRestHandler restParser;

	private ClientEngine() {
		restParser = new RestHandler("http://localhost:8001");
	}

	public static ClientEngine getInstance() {
		if(me == null) {
			me = new ClientEngine();
		}
		return me;
	}

	public Order[] getAllOrders() {
		String s[] = restParser.get("/orders/all/").split("\n");
		ArrayList<Order> ol = new ArrayList<>();
		for(String st : s) {
			ol.add(Order.fromString(st));
		}
		Order[] orders = new Order[ol.size()];
		ol.toArray(orders);
		return orders;
	}

//	Make a method for each type of action that the client should support.
//	Each method performs the REST http reuest on the server, interprets the
//	response and returns appropriate data objects.
//
//	Client and server are tied using a REST protocol

}
