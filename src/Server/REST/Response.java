package Server.REST;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
	public static void OK(HttpExchange httpExchange, byte[] respond) throws IOException {
		httpExchange.sendResponseHeaders(200, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public static void badRequest(HttpExchange httpExchange) throws IOException {
		byte[] respond = "400 Bad Request".getBytes();
		httpExchange.sendResponseHeaders(400, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public static void notFound(HttpExchange httpExchange) throws IOException {
		byte[] respond = "404 Not Found".getBytes();
		httpExchange.sendResponseHeaders(404, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public static void internalError(HttpExchange httpExchange) throws IOException {
		byte[] respond = "500 Internal Error".getBytes();
		httpExchange.sendResponseHeaders(500, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public static void unauthorized(HttpExchange httpExchange) throws IOException {
		byte[] respond = "401 Unauthorized request".getBytes();
		httpExchange.sendResponseHeaders(401, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

}
