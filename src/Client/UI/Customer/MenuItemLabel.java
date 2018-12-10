package Client.UI.Customer;

import java.util.Optional;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuItemLabel extends Label
{
   MenuItem item;

   public MenuItemLabel(MenuItem item)
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
            
            Alert a = new Alert(Alert.AlertType.NONE, "Add to cart?");
            ButtonType yes = new ButtonType("Yes",ButtonType.OK.getButtonData());
            ButtonType no = new ButtonType("No",ButtonType.CANCEL.getButtonData());
            a.setTitle("Adding item to cart");
            a.getButtonTypes().add(no);
            a.getButtonTypes().add(yes);

               
            Optional<ButtonType> ret = a.showAndWait();
            if(ret.get().equals(yes))
            {
               ClientEngine.getInstance().addToCart(item);
            }
            
            

         }
      });
   }
}
