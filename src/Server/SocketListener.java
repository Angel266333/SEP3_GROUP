package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener implements Runnable {
	int port;
	static Server server;
	private ServerSocket ss;

	public SocketListener(int port, Server server) {
		this.port = port;
		SocketListener.server = server;
		try {
			ss = new ServerSocket(port);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			try {
				Socket socket = ss.accept();
				new Thread(new SocketSession(socket)).start();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		Thread.currentThread().interrupt();
		try {
			ss.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
