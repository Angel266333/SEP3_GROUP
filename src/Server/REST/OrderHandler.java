package Server.REST;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.notFound;
import static Server.REST.Response.unauthorized;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.Order;
import Utils.BodyReader;
import Utils.Token;

public class OrderHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		if(!Token.validate(httpExchange)) {
			unauthorized(httpExchange);
			return;
		}
		switch (httpExchange.getRequestMethod()) {
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

	public void GET(HttpExchange httpExchange) throws IOException {

		URI uri = httpExchange.getRequestURI();
		byte[] respond;
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			// Load data
			Order respondObject = RestListener.server.getOrder(id);
			ObjectMapper mapper = new ObjectMapper();
			String respondString = mapper.writeValueAsString(respondObject);
			respond = respondString.getBytes();

			OK(httpExchange, respond);
		} catch (ArrayIndexOutOfBoundsException q) {
			notFound(httpExchange);
		} catch (NumberFormatException x) {
			badRequest(httpExchange);
		}
	}

	public void PUT(HttpExchange httpExchange) {
		System.out.println("Test");
		URI uri = httpExchange.getRequestURI();
		byte[] respond = "".getBytes();
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			String body = BodyReader.readString(httpExchange.getRequestBody());
			ObjectMapper mapper = new ObjectMapper();
			Order order1 = mapper.readValue(body, Order.class);
			if (order1.id != id) {
				badRequest(httpExchange);
				return;// break to not continue executing the method.
			}
			// TODO
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}