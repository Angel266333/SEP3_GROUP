package Client.UI.Customer;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
	@FXML
	private VBox vBoxId;

	@FXML
	private ScrollPane scp2;

	@FXML
	private Button SearchButton;

	@FXML
	private GridPane paneID;

	public static MenuItem[] menuItems;
	private Stage stage;

	public MainViewController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			menuItems = ClientEngine.getInstance().getMenu();
		}catch(Exception e) {
			new Alert(Alert.AlertType.ERROR, "Connection problems", ButtonType.OK).showAndWait();
		}
	}

	public void loadMenuItems() {
		int r = 0;
		for(MenuItem m : menuItems) {
			paneID.addRow(r++, new MenuItemLabel(m));
			System.out.println(m.price);
		}
	}
}
