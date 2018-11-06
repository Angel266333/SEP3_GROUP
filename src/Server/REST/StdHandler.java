package Server.REST;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class StdHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		httpExchange.sendResponseHeaders(200, "Hello World".getBytes().length);
		httpExchange.getResponseBody().write("Hello World".getBytes());
		httpExchange.getResponseBody().close();
	}
}
