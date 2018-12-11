package Server.REST;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.unauthorized;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.MenuItem;
import Shared.Order;

public class OrderListHandler implements HttpHandler
{

   @Override

   public void handle(HttpExchange httpExchange) throws IOException
   {
      
      if(!httpExchange.getRequestMethod().equals("GET")) {
         badRequest(httpExchange);
         return;
      }
      if(!Utils.Token.validate(httpExchange)) {
         unauthorized(httpExchange);
         return;
      }
      Order[] orders = RestListener.server.getAllOrders();

      StringBuilder sb = new StringBuilder();
      ObjectMapper mapper = new ObjectMapper();
      for (Order m : orders) {
         sb.append(mapper.writeValueAsString(m));
         sb.append('\n');
      }
      System.out.println("DFD" + sb.toString());
      OK(httpExchange, sb.toString().getBytes());
   }

}
