package Client.UI;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControllerServerMainView extends UIController
{
   Parent parent;
   
   
   public ControllerServerMainView() {

      try {
         parent = FXMLLoader.load(getClass().getResource("ServerMainView.fxml"));
      } catch (IOException e) {
         System.out.println("Could not load resource! Error.");
         e.printStackTrace();
         
      }
   }


   @Override
   String getTitle()
   {
      
      return "ServerMainView";
   }


   @Override
   Parent getParent()
   {
     
      return parent;
   }

   }
