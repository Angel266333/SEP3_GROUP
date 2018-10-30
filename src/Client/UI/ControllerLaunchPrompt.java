package Client.UI;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class ControllerLaunchPrompt extends UIController {

	Parent parent;
	
	
   public ControllerLaunchPrompt() {

		try {
			parent = FXMLLoader.load(getClass().getResource("ControllerLaunchPrompt.fxml"));
		} catch (IOException e) {
			System.out.println("Could not load resource! Error.");
			e.printStackTrace();
			
		}
	//	button = parent.lookup("#btn0_getMenuUIClient");
		parent.lookup("#btn0_getMenuUIClient").addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event)
         {
            
            container.loadController(new ControllerGetMenuUI());
         }
		   
		});
		
		parent.lookup("#btn1_ServerMainView").addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event)
         {
            
            container.loadController(new ControllerServerMainView());
         }
         
      });
		parent.lookup("#btn2_Controller3").addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event)
         {
            
           // container.loadController(new ControllerTest());
         }
         
      });
		
		parent.lookup("#btn3_Controller4").addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event)
         {
            
           // container.loadController(new ControllerTest2());
         }
         
      });
		
	}

	@Override
	String getTitle() {
		return "Controller Launch Window";
	}

	@Override
	Parent getParent() {
		return parent;
	}
}