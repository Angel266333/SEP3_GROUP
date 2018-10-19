package Database;

import java.rmi.Remote;

import Shared.Filter;
import Shared.MenuItem;

public interface IDatabase extends Remote
{
   public int update(MenuItem item);
   public int remove(MenuItem item);
   public MenuItem [] search(Filter menuFilter);
   
}
