package Client.UI.Staff;

import java.util.Optional;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AvailabilityLabel extends Label{
	MenuItem item;

	   public AvailabilityLabel(MenuItem item)
	   {
	      
	      this.item = item;
	      setText(String.valueOf(item.isAvailable));
	      setStyle("-fx-font-size: 18;-fx-font-weight: bold");

	      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
	      {
	         // TODO make window with details open
	         @Override
	         public void handle(MouseEvent event)
	         {
	            
	            Alert a = new Alert(Alert.AlertType.NONE, "Is this item available?");
	            ButtonType yes = new ButtonType("Yes",ButtonType.OK.getButtonData());
	            ButtonType no = new ButtonType("No",ButtonType.CANCEL.getButtonData());
	            a.setTitle("Availability");
	            a.getButtonTypes().add(no);
	            a.getButtonTypes().add(yes);

	               
	            Optional<ButtonType> ret = a.showAndWait();
	            if(ret.get().equals(yes))
	            {
	               ClientEngine.getInstance().setAvailability(item.id, true);
	            }
	            else {

		               ClientEngine.getInstance().setAvailability(item.id, false);
	            }
	            
	            

	         }
	      });
	   }
}
