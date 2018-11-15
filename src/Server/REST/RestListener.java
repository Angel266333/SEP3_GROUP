package Server.REST;

import java.io.IOException;
import java.net.InetSocketAddress;
import Server.REST.OrderStatusHandler;

import com.sun.net.httpserver.HttpServer;

import Server.Server;

public class RestListener {
	public static Server server;
	// TODO - Convert HTTP to HTTPS.
	HttpServer hServer;

	public RestListener(Server server) throws IOException {
		this.server = server;
		hServer = HttpServer.create(new InetSocketAddress(8001), 0);
		hServer.setExecutor(null);
		hServer.createContext("/menu/list/", new MenuListHandler());
		hServer.createContext("/", new StdHandler());
		hServer.createContext("/seat/", new SeatHandler());
		hServer.createContext("/order/status/", new OrderStatusHandler());
	}
	//

	public void start() {
		hServer.start();
	}

	public void stop() {
		hServer.stop(0);
	}
}
