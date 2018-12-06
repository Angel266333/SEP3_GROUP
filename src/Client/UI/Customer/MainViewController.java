package Client.UI.Customer;

import Shared.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
	@FXML
	private VBox vBoxId;

	@FXML
	private ScrollPane scp2;

	@FXML
	private Button SearchButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void doShit(ActionEvent e) {
	}
}
