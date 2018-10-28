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

	public Server() throws NotBoundException, IOException {
		registry = LocateRegistry.getRegistry("localhost", 1099);
		database = (IDatabase) registry.lookup("Database");
		rs = new RestListener(this);
		rs.start();
	}

	public MenuItem[] getMenuItems(Filter filter) throws RemoteException {
		return database.search(filter);
	}

	public static void main(String[] args) throws NotBoundException, IOException {
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