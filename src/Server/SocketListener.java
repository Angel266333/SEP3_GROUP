package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import Server.REST.RestListener;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			socket = serverSocket.accept();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write("test");
			System.out.println("What ever by: KENNETH!");
			writer.newLine();
			while(!socket.isClosed()) {
				String s = reader.readLine();
				System.out.println(s);
				command(s);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void command(String command) throws IOException {
		String[] commands = command.split(" ");
		System.out.println(command);
		switch (commands[0]) {
		// The Web Server sends a string, for example â€œGETORDER 4â€� to the Server 
		// that in turn is followed by a response from the Server with the specific order data 
		// that was requested. If the ID is not specified, all orders are returned in the form of an array.
		case "GETORDER":
			int id = Integer.parseInt(commands[1]);
			Order order = server.getOrder(id);
		// The Web Server sends â€œGETMENUITEMSâ€� and in turn, the Server sends an array of menu items.
		case "GETMENUITEMS":
			MenuItem[] items = server.getMenuItems(null);
			for (MenuItem i : items) {
				response(i.toString());
			}
			writer.newLine();
			// The Web Server sends a command â€œSUBMITORDERâ€�  and in turn, the Server returns an OK response.
		case "SUBMITORDER":
		   System.out.println("sos");
		   while (true) {
			writer.write("test");
			System.out.println("True");
			writer.write('\n');
		   }
		}
	}
	
	public void response(String response) {
		try {
			writer.write(response);
			System.out.println(response);
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
	
	public static void main(String[] args)
   {
      Thread t1 = new Thread(new SocketListener(RestListener.server));
      t1.start();
   }
   
}