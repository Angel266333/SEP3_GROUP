package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StaffMainController implements Initializable {

	@FXML
	private Button btn0_Menu;
	@FXML
	private Button btn1_Orders;
	@FXML
	private Button btn2_Tables;
	
	private Stage stage;
	
	public StaffMainController(Stage primaryStage) {
		this.stage = primaryStage;	
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	
	public void redirectToMenuSection() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantMenu.fxml"));
		loader.setController(new RestaurantMenuController(stage));
		stage.getScene().setRoot(loader.load());
	}
	
	public void redirectToOrdersSection() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Order.fxml"));
		loader.setController(new OrderController(stage));
		stage.getScene().setRoot(loader.load());
	}
	
	public void redirectToTablesSection() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Tables.fxml"));
		loader.setController(new TableController(stage));
		stage.getScene().setRoot(loader.load());
	}
}