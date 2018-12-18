package Server;

import Shared.MenuItem;
import Shared.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class SocketSession implements Runnable {
	Socket socket;
	BufferedWriter writer;

	public SocketSession(Socket socket) {
		this.socket = socket;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch(IOException e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
	}

	@Override
	public void run() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			int c;
			while(true) {
				c = reader.read();
				if(c < 1) {
					break;
				}
				baos.write(c);
			}
			handle(new String(baos.toByteArray(), Charset.forName("UTF-8")));
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}

	private void handle(String s) {
		if(s.equals("GETMENUITEMS")) {
			MenuItem[] items = SocketListener.server.getMenuItems();
			StringBuilder sb = new StringBuilder();
			ObjectMapper mapper = new ObjectMapper();
			for(MenuItem i : items) {
				try {
					sb.append(mapper.writeValueAsString(i));
					sb.append('|');
				} catch(JsonProcessingException e) {
					e.printStackTrace();
				}
			}

			String r = sb.toString();
			reply(r.substring(0, r.length() - 1));
		}
		else if(s.length() > 11 && s.substring(0, 12).equals("SUBMITORDER|")) {
			try {
				Order o = new ObjectMapper().readValue(s.split("\\|")[1], Order.class);
				int oid = SocketListener.server.addOrder(o);
				reply("" + oid);
			} catch(IOException e) {
				e.printStackTrace();
				reply("-1");
			}
		}
		else {
			reply("Hello world");
		}
	}

	private void reply(String s) {
		try {
			writer.write(s);
			writer.write(0x00);
			writer.flush();
			writer.close();
			socket.close();
		} catch(IOException e) {
			try {
				socket.close();
			} catch(IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
