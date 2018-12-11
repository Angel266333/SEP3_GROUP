package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Database.IDatabase;
import Server.REST.RestListener;
import Shared.*;
import Utils.Token;

public class Server {

	Registry registry;
	RestListener rs;
	IDatabase database;
	Thread socketListenerThread;
	SocketListener socketListener;

	public Server() {
		try {
			registry = LocateRegistry.getRegistry("localhost", 1099);
			database = (IDatabase) registry.lookup("Database");
			rs = new RestListener(this);
			rs.start();
			socketListener = new SocketListener(this);
			socketListenerThread = new Thread(socketListener);
			socketListenerThread.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int addOrder(Order order) {
		try {
			int i = database.placeOrder(order);
			return i;
		} catch(RemoteException e) {
			return ERROR.REMOTE_EXCEPTION;
		}
	}

	public int addMenuItem(MenuItem menuItem) {
		try {
			int i = database.addMenuItem(menuItem);
			return i;
		} catch (RemoteException e) {
			e.printStackTrace();
			return ERROR.REMOTE_EXCEPTION;
		}
	}
	public int removeMenuItem(int id) {
		try {
			int i = database.removeMenuItem(id);
			return i;
		} catch (RemoteException e) {
			e.printStackTrace();
			return ERROR.REMOTE_EXCEPTION;
		}
	}

	public MenuItem[] getMenuItems(Filter filter) {
		try {
			return database.search(filter);
		} catch(RemoteException re) {
			System.out.println(re.getMessage());
			re.printStackTrace();
			return null;
		}
	}
	public Order [] getAllOrders()
	{
	   try
      {
         return database.getAllOrders();
      }
      catch (RemoteException e)
      {
         e.printStackTrace();
         return null;
      }
	}
	public Order getOrder(int id) {
		try {
			return database.getOrder(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateOrderStatus(int id, String status) {
		if (Order.STATUS.forName(status) == null) {
			return ERROR.INVALID_DATA_VALUE;
		}
		try {
			int i = database.updateOrderStatus(id, status);
			return i;

		} catch (RemoteException e) {

			return ERROR.REMOTE_EXCEPTION;
		}
	}

	public Seat getSeat(int id) {
		try {
			return database.getSeat(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int updateSeat(Seat seat) {
		try {
			return database.update(seat);
		} catch (RemoteException e) {
			return ERROR.REMOTE_EXCEPTION; // If we get a remote exception (check others in ConcreteDatabase.update).
		}

	}
	public int setAvailability(int id, boolean available) {
		int a;
		try {
			a = database.setAvailability(id, available);

			return a;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR.REMOTE_EXCEPTION;
		}
		
	}

	public static void main(String[] args) {
		System.out.println("Type 'exit' to terminate server");
		Scanner keyboard = new Scanner(System.in);
		Server server = new Server();
		Token.readToken();
		String usrInput;
		while (true) {
			usrInput = keyboard.nextLine();
			if (usrInput.equals("exit")) {
				break;
			}
			if(usrInput.equals("token print")) {
				System.out.println(Token.getToken());
			}
			else if(usrInput.equals("token generate")) {
				System.out.println("Generating new token...");
				Token.generateToken();
				System.out.println("New token is: " + Token.getToken());
			}
		}
		server.rs.stop();
		server.socketListenerThread.interrupt();
		server.socketListener.stopOperation();
	}
	

}

