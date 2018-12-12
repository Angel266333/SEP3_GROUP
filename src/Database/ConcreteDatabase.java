package Database;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Shared.ERROR;
import Shared.Filter;
import Shared.MenuItem;
import Shared.Order;
import Shared.Seat;

public class ConcreteDatabase implements IDatabase {
	public Connection connection;

	public ConcreteDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
	}
	

	@Override
	public int update(MenuItem item) {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".ingredient");
			ResultSet rs = statement.executeQuery();
			rs.next();
			System.out.println(rs.getString(1));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int remove(MenuItem item) {
		return -1;
	}

	@Override
	public MenuItem[] search(Filter menuFilter) {
		System.out.println("Running");
		if (menuFilter == null) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".menuitem");
				ArrayList<MenuItem> menuItems = new ArrayList<>();
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					MenuItem item = new MenuItem(rs.getInt(1), rs.getString(2), rs.getString(3),new String[]{}, rs.getBoolean(4), rs.getInt(5));
					//TODO Get the actual ingredients
					menuItems.add(item);
				}
				MenuItem[] menuItemsArray = new MenuItem[menuItems.size()];
				menuItems.toArray(menuItemsArray);
				return menuItemsArray;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
//Main method used only for testing:
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConcreteDatabase database = new ConcreteDatabase();
		MenuItem[] arrayTest = database.search(null);
		for (MenuItem m : arrayTest) {
			System.out.println(m);
		}
	}
   @Override
	public Order [] getAllOrders()
	{
	   PreparedStatement statement;
	   try
      {
         statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".orders");
         ArrayList<Order> orders = new ArrayList<>();
         ResultSet rs = statement.executeQuery();
         statement = connection.prepareStatement("select * from \"Kartofil\".menuitem_order");
         ResultSet rel = statement.executeQuery();
         ArrayList<int[]> relation = new ArrayList<>();
         while(rel.next()) {
            int m = rel.getInt(1);
            int o = rel.getInt(2);
            relation.add(new int[] {m, o});
         }
         while (rs.next()) {
            Order order = new Order();
            order.id = rs.getInt(1);
            order.idTable = rs.getInt(2);
            order.status = rs.getString(3);
            order.comment = rs.getString(4);
            order.receipt= rs.getString(5);
            ArrayList<Integer> items = new ArrayList<>();
            for(int[] i : relation) {
               if(i[0] == order.id) {
                  items.add(i[1]);
               }
            }
            order.items = new int[items.size()];
            int j = 0;
            for(int it : items) {
               order.items[j++] = it;
            }
            orders.add(order);
         }
         Order[] result = new Order[orders.size()];
         orders.toArray(result);
         return result;
      }catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
         return null;

      }
	}
	@Override
	public Order getOrder(int id) throws RemoteException
	{
		PreparedStatement statement;
		try
		{
			statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".orders WHERE table_id =?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			statement =connection.prepareStatement("select (item_id) from \"Kartofil\".menuitem_order where order_id=?");
			statement.setInt(1,  id);
			ResultSet relation = statement.executeQuery();
			ArrayList<Integer> items = new ArrayList<>();
			while(relation.next()) {
			   items.add(relation.getInt(1));
			}
			
			rs.next();
			int[] its = new int[items.size()];
			int j = 0;
			for(int i : items) {
			   its[j++] = i;
			}
			Order ord = new Order(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), its);
			return ord;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

   @Override
   public Seat getSeat(int id) throws RemoteException
   {
     PreparedStatement statement;
     try
   {
      statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".seat WHERE table_id =?");
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      rs.next();
      return new Seat(rs.getInt(1),rs.getBoolean(2));
      
   }
   catch (SQLException e)
   {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
     
     return null;
   }

   @Override
   public int update(Seat seat) throws RemoteException
   {
      PreparedStatement statement;
      
      try
      {
         statement = connection.prepareStatement("UPDATE \"Kartofil\".seat set isOccupied =?  WHERE id_table =?");
         statement.setBoolean(1, seat.isOccupied);
         statement.setInt(2, seat.id);
         statement.execute();
         
         return 0;
         
      }
      catch (SQLException e)
      {
          return ERROR.DATABASE_ERROR;//If you have a SQL error.
      }
      
      
   }

	@Override
	public int updateOrderStatus(int id, String status) throws RemoteException {
		PreparedStatement statement;
        try {
			statement = connection.prepareStatement("UPDATE \"Kartofil\".orders set status = ?  WHERE order_id = ?");
			statement.setString(1, status);
			statement.setInt(2, id);
			statement.execute();
		} catch (SQLException e) {
			
		    return ERROR.DATABASE_ERROR;//If you have a SQL error.
		
		}
		return 0; // We return 0 when it is successful. This int will be used in the Server class updateOrderStatus method.
	}

	@Override
	public int placeOrder(Order order) throws RemoteException {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("INSERT INTO \"Kartofil\".orders (table_id, status, feedback, receipt) VALUES (?, ?, ?, ?) returning order_id");
			
			statement.setInt(1, order.idTable);
			statement.setString(2, order.status);
			statement.setString(3, order.comment);
			statement.setString(4, order.receipt);
			ResultSet rs = statement.executeQuery();
			rs.next();
			int oid = rs.getInt(1);
			System.out.println(oid);
			for(int i : order.items)
			{
			   statement = connection.prepareStatement("INSERT INTO \"Kartofil\".menuitem_order VALUES (?,?) ");
	         statement.setInt(1, i);
	         statement.setInt(2, oid);
	         System.out.println(statement.toString());

	         statement.execute();
			}	
        } catch (SQLException e) {
		  return ERROR.DATABASE_ERROR;
		}
		return 0;
	}

	@Override
	public int addMenuItem(MenuItem menuItem) throws RemoteException {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("INSERT INTO \"Kartofil\".menuItem (menu_name, description, isAvailable, price) VALUES (?, ?, ?, ?)");
			statement.setString(1, menuItem.name);
			statement.setString(2, menuItem.description);
			statement.setBoolean(3, menuItem.isAvailable);
			statement.setInt(4, menuItem.price);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return ERROR.DATABASE_ERROR;
		}
		return 0;
	}

	@Override
	public int removeMenuItem(int id) throws RemoteException {
		PreparedStatement statement;
			try {
				statement = connection.prepareStatement("DELETE FROM \"Kartofil\".menuItem WHERE item_id = ? CASCADE");
				statement.setInt(1, id);
				statement.execute();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return ERROR.DATABASE_ERROR;
			}
			
		return 0;
	}


	
	public int setAvailability(int id, boolean availability) throws RemoteException {

		PreparedStatement statement;
  
		try {
			statement = connection.prepareStatement("UPDATE \"Kartofil\".menuItem set isAvailable = ?  WHERE item_id = ?");
			statement.setBoolean(1, availability);
			statement.setInt(2, id);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR.DATABASE_ERROR;
		}
		return 0;
	}

	@Override
	public int updateTable(int id, boolean isOccupied) throws RemoteException {
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("INSERT INTO \"Kartofil\".seat (table_id, isOccupied) VALUES (?,?)");
			statement.setInt(1, id);
			statement.setBoolean(2, isOccupied);
		} catch (SQLException e) {
			e.printStackTrace();
			return ERROR.DATABASE_ERROR;
		}
		
		return 0;
	}
	

}