package Server.REST;

import static Server.REST.Response.*;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.Seat;
import Utils.BodyReader;
import Utils.Token;

public class SeatHandler implements HttpHandler {
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

	// user story two
	public void GET(HttpExchange httpExchange) throws IOException {
		URI uri = httpExchange.getRequestURI();
		byte[] respond;
		try {
			String s = uri.getPath().split("/")[3];
			System.out.println(s);
			int id = Integer.parseInt(s);
			// load data
			Seat seat = RestListener.server.getSeat(id);
			if (seat.isOccupied) {
				respond = "True".getBytes();
			} else {
				respond = "False".getBytes();
			}
			OK(httpExchange, respond);
		} catch (ArrayIndexOutOfBoundsException e) {
			notFound(httpExchange);
		} catch (NumberFormatException ne) {
			badRequest(httpExchange);
		}
	}

	public void PUT(HttpExchange httpExchange) throws IOException {
		URI uri = httpExchange.getRequestURI();
		byte[] respond = "".getBytes();
		try {
			String s = uri.getPath().split("/")[3];
			int id = Integer.parseInt(s);
			String body = BodyReader.readString(httpExchange.getRequestBody());
			boolean bodyConvert;
			if (body.equals("True")) {
				bodyConvert = true;
			} else {
				bodyConvert = false;
			}
			int a = RestListener.server.updateTable(id, bodyConvert);
			if (a == 0) {
			OK(httpExchange, "OK".getBytes());
			} else {
				internalError(httpExchange);
			}
		} catch (Exception e) {
			badRequest(httpExchange);
			e.printStackTrace();
		}
	}
}