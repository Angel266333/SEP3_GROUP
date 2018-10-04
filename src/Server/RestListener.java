package Server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RestListener {
	HttpServer server;

	public RestListener() throws IOException {
		server = HttpServer.create(new InetSocketAddress(8001), 0);
		server.setExecutor(null);
	}

	public void start() {
		server.start();
	}

	public void stop() {
		server.stop(0);
	}

}
