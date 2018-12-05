package Client.UI.Customer;

import Client.UI.UIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ControllerMainView extends UIController {
	Parent parent;

	public ControllerMainView() {
		try {
			parent = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
		} catch(IOException e) {
			parent = null;
			e.printStackTrace();
		}
	}

	@Override
	public String getTitle() {
		return "Menu";
	}

	@Override
	public Parent getParent() {
		return parent;
	}
}
