package Client;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControllerTest extends UIController
{
   Parent parent;

   public ControllerTest() {
      
      try
      {
         parent = FXMLLoader.load(getClass().getResource("Test.fxml"));
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
      return "Test";
   }

   @Override
   Parent getParent()
   {
      return parent;
   }
}
