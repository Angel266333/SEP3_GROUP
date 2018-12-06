package Server.REST;

import Utils.BodyReader;
import Utils.Token;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.internalError;
import static Server.REST.Response.unauthorized;

public class OrderStatusHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		if(!Token.validate(httpExchange)) {
			unauthorized(httpExchange);
			return;
		}
		switch(httpExchange.getRequestMethod()) {
			case "PUT":
				PUT(httpExchange);
			default:
				badRequest(httpExchange);
		}
	}

	public void PUT(HttpExchange httpExchange) throws IOException {
		try {
			String s = httpExchange.getRequestURI().getPath().split("/")[3];
			int id = Integer.parseInt(s);

			String status = BodyReader.readString(httpExchange.getRequestBody());
			int i = RestListener.server.updateOrderStatus(id, status);
			//TODO Make accurate responses
			if(i == 1) { // check what the numbers mean in Shared.ERROR
				internalError(httpExchange);
				return;
			}
			if(i == 2) { // check what the numbers mean in Shared.ERROR
				internalError(httpExchange);
				return;
			}
			if(i == 3) { // check what the numbers mean in Shared.ERROR
				badRequest(httpExchange);
				return;
			}
			OK(httpExchange, "Status updated successfully".getBytes());
		} catch(Exception e) {
			badRequest(httpExchange);
		}
	}
}
