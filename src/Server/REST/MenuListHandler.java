package Server.REST;

import Shared.MenuItem;
import Utils.Token;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;
import static Server.REST.Response.unauthorized;

public class MenuListHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		MenuItem[] menuItems = RestListener.server.getMenuItems();
		StringBuilder sb = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		for (MenuItem m : menuItems) {
			sb.append(mapper.writeValueAsString(m));
			sb.append('\n');
		}
		OK(httpExchange, sb.toString().getBytes());
	}
}
