package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Shared.MenuItem;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

public class RestListener {
	Server server;
	HttpServer hServer;

	public RestListener(Server server) throws IOException {
		this.server = server;
		hServer = HttpServer.create(new InetSocketAddress(8001), 0);
		hServer.setExecutor(null);
		hServer.createContext("/menu/list/", menuListHandler);
		hServer.createContext("/", stdHandler);
		hServer.createContext("/seat/", seatHandlerMain);
	}

	public HttpHandler stdHandler = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			httpExchange.sendResponseHeaders(200, "Hello World".getBytes().length);
			httpExchange.getResponseBody().write("Hello World".getBytes());
			httpExchange.getResponseBody().close();
		}
	};

	public HttpHandler menuListHandler = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			MenuItem[] menuItems = server.getMenuItems(null);
			StringBuilder sb = new StringBuilder();
			for(MenuItem m : menuItems) {
				sb.append(m.toString());
				sb.append('\n');
			}
			OK(httpExchange, sb.toString().getBytes());
		}
	};

	public HttpHandler seatHandlerMain = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			String method = httpExchange.getRequestMethod();
			if(method.equals("GET")) {
				seatHandlerGET(httpExchange);
				return;
			}
			if(method.equals("PUT")) {
				seatHandlerPUT(httpExchange);
				return;
			}
			badRequest(httpExchange);
		}
	};

	// user story two
	public void seatHandlerGET(HttpExchange httpExchange) throws IOException {
		URI uri = httpExchange.getRequestURI();
		byte[] respond;
		try {
			String s = uri.getPath().split("/")[2];
			int id = Integer.parseInt(s);
			// load data
			respond = server.getSeat(id).toString().getBytes();
			OK(httpExchange, respond);
		} catch(ArrayIndexOutOfBoundsException e) {
			notFound(httpExchange);
		} catch(NumberFormatException ne) {
			badRequest(httpExchange);
		}
	}

	public void seatHandlerPUT(HttpExchange httpExchange) throws IOException {
		badRequest(httpExchange);
	}

	public void OK(HttpExchange httpExchange, byte[] respond) throws IOException {
		httpExchange.sendResponseHeaders(200, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public void badRequest(HttpExchange httpExchange) throws IOException {
		byte[] respond = "400 Bad Request".getBytes();
		httpExchange.sendResponseHeaders(400, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public void notFound(HttpExchange httpExchange) throws IOException {
		byte[] respond = "404 Not Found".getBytes();
		httpExchange.sendResponseHeaders(404, respond.length);
		OutputStream os = httpExchange.getResponseBody();
		os.write(respond);
		os.flush();
		os.close();
	}

	public void start() {
		hServer.start();
	}

	public void stop() {
		hServer.stop(0);
	}
}
