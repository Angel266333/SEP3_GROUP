package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.UI.Customer.ReceiptController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OrderController implements Initializable {

	private Stage stage;
	
	private Button mainMenu;
	
	public OrderController(Stage primaryStage) {
		this.stage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
