package Client;

import Shared.MenuItem;

import java.util.ArrayList;

//Singleton class that contains all the functionality of the client
public class ClientEngine {
	private static ClientEngine me = null;
	private IRestHandler restHandler;

	private ClientEngine() {
		restHandler = new RestHandler("http://localhost:8001");
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
		for (String s : ss) {
			al.add(MenuItem.fromString(s));
		}

		MenuItem[] res = new MenuItem[al.size()];
		al.toArray(res);
		return res;
	}

//	Make a method for each type of action that the client should support.
//	Each method performs the REST request on the server, interprets the
//	response and returns appropriate data objects.
//
//	Client and server are tied using a REST protocol
}
