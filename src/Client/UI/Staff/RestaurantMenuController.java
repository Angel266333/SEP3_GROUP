package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class RestaurantMenuController implements Initializable {
	
	private Stage stage;
	
	public RestaurantMenuController(Stage primaryStage) {
		this.stage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void addMenuItem() {
		
	}
	
	public void removeMenuItem() {
		
	}
	
	public void viewMenuItemDetails() {
		
	}
	
	public void setMenuItemToAvailable() {
		
	}
	
	public void setMenuItemToNOTAvailable() {
		
	}
	
	public void redirectToMainSection() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
		loader.setController(new RestaurantMenuController(stage));
		stage.getScene().setRoot(loader.load());
		
	}
}