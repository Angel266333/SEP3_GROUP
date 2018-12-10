package Client.UI.Staff;

import java.util.Optional;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuItemLabelStaff extends Label{
	MenuItem item;

	   public MenuItemLabelStaff(MenuItem item)
	   {
	      
	      this.item = item;
	      setText(item.name);
	      setStyle("-fx-font-size: 18;-fx-font-weight: bold");

	      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
	      {
	         // TODO make window with details open
	         @Override
	         public void handle(MouseEvent event)
	         {
	            
	            Alert a = new Alert(Alert.AlertType.NONE, "Do you want to remove the item from the menu?");
	            ButtonType yes = new ButtonType("Yes",ButtonType.OK.getButtonData());
	            ButtonType no = new ButtonType("No",ButtonType.CANCEL.getButtonData());
	            a.setTitle("Remove Item");
	            a.getButtonTypes().add(no);
	            a.getButtonTypes().add(yes);

	               
	            Optional<ButtonType> ret = a.showAndWait();
	            if(ret.get().equals(yes))
	            {
	               ClientEngine.getInstance().removeMenuItem(item.id);
	            }
	            
	            

	         }
	      });
	   }
}


