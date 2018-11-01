package Database;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Shared.Filter;
import Shared.MenuItem;

public interface IDatabase extends Remote
{
   public int update(MenuItem item) throws RemoteException;
   public int remove(MenuItem item) throws RemoteException;
   public MenuItem[] search(Filter menuFilter) throws RemoteException;
}