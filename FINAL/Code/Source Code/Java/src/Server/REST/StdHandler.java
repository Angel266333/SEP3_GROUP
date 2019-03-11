package Server.REST;

import Utils.Token;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.Charset;

import static Server.REST.Response.OK;

public class StdHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		if(!Token.validate(httpExchange)) {
			OK(httpExchange, "You are not authorized to use this server.".getBytes());
		}
		OK(httpExchange, "Hello Client. You are authorized to use this server.".getBytes());
	}
}
