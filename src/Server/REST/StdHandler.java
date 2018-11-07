package Server.REST;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;

public class StdHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		OK(httpExchange, "Hello World".getBytes());
	}
}
