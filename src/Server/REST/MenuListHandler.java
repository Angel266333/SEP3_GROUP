package Server.REST;

import Shared.MenuItem;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

import static Server.REST.Response.OK;

public class MenuListHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		MenuItem[] menuItems = RestListener.server.getMenuItems(null);
		StringBuilder sb = new StringBuilder();
		for (MenuItem m : menuItems) {
			sb.append(m.toString());
			sb.append('\n');
		}
		OK(httpExchange, sb.toString().getBytes());
	}
}
