package Client.UI.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Client.RestHandler;
import Database.ConcreteDatabase;
import Shared.MenuItem;
import Shared.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReceiptController implements Initializable
{  
  
   @FXML
   private Button mainViewButton;
   private Stage stage;
   private Order order;
   private TextField tar0_orderID;
   
   public ReceiptController(Stage stage)
   {
      this.stage = stage;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
	order = new Order();
	order.id = -1;
	order.idTable = -1;
	MenuItem[] items = ClientEngine.getInstance().getCart();
	int[] store = new int[items.length];
	int j = 0;
	for (MenuItem m : items) {
		store[j++] = m.id;
	}
	order.items = store;
	order.status = "PENDING";
	order.comment = "";
	order.receipt = "";
	ClientEngine.getInstance().placeOrder(order);
   }
   
   public void setOrderID() {
	   tar0_orderID.setText();
   }
   

   public void mainViewButton() throws IOException
   {
      FXMLLoader loader = new FXMLLoader(
            getClass().getResource("Main_view.fxml"));
      loader.setController(new MainViewController(stage));
      Parent p1 = loader.load();
      stage.getScene().setRoot(p1);
      stage.sizeToScene();
      ClientEngine.getInstance().emptyCart();
   }

 
}
