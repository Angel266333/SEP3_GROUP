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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReceiptController implements Initializable
{  
  
   @FXML
   private Button mainViewButton;
   @FXML
   private TextArea tar0_orderID;
   private Stage stage;
   private Order order;
   private int oID;
   
   public ReceiptController(Stage stage)
   {
      this.stage = stage;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
	order = new Order();
	order.id = -1;
	order.idTable = ClientEngine.tableNumber;
	MenuItem[] items = ClientEngine.getInstance().getCart();
	int[] store = new int[items.length];
	int j = 0;
	for (MenuItem m : items) {
		store[j++] = m.id;
	}
	order.items = store;
	order.status = "PENDING";
	order.comment = ClientEngine.comment;
	order.receipt = "";
	tar0_orderID.setText("" + ClientEngine.getInstance().placeOrder(order));
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
