package Server.REST;

import java.io.IOException;

import org.apache.http.entity.mime.content.StringBody;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Utils.BodyReader;
import Utils.Token;

import static Server.REST.Response.*;

public class AvailableItemHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		if(!Token.validate(httpExchange)) {
			unauthorized(httpExchange);
			return;
		}
		if(!httpExchange.getRequestMethod().equals("PUT")) {
			badRequest(httpExchange);
		}
		String s = httpExchange.getRequestURI().getPath().split("/")[3];
		int id = Integer.parseInt(s);
		String body = BodyReader.readString(httpExchange.getRequestBody());
		boolean b;
		if(body.equals("true")) {
			b = true;
		}
		else {
			b = false;
		}
			
		
	
	

	}
}

