package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Shared.MenuItem;

public class RestListener {
	Server server;
	HttpServer hServer;

	public RestListener(Server server) throws IOException {
		this.server = server;
		hServer = HttpServer.create(new InetSocketAddress(8001), 0);
		hServer.setExecutor(null);
		hServer.createContext("/menu/list/", menuListHandler);
		hServer.createContext("/", stdHandler);
	}

	public HttpHandler stdHandler = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			httpExchange.sendResponseHeaders(200, "Hello World".getBytes().length);
			httpExchange.getResponseBody().write("Hello World".getBytes());
			httpExchange.getResponseBody().close();
		}
	};

	public HttpHandler menuListHandler = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			MenuItem[] menuItems = server.getMenuItems(null);
			StringBuilder sb = new StringBuilder();
			for (MenuItem m : menuItems) {
				sb.append(m.toString());
				sb.append('\n');
			}
			String response = sb.toString();
			System.out.println(response);
			httpExchange.sendResponseHeaders(200, response.getBytes().length);
			OutputStream os = httpExchange.getResponseBody();
			os.write(response.getBytes());
			os.flush();
			os.close();
		}
	};

	public void start() {
		hServer.start();
	}

	public void stop() {
		hServer.stop(0);
	}
}
