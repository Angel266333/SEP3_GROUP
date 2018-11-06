package Server.REST;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.notFound;

public class SeatHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String method = httpExchange.getRequestMethod();
		if(method.equals("GET")) {
			GET(httpExchange);
			return;
		}
		if(method.equals("PUT")) {
			PUT(httpExchange);
			return;
		}
		badRequest(httpExchange);
	}

	// user story two
	public void GET(HttpExchange httpExchange) throws IOException {
		URI uri = httpExchange.getRequestURI();
		byte[] respond;
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			// load data
			respond = RestListener.server.getSeat(id).toString().getBytes();
			OK(httpExchange, respond);
		} catch(ArrayIndexOutOfBoundsException e) {
			notFound(httpExchange);
		} catch(NumberFormatException ne) {
			badRequest(httpExchange);
		}
	}

	public void PUT(HttpExchange httpExchange) throws IOException {
		badRequest(httpExchange);
		//TODO Handle put
	}
}
