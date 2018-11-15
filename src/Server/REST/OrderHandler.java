package Server.REST;

import Shared.Order;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;

public class OrderHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		switch(httpExchange.getRequestMethod()) {
			case "GET":
				GET(httpExchange);
				break;
			case "PUT":
				PUT(httpExchange);
				break;
			default:
				badRequest(httpExchange);
		}
	}

	public void GET(HttpExchange httpExchange) {

	}

	public void PUT(HttpExchange httpExchange) {

	}
}