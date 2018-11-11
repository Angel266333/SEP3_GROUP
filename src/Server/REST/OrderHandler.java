package Server.REST;

import Shared.Order;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;

public class OrderHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		//WIP
}
}