package Client;
import Server.Server;
import java.io.IOException;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Server.Server;
import Shared.MenuItem;
import Shared.Order;
import Shared.Seat;
import Utils.Token;

//Singleton class that contains all the functionality of the client
public class ClientEngine {
	private static ClientEngine me = null;
	private RestHandler restHandler;
	private ArrayList<MenuItem> cart;

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

	public MenuItem[] searchMenuItems(MenuItem[] items, String searchString) {
		ArrayList<MenuItem> ma = new ArrayList<>();
		for(MenuItem m : items) {
			if(m.name.contains(searchString)) {
				ma.add(m);
			}
			else if(m.description.contains(searchString)) {
				ma.add(m);
			}
		}
		MenuItem[] result = new MenuItem[ma.size()];
		ma.toArray(result);
		return result;
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
	public Order[] getAllOrders()
   {
      String[] order = restHandler.get("/order/list/").split("\n");

      ArrayList<Order> p = new ArrayList<>();
      ObjectMapper mapper = new ObjectMapper();

      try
      {
         for (String o : order)
         {
            p.add(mapper.readValue(o, Order.class));
         }
      }

      catch (IOException e)
      {

         e.printStackTrace();
         return null;
      }
      
      Order [] res = new Order[p.size()];
      p.toArray(res);
      return res;
   }
	public void addToCart(MenuItem i) {
		cart.add(i);
	}

	public MenuItem[] getCart() {
		MenuItem[] items = new MenuItem[cart.size()];
		int j = 0;
		for(MenuItem mi : cart) {
			items[j++] = mi;
		}
		return items;
	}

	public void emptyCart() {
		cart.clear();
	}
	
	public int getPrice()
	{
	   int p = 0;
	   for(MenuItem m : cart)
	   {
	     p+= m.price;
	   }
	   return p;
	}
	
	public boolean addMenuItem(MenuItem e) {
		String json;
		try {
			json = new ObjectMapper().writeValueAsString(e);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		boolean z = restHandler.set("/menu/add/", json.getBytes());

		return z;
	}
	public void removeMenuItem(int id) {

		String r = restHandler.get("/menu/remove/"+id);
	}
	
	public String getTableStatus(int id) {
		try {
		String g = restHandler.get("/table/status/"+id);
		if (g.equals("True")) {
		return "Occupied";
		}
		else if (g.equals("False")) {
			return "Available";
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "No information";
	}
	
	public void changeTableStatus(int id, boolean isOccupied) {
		String b;
		if (isOccupied) {
			b = "true";
		} else {
			b = "false";
		}
		restHandler.set("/table/status/" + id, b.getBytes());
	}
	
//	Make a method for each type of action that the client should support.
//	Each method performs the REST request on the server, interprets the
//	response and returns appropriate data objects.
//
//	Client and server are tied using a REST protocol

}
