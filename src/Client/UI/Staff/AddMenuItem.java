package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Client.UI.Customer.AddToCartController;
import Shared.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMenuItem  implements Initializable {

	private Stage stage;


	@FXML
    private TextField nameTextField;

	@FXML
    private TextField ingredientsTextField;
	
	@FXML
    private TextField priceTextField;
	
	@FXML
    private TextField descriptionTextField;
	
	@FXML
    private Button menuItemsButton;
	
	@FXML
	private Button addItem;
	
	

	public AddMenuItem(Stage primaryStage) {
		this.stage = primaryStage;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void addMenuItem() {
		String name = nameTextField.getText();
		String description = descriptionTextField.getText();
		String[] ingredients = ingredientsTextField.getText().split(",");
		try {

			int price = Integer.parseInt(priceTextField.getText());
			
			MenuItem i = new MenuItem(0, name, description, ingredients, true, price);
			
			boolean b = ClientEngine.getInstance().addMenuItem(i);
			if(b) {

				nameTextField.clear();
				descriptionTextField.clear();
				ingredientsTextField.clear();
				priceTextField.clear();
			}
			
		}catch (NumberFormatException e)
		{
			Alert a = new Alert(AlertType.NONE);
			a.setTitle("ERROR");
			a.setContentText("Price is not a number");
			a.getButtonTypes().add(ButtonType.OK);
			a.showAndWait();
			return;
		}
		
		
		
	}

	public void menuItemsButton() throws IOException {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantMenu.fxml"));
			loader.setController(new RestaurantMenuController(stage));
			stage.getScene().setRoot(loader.load());
		
		
	}

}
