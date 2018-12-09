package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Client.UI.Customer.MainViewController;
import Client.UI.Customer.MenuItemLabel;
import Shared.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;


public class RestaurantMenuController implements Initializable {
	
	   
	   @FXML
	   private Button addButton;
	   
	   @FXML
	   private Button removeButton;

	   @FXML
	   private Pane menuItemsPane;

	   @FXML
	   private RadioButton availableRadioButton;
	   
	   @FXML
	   private RadioButton notAvailableButton;
	   
	   @FXML
	   private Button mainMenuButton;


	   public static MenuItem[] menuItems;
	   public Stage stage;
	
	public RestaurantMenuController(Stage primaryStage) {
		this.stage = primaryStage;
	}

    
	@Override
	public void initialize(URL location, ResourceBundle resources){
        try
        {
            menuItems = ClientEngine.getInstance().getMenu();
        }
        catch (Exception e)
        {
            new Alert(Alert.AlertType.ERROR, "Connection problems", ButtonType.OK)
                    .showAndWait();
        }
    }
	
	public void addMenuItem() {

	      FXMLLoader loader = new FXMLLoader(
	            getClass().getResource("AddMenuItem.fxml"));
	      loader.setController(new RestaurantMenuController(stage));
	      try {
			stage.getScene().setRoot(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      stage.sizeToScene();
		
	}
	
	public void removeMenuItem() {

	      FXMLLoader loader = new FXMLLoader(remove
	}
	
	public void viewMenuItemDetails() {
		
	}
	
	public void setMenuItemToAvailable() {
		
	}
	
	public void setMenuItemToNOTAvailable() {
		
	}
	
	public void mainMenuButton() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
		loader.setController(new RestaurantMenuController(stage));
		stage.getScene().setRoot(loader.load());
		
		
	}
}