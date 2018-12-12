package Client.UI.Customer;

import Client.ClientEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EnterTableNumberController implements Initializable {
	@FXML
	private TextField numberField;

	private Stage stage;

	public EnterTableNumberController(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void startSystem() throws IOException {
		String s = numberField.getText();
		int i;
		try {
			i = Integer.parseInt(s);
			ClientEngine.tableNumber = i;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_view.fxml"));
			loader.setController(new MainViewController(stage));
			stage.getScene().setRoot(loader.load());
			stage.sizeToScene();
		} catch(NumberFormatException e) {
			Alert a = new Alert(Alert.AlertType.NONE);
			a.setTitle("Error");
			a.setContentText("Invalid table number");
			a.getButtonTypes().add(ButtonType.OK);
			a.showAndWait();
		}
	}
}
