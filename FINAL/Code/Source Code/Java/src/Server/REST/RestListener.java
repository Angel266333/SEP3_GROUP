package Server.REST;

import java.io.IOException;
import java.net.InetSocketAddress;
import Server.REST.OrderStatusHandler;
import Utils.HttpsServerCreator;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;

import Server.Server;

public class RestListener {
	public static Server server;
	HttpsServer hServer;

	public RestListener(Server server) throws IOException {
		this.server = server;
		hServer = HttpsServerCreator.create(new InetSocketAddress(8001));
		hServer.setExecutor(null);
		hServer.createContext("/menu/list/", new MenuListHandler());
		hServer.createContext("/", new StdHandler());
		hServer.createContext("/seat/", new SeatHandler());
		hServer.createContext("/order/", new OrderHandler());
		hServer.createContext("/order/list/", new OrderListHandler());
		hServer.createContext("/order/status/", new OrderStatusHandler());
		hServer.createContext("/order/submit/", new OrderSubmitHandler());
		hServer.createContext("/menu/add/", new MenuAddHandler());
		hServer.createContext("/menu/remove/", new MenuRemoveHandler());
		hServer.createContext("/menu/availability/", new AvailableItemHandler());
		hServer.createContext("/table/status/", new SeatHandler());

	}
	public void start() {
		hServer.start();
	}

	public void stop() {
		hServer.stop(0);
	}
}
