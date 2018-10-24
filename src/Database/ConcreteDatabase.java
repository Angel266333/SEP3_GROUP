package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Shared.Filter;
import Shared.MenuItem;

public class ConcreteDatabase implements IDatabase
{
   public Connection connection;   
   
   
   public ConcreteDatabase() throws ClassNotFoundException, SQLException
   {
      Class.forName("org.postgresql.Driver");
      
      
      connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
   }
   
   
   @Override
   public int update(MenuItem item)
   {
      PreparedStatement statement;
      try
      {
         statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".ingredient");
         ResultSet rs = statement.executeQuery();
         rs.next();
         System.out.println(rs.getString(1));
        
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return 0;
   }

   @Override
   public int remove(MenuItem item)
   {
      return -1;
   }

   @Override
   public MenuItem[] search(Filter menuFilter)
   {
      // TODO Auto-generated method stub
      return null;
   }
   
   public static void main(String[] args) throws ClassNotFoundException, SQLException
   {
      ConcreteDatabase database = new ConcreteDatabase();
      database.update(null);
      
   } 
   }

 


