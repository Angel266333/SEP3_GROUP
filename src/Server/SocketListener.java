package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import Shared.MenuItem;
import Shared.Order;

public class SocketListener implements Runnable {
	
	ServerSocket serverSocket;
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	Server server;

	public SocketListener(Server server) {
		try {
			this.server = server;
			serverSocket = new ServerSocket(8002);
			socket = serverSocket.accept();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while (true) {
			try {
				String s = reader.readLine();
				command(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void command(String command) {
		String[] commands = command.split(" ");
		switch (commands[0]) {
		// The Web Server sends a string, for example “GETORDER 4” to the Server 
		// that in turn is followed by a response from the Server with the specific order data 
		// that was requested. If the ID is not specified, all orders are returned in the form of an array.
		case "GETORDER":
			int id = Integer.parseInt(commands[1]);
			Order order = server.getOrder(id);
		// The Web Server sends “GETMENUITEMS” and in turn, the Server sends an array of menu items.
		case "GETMENUITEMS":
			MenuItem[] items = server.getMenuItems(null);
			for (MenuItem i : items) {
				response(i.toString());
			}
			response("\n");
		// The Web Server sends a command “SUBMITORDER”  and in turn, the Server returns an OK response.
		case "SUBMITORDER":
			//TODO
		}
	}
	
	public void response(String response) {
		try {
			writer.write(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopOperation() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}