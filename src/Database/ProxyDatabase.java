package Database;

import Shared.Filter;
import Shared.MenuItem;

import java.rmi.RemoteException;

public class ProxyDatabase implements IDatabase
{
   private ConcreteDatabase database;
   
   
   @Override
   public int update(MenuItem item)
   {
      return 0;
      
   }

   @Override
   public int remove(MenuItem item)
   {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public MenuItem[] search(Filter menuFilter)
   {
      // TODO Auto-generated method stub
      return null;
   }

}
