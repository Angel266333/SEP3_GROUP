package Server.REST;

import Utils.Token;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.unauthorized;

public class StdHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		if(!Token.validate(httpExchange)) {
			unauthorized(httpExchange);
			return;
		}
		OK(httpExchange, "Hello World".getBytes());
	}
}
