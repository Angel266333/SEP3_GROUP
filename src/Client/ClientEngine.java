package Client;

import Shared.MenuItem;
import Utils.Token;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

//Singleton class that contains all the functionality of the client
public class ClientEngine {
	private static ClientEngine me = null;
	private RestHandler restHandler;
	private ArrayList<Integer> cart;

	private ClientEngine() {
		restHandler = new RestHandler("https://localhost:8001");
		Token.readToken();
		restHandler.setToken(Token.getToken());
		cart = new ArrayList<>();
	}

	public static ClientEngine getInstance() {
		if (me == null) {
			me = new ClientEngine();
		}
		return me;
	}

	public MenuItem[] getMenu() {
		String[] ss = restHandler.get("/menu/list/").split("\n");

		ArrayList<MenuItem> al = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			for(String s : ss) {
				al.add(mapper.readValue(s, MenuItem.class));
			}
		}catch(IOException e) {
			return null;
		}

		MenuItem[] res = new MenuItem[al.size()];
		al.toArray(res);
		return res;
	}

	public void addToCart(int i) {
		cart.add(i);
	}

	public int[] getCart() {
		int[] items = new int[cart.size()];
		int j = 0;
		for(int it : cart) {
			items[j++] = it;
		}
		return items;
	}

	public void emptyCart() {
		cart.clear();
	}

//	Make a method for each type of action that the client should support.
//	Each method performs the REST request on the server, interprets the
//	response and returns appropriate data objects.
//
//	Client and server are tied using a REST protocol
}
