package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Client.UI.Customer.ReceiptController;
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

public class OrderController implements Initializable {

	private Stage stage;
	@FXML
	private Button mainMenu;
	
	@FXML
	private GridPane gridPane;
	
	 public static Order order;
	
	public OrderController(Stage primaryStage) {
		this.stage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void loadOrders()
	{
	   int count = 0;
      Order [] order = ClientEngine.getInstance().getAllOrders();
      System.out.println(order.length);
      for (Order o : order) {
         gridPane.addRow(count++, new Label(""+ o.idTable), new OrderStatusLabel(o), new Label(o.comment));
      }    
	}
	
	public void mainMenu() throws IOException
	{
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
      loader.setController(new StaffMainController(stage));
      Parent p1 = loader.load();
      stage.getScene().setRoot(p1);
      stage.sizeToScene();
	}
}
