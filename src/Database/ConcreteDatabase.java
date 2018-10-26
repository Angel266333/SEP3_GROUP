package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Shared.Filter;
import Shared.MenuItem;

public class ConcreteDatabase implements IDatabase {
	public Connection connection;

	public ConcreteDatabase() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "12131213");
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
		if (menuFilter == null) {
			PreparedStatement statement;
			try {
				statement = connection.prepareStatement("SELECT * FROM \"Kartofil\".menuitem");
				ArrayList<MenuItem> menuItems = new ArrayList<>();
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					MenuItem item = new MenuItem(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
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

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ConcreteDatabase database = new ConcreteDatabase();
		MenuItem[] arrayTest = database.search(null);
		for (MenuItem m : arrayTest) {
			System.out.println(m);
		}
	}
}

