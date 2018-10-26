package Database;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws RemoteException, ClassNotFoundException, SQLException, AlreadyBoundException {
		System.out.println("Type 'exit' to terminate database.");
		Scanner keyboard = new Scanner(System.in);
		Registry registryDatabase = LocateRegistry.createRegistry(1099);
		IDatabase database = (IDatabase) UnicastRemoteObject.exportObject(new ConcreteDatabase(), 1099);
		registryDatabase.bind("Database", database);
		String usrInput;
		while (true) {
			usrInput = keyboard.nextLine();
			if (usrInput.equals("exit")) {
				break;
			}
		}
	}
}
