package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
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
	   private Button showItemsButton;
	   
	   @FXML
	   private Button addButton;
	   

	   @FXML
	   private GridPane menuItemsPane;
	   
	   @FXML
	   private Label elementRemoveLabel;

	   @FXML
	   private RadioButton availableButton;
	   
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


    public void loadMenuItems() {
		menuItemsPane.getChildren().clear();
		for(MenuItem m : ClientEngine.getInstance().getMenu()) {
//			System.out.println(m);
		}
	}
    public void showItemsButton()
    {
        int r = 0;
        menuItemsPane.getChildren().clear();
        for (MenuItem m : ClientEngine.getInstance().getMenu())
        {
        	menuItemsPane.addRow(r++, new MenuItemLabelStaff(m), new AvailabilityLabel(m));
        }
    }
    
	
	public void addButton() {

		
	      FXMLLoader loader = new FXMLLoader(
	            getClass().getResource("AddMenuItem.fxml"));
	      loader.setController(new AddMenuItem(stage));
	      try {
			stage.getScene().setRoot(loader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      stage.sizeToScene();
		
	}
	
	
	public void mainMenuButton() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
		loader.setController(new StaffMainController(stage));
		stage.getScene().setRoot(loader.load());
		
		
	}
}