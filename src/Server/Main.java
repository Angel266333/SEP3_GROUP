package Server;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			RestListener server = new RestListener();
			Scanner scanner = new Scanner(System.in);
			String s;
			server.start();
			System.out.println("Server started");
			while(true) {
				s = scanner.nextLine();
				if(s.equals("exit")) {
					break;
				}
			}
			server.stop();
			System.out.println("Server stopped");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}