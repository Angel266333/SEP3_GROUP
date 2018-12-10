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
   public int placeOrder(Order order) throws RemoteException;
   public int addMenuItem(MenuItem menuItem) throws RemoteException;
   public int removeMenuItem(int id) throws RemoteException;
   public Order [] getAllOrders() throws RemoteException;
}
