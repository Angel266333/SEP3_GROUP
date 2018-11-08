package Server.REST;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Shared.Seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import static Server.REST.Response.OK;
import static Server.REST.Response.badRequest;
import static Server.REST.Response.notFound;

public class SeatHandler implements HttpHandler
{
   @Override
   public void handle(HttpExchange httpExchange) throws IOException
   {
      switch (httpExchange.getRequestMethod())
      {
         case "GET":
            GET(httpExchange);
            break;
         case "PUT":
            PUT(httpExchange);
            break;
         default:
            badRequest(httpExchange);
      }
   }

   // user story two
   public void GET(HttpExchange httpExchange) throws IOException
   {
      URI uri = httpExchange.getRequestURI();
      byte[] respond;
      try
      {
         String s = uri.getPath().split("/")[2];
         int id = Integer.parseInt(s);
         // load data
         respond = RestListener.server.getSeat(id).toString().getBytes();
         OK(httpExchange, respond);
      }
      catch (ArrayIndexOutOfBoundsException e)
      {
         notFound(httpExchange);
      }
      catch (NumberFormatException ne)
      {
         badRequest(httpExchange);
      }
   }

   public void PUT(HttpExchange httpExchange) throws IOException
   {
      System.out.println("vbnm");
      URI uri = httpExchange.getRequestURI();
      byte[] respond = "".getBytes();
      try
      {
         String s = uri.getPath().split("/")[2];
         int id = Integer.parseInt(s);
         BufferedReader reader = new BufferedReader(
               new InputStreamReader(httpExchange.getRequestBody(), "utf-8"));
         StringBuilder strBuilder = new StringBuilder();
         int b;
         // We read one character at a time then, we put it in a StringBuilder.
         // When the InputStream finishes reading, it returns -1. We read until
         // we reach this value.
         while ((b = reader.read()) != -1)
         {

            strBuilder.append((char) b);
         }

         reader.close();
         System.out.println(strBuilder.toString());
         OK(httpExchange, respond);
      }
      // If there is no ID, we go to the ArrayIndexOutOfBoundsException.
      catch (ArrayIndexOutOfBoundsException e)
      {

      }
      // If this exception is triggered, then the system throws a bad request -
      // HTTP 400.
      catch (NumberFormatException o)
      {

         badRequest(httpExchange);
      }

   }
}
