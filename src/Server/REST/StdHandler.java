package Server.REST;

import Utils.Token;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.Charset;

import static Server.REST.Response.OK;

public class StdHandler implements HttpHandler {
	byte[] hello;

	public StdHandler() {
		try {
			FileReader fr = new FileReader(new File(getClass().getResource("hello.html").toURI()));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int c;
			while(true) {
				c = fr.read();
				if(c == -1) {
					break;
				}
				baos.write(c);
			}
			hello = baos.toByteArray();
		} catch(Exception e) {
			hello = "Unauthorized.".getBytes();
		}
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		if(!Token.validate(httpExchange)) {
			OK(httpExchange, hello);
		}
		OK(httpExchange, "Hello Client. You are authorized to use this server.".getBytes());
	}
}
