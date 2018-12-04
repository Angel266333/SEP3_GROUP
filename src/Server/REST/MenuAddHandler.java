package Server.REST;

import static Server.REST.Response.*;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.ERROR;
import Shared.MenuItem;
import Utils.BodyReader;

public class MenuAddHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		String body = BodyReader.readString(httpExchange.getRequestBody()); //getting json data
		ObjectMapper mapper = new ObjectMapper(); //used for json conversion
		try {
			MenuItem menuItem = mapper.readValue(body, MenuItem.class);
			int i = RestListener.server.addMenuItem(menuItem);//converting json data into an object
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
