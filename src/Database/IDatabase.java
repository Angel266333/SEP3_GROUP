package Database;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Shared.Filter;
import Shared.MenuItem;
import Shared.Order;
import Shared.Seat;

public interface IDatabase extends Remote
{
   public int update(MenuItem item) throws RemoteException;
   public int remove(MenuItem item) throws RemoteException;
   public MenuItem[] search(Filter menuFilter) throws RemoteException;
   public Order getOrder(int id) throws RemoteException;
   public Seat getSeat(int id) throws RemoteException;
   public int update(Seat seat) throws RemoteException;
	public int updateOrderStatus(int id, String status) throws RemoteException;
}
