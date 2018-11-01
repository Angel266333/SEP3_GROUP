package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.jndi.toolkit.url.Uri;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import Shared.MenuItem;

public class RestListener
{
   Server server;
   HttpServer hServer;

   public RestListener(Server server) throws IOException
   {
      this.server = server;
      hServer = HttpServer.create(new InetSocketAddress(8001), 0);
      hServer.setExecutor(null);
      hServer.createContext("/menu/list/", menuListHandler);
      hServer.createContext("/", stdHandler);
      hServer.createContext("/seat/", seatHandler);
   }

   public HttpHandler stdHandler=new HttpHandler(){@Override public void handle(HttpExchange httpExchange)throws IOException{httpExchange.sendResponseHeaders(200,"Hello World".getBytes().length);httpExchange.getResponseBody().write("Hello World".getBytes());httpExchange.getResponseBody().close();}};

   public HttpHandler menuListHandler = new HttpHandler() {
		@Override
		public void handle(HttpExchange httpExchange) throws IOException {
			MenuItem[]menuItems=server.getMenuItems(null);StringBuilder sb=new StringBuilder();for(MenuItem m:menuItems){sb.append(m.toString());sb.append('\n');}String response=sb.toString();System.out.println(response);httpExchange.sendResponseHeaders(200,response.getBytes().length);OutputStream os=httpExchange.getResponseBody();os.write(response.getBytes());os.flush();os.close();}};

   public HttpHandler seatHandler = new HttpHandler()
   {
      
      @Override
      public void handle(HttpExchange arg0) throws IOException
      {
         URI uri = arg0.getRequestURI(); 
         try {
         String s = uri.getPath().split("/")[2];
         }
         
         catch(ArrayIndexOutOfBoundsException e)
         {
            String respond = "404 Not Found";
            arg0.sendResponseHeaders(404,respond.getBytes().length);
            OutputStream os = arg0.getResponseBody();
            os.write(respond.getBytes());
            os.flush();
            os.close();
         }
         
      }
   };

   public void start()
   {
      hServer.start();
   }

   public void stop()
   {
      hServer.stop(0);
   }
}
