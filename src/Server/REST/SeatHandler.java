package Server.REST;

import static Server.REST.Response.*;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.Seat;
import Utils.BodyReader;

public class SeatHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
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

	// user story two
	public void GET(HttpExchange httpExchange) throws IOException {
		URI uri = httpExchange.getRequestURI();
		byte[] respond;
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			// load data
			ObjectMapper mapper = new ObjectMapper();
			String respondString = mapper.writeValueAsString(RestListener.server.getSeat(id));
			respond = respondString.getBytes();
			OK(httpExchange, respond);
		} catch (ArrayIndexOutOfBoundsException e) {
			notFound(httpExchange);
		} catch (NumberFormatException ne) {
			badRequest(httpExchange);
		}
	}

	public void PUT(HttpExchange httpExchange) throws IOException {
		System.out.println("vbnm");
		URI uri = httpExchange.getRequestURI();
		byte[] respond = "".getBytes();
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			String body = BodyReader.readString(httpExchange.getRequestBody());
			ObjectMapper mapper = new ObjectMapper();
			Seat seat1 = mapper.readValue(body, Seat.class);
			if (seat1.id != id) {
				badRequest(httpExchange);
				return;// break to not continue executing the method.
			}
			int i = RestListener.server.updateSeat(seat1);// Indicates if the update was performed successfully.
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
