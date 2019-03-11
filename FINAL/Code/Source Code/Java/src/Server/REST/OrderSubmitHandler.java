package Server.REST;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.internalError;
import static Server.REST.Response.unauthorized;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.Order;
import Shared.Seat;
import Utils.BodyReader;
import Utils.Token;

public class OrderSubmitHandler implements HttpHandler
{

   @Override
   public void handle(HttpExchange httpExchange) throws IOException
   {
      switch (httpExchange.getRequestMethod())
      {
         case "PUT":
            PUT(httpExchange);
         default:
            badRequest(httpExchange);
      }
   }

   private void PUT(HttpExchange httpExchange) throws IOException
   {
      Order order1;
      try
      {
         String body = BodyReader.readString(httpExchange.getRequestBody());
        
         ObjectMapper mapper = new ObjectMapper();
         order1 = mapper.readValue(body, Order.class);

         
         int i = RestListener.server.addOrder(order1);// Indicates if the update was performed successfully.
         if (i >= 0)
         {// If it succeeds.
        	 String response = "" + i;
        	 OK(httpExchange, response.getBytes());
         }
         else
         {// Return 1 if the database rejected the request.
            internalError(httpExchange);
         }
      }
      // If there is no ID, we go to the ArrayIndexOutOfBoundsException.
      catch (ArrayIndexOutOfBoundsException e)
      {
         badRequest(httpExchange);
      }
      // If this exception is triggered, then the system throws a bad request -
      // HTTP 400.
      catch (NumberFormatException o)
      {
         o.printStackTrace();
         badRequest(httpExchange);
      }
      catch(Exception k)
      {
         k.printStackTrace();
         badRequest(httpExchange);
      }
   }
}