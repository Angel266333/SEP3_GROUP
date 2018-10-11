package Client;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControllerGetMenuUI extends UIController
{
   Parent parent;
   
   
   public ControllerGetMenuUI() {

      try {
         parent = FXMLLoader.load(getClass().getResource("getMenuUIClient.fxml"));
      } catch (IOException e) {
         System.out.println("Could not load resource! Error.");
         e.printStackTrace();
         
      }
   }

   @Override
   String getTitle()
   {
      return "getMenuUI";
   }

   @Override
   Parent getParent()
   {
      return parent;
   }

}
