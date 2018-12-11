package Client.UI.Staff;

import java.util.Optional;

import Client.ClientEngine;
import Shared.Order;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class OrderStatusLabel extends Label 
{
   public Order order;
   
   public OrderStatusLabel(Order order)
   {
      this.order = order;
      setText(order.status);
      setStyle("-fx-font-size: 18;-fx-font-weight: bold");
      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
      {
         @Override
         public void handle(MouseEvent event)
         {
            
            Alert a = new Alert(Alert.AlertType.NONE, "Change the Status");
            ButtonType pending = new ButtonType("Pending",ButtonType.OK.getButtonData());
            ButtonType delivered = new ButtonType("Delivered",ButtonType.OK.getButtonData());
            a.setTitle("Change order Status");
            a.getButtonTypes().add(pending);
            a.getButtonTypes().add(delivered);

               
            Optional<ButtonType> ret = a.showAndWait();
            if(ret.get().equals(pending))
            {
              boolean result =  ClientEngine.getInstance().changeOrderStatus(order.id, "PENDING");
              if(result)
              {
                 setText("PENDING");
              }
            }
            
            else if(ret.get().equals(delivered))
            {
               boolean result =ClientEngine.getInstance().changeOrderStatus(order.id, "DELIVERED");
               if(result)
               {
                  setText("DELIVERED");
               }
            }

         }
      });
   }
}
