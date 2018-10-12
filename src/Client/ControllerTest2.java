package Client;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControllerTest2 extends UIController
{  
   Parent parent;
   
   public ControllerTest2() {
      try
      {
         parent = FXMLLoader.load(getClass().getResource("Test2.fxml"));
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   @Override
   String getTitle()
   {
      return "Test2";
   }

   @Override
   Parent getParent()
   {
      return parent;
   }

}
