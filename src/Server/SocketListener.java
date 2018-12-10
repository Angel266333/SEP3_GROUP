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
import com.fasterxml.jackson.databind.ObjectMapper;

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
		while(!Thread.currentThread().isInterrupted()) {
			try {
				socket = serverSocket.accept();
				if(socket == null) {
					break;
				}
				System.out.println("Socket connection established");
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				while(!socket.isClosed()) {
					String s = reader.readLine();
					System.out.println(s);
					command(s);
				}
			} catch(IOException e) {
				System.out.println("Socket connection lost.");
			}
		}
	}

	public void command(String command) throws IOException {
		String[] commands = command.split("\\|");//Splitting by space disturbs the JSON
		System.out.println(commands.length);
		if(commands.length < 2) {
			response("<" + commands[0] + ">");
			response("Invalid request");
			response("</" + commands[0] + ">");
			writer.flush();
		}
		else if(commands[1].equals("GETMENUITEMS")) {
			response("<" + commands[0] + ">");
			MenuItem[] items = server.getMenuItems(null);
			System.out.println(items.length);
			ObjectMapper mapper = new ObjectMapper();
			for(MenuItem m : items) {
				response(mapper.writeValueAsString(m));
			}
			response("</" + commands[0] + ">");
			writer.flush();
		}
		else if(commands[1].equals("SUBMITORDER")) {
			Order o = new ObjectMapper().readValue(commands[2], Order.class);
			//TODO handle the order
		}
		else {
			response("Invalid command");
			writer.flush();
		}
	}
	
	public void response(String response) {
		try {
			writer.write(response);
			writer.write('\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopOperation() {
		try {
			serverSocket.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}