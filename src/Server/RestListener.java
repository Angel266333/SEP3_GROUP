package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Shared.MenuItem;

public class RestListener {
	Server server;
	HttpServer HTTPServer;

	public RestListener(Server server) throws IOException {

		this.server = server;

		HTTPServer = HttpServer.create(new InetSocketAddress(8001), 0);
		HTTPServer.setExecutor(null);
		HTTPServer.createContext("/menu/list/", menuListHandler);
	}

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
			httpExchange.sendResponseHeaders(200, response.getBytes().length);
			OutputStream os = httpExchange.getResponseBody();
			os.write(response.getBytes());
			os.flush();
			os.close();
		}
	};
}
