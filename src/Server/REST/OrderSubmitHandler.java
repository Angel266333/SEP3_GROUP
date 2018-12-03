package Server.REST;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.internalError;
import java.io.IOException;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.Order;
import Shared.Seat;
import Utils.BodyReader;

public class OrderSubmitHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		switch(httpExchange.getRequestMethod()) {
		case "PUT":
			PUT(httpExchange);
		default:
			badRequest(httpExchange);
		}
	}

	private void PUT(HttpExchange httpExchange) throws IOException {
		URI uri = httpExchange.getRequestURI();
		byte[] respond = "".getBytes();
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			String body = BodyReader.readString(httpExchange.getRequestBody());
			Order order1 = Order.fromString(body);
			if (order1.id != id) {
				badRequest(httpExchange);
				return;// break to not continue executing the method.
			}
			int i = RestListener.server.addOrder(order1);// Indicates if the update was performed successfully.
			if (i == 0) {// If it succeeds.
				OK(httpExchange, "ok".getBytes());
			}
			if (i > 0) {// Return 1 if the database rejected the request.
				internalError(httpExchange);
				System.out.println(i);
			}
		}
		// If there is no ID, we go to the ArrayIndexOutOfBoundsException.
		catch (ArrayIndexOutOfBoundsException e) {
			badRequest(httpExchange);
		}
		// If this exception is triggered, then the system throws a bad request -
		// HTTP 400.
		catch (NumberFormatException o) {
			badRequest(httpExchange);
		}
}
}