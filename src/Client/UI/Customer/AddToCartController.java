package Client.UI.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Shared.MenuItem;
import Shared.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddToCartController implements Initializable
{
   @FXML
   private Button payButton;
   
   @FXML
   private GridPane gridPane;
   
   public Stage stage;
   
   public static Order order;
   
   public AddToCartController(Stage stage)
   {
      this.stage = stage;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      order = new Order();
      int count = 0 ;
     MenuItem [] items = ClientEngine.getInstance().getCart();
     for(MenuItem item : items)
     {
        gridPane.addRow(count++, new Label(item.name), new Label(item.description), new Label("" +item.price));
     }
   }

   
   public void payButton() throws IOException
   {
      FXMLLoader loader = new FXMLLoader(
            getClass().getResource("Payment.fxml"));
      loader.setController(new AddToCartController(stage));
      Parent p = loader.load();
      stage.getScene().setRoot(p);
   }
   
  
}
