package Server;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import Database.IDatabase;
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
		} catch(Exception e) {
			System.out.println(e.getMessage());
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

	public static void main(String[] args) {
		System.out.println("Type 'exit' to terminate server");
		Scanner keyboard = new Scanner(System.in);
		Server server = new Server();
		String usrInput;
		while(true) {
			usrInput = keyboard.nextLine();
			if (usrInput.equals("exit")) {
				break;
			}
		}
		server.rs.stop();
	}
}