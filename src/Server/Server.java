package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Database.IDatabase;
import Server.REST.RestListener;
import Shared.*;

public class Server {

	Registry registry;
	RestListener rs;
	IDatabase database;

	public Server() {
		try {
			registry = LocateRegistry.getRegistry("localhost", 1099);
			database = (IDatabase) registry.lookup("Database");
			rs = new RestListener(this);
			rs.start();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public MenuItem[] getMenuItems(Filter filter) {
		try {
			return database.search(filter);
		} catch (RemoteException re) {
			System.out.println(re.getMessage());
			re.printStackTrace();
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
			return 3; // If we get a remote exception (check others in ConcreteDatabase.update).
		}

	}

	public static void main(String[] args) {
		System.out.println("Type 'exit' to terminate server");
		Scanner keyboard = new Scanner(System.in);
		Server server = new Server();
		String usrInput;
		while (true) {
			usrInput = keyboard.nextLine();
			if (usrInput.equals("exit")) {
				break;
			}
		}
		server.rs.stop();
	}

}