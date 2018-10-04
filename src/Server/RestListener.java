package Server;

import Commons.Order;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RestListener {
	HttpServer server;
	Order testOrder;

	public RestListener() throws IOException {
		server = HttpServer.create(new InetSocketAddress(8001), 0);
		server.createContext("/orders/all/", getOrders);
		server.setExecutor(null);
		testOrder = new Order(new int[] {12, 14}, 100, LocalDateTime.ofEpochSecond(3423423400L, 0, ZoneOffset.UTC));
	}

	public void start() {
		server.start();
	}

	public void stop() {
		server.stop(0);
	}

	private HttpHandler getOrders = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			String response = testOrder.toString();
			httpExchange.sendResponseHeaders(200, response.length());
			OutputStream os = httpExchange.getResponseBody();
			os.write(response.getBytes());
			os.flush();
			os.close();
		}
	};
}
