package Database;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Main {
	static IDatabase database;
	static ConcreteDatabase concreteDatabase;

	public static void main(String[] args) {
		try {
			Scanner keyboard = new Scanner(System.in);
			Registry registryDatabase = LocateRegistry.createRegistry(1099);
			concreteDatabase = new ConcreteDatabase();
			database = (IDatabase) UnicastRemoteObject.exportObject(concreteDatabase, 1099);
			registryDatabase.bind("Database", database);
			String usrInput;
			while (true) {
				usrInput = keyboard.nextLine();
				if (usrInput.equals("exit")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
