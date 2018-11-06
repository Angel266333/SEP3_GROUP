package Server.REST;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import Server.Server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Shared.MenuItem;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.notFound;

public class RestListener {
	public static Server server;
	HttpServer hServer;

	public RestListener(Server server) throws IOException {
		this.server = server;
		hServer = HttpServer.create(new InetSocketAddress(8001), 0);
		hServer.setExecutor(null);
		hServer.createContext("/menu/list/", new MenuListHandler());
		hServer.createContext("/", new StdHandler());
		hServer.createContext("/seat/", new SeatHandler());
	}

	public void start() {
		hServer.start();
	}

	public void stop() {
		hServer.stop(0);
	}
}
