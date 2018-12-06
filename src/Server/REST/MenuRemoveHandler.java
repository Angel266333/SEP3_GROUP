package Server.REST;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import static Server.REST.Response.*;

import Shared.ERROR;
import Shared.MenuItem;
import Utils.BodyReader;
import Utils.Token;

public class MenuRemoveHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		if(!Token.validate(httpExchange)) {
			unauthorized(httpExchange);
			return;
			}
		try {
		
			String s = httpExchange.getRequestURI().getPath().split("/")[3];
			int id = Integer.parseInt(s);
			int i = RestListener.server.removeMenuItem(id);
			if(i==0) {
				OK(httpExchange, "Success".getBytes());
			}
			else if(i==ERROR.DATABASE_ERROR){
				badRequest(httpExchange);
			}
			else if(i==ERROR.REMOTE_EXCEPTION){
				internalError(httpExchange);
			}
			else {
				badRequest(httpExchange);
				
			}
			
		}catch(Exception e)
		{
			badRequest(httpExchange);
		}
		
	}

}
