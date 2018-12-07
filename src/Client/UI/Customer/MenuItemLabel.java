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

      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
      {
         // TODO make window with details open
         @Override
         public void handle(MouseEvent event)
         {
            
            Alert a = new Alert(Alert.AlertType.NONE, "Add to cart?");
            ButtonType yes = new ButtonType("Yes",ButtonType.OK.getButtonData());
            ButtonType no = new ButtonType("NO",ButtonType.CANCEL.getButtonData());
            a.getButtonTypes().add(yes);
            a.getButtonTypes().add(no);
            
               
            Optional<ButtonType> ret = a.showAndWait();
            if(ret.get().equals(yes))
            {
               ClientEngine.getInstance().addToCart(item.id);
            }
            
            

         }
      });
   }
}
