package Database;

import java.rmi.RemoteException;

import Shared.MenuItem;
import Shared.Order;
import Shared.Seat;

public class ProxyDatabase implements IDatabase {
	private ConcreteDatabase database;

	@Override
	public int update(MenuItem item) {
		return 0;

	}

	@Override
	public int remove(MenuItem item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MenuItem[] search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seat getSeat(int id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Seat seat) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrderStatus(int id, String status) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

   @Override
   public int placeOrder(Order order) throws RemoteException
   {
      // TODO Auto-generated method stub
      return 0;
   }

	@Override
	public int addMenuItem(MenuItem menuItem) throws RemoteException {
		return 0;
	}

	@Override
	public int removeMenuItem(int id) throws RemoteException {
		return 0;
	}

   @Override
   public Order[] getAllOrders() throws RemoteException
   {
      // TODO Auto-generated method stub
      return null;
   }

public int setAvailability(int id, boolean availability) throws RemoteException {
	return 0;
	// TODO Auto-generated method stub
	
}

@Override
public int updateTable(int id, boolean isOccupied) throws RemoteException {
	// TODO Auto-generated method stub
	return 0;
}
}
