package Database;

import Shared.Filter;
import Shared.MenuItem;
import Shared.Order;
import Shared.Seat;

import java.rmi.RemoteException;

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
	public MenuItem[] search(Filter menuFilter) {
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
}
