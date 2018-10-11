package Client;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ControllerLaunchPrompt extends UIController {

	Parent parent;

	public ControllerLaunchPrompt() {

		try {
			parent = FXMLLoader.load(getClass().getResource("ControllerLaunchPrompt.fxml"));
		} catch (IOException e) {
			System.out.println("Could not load resource! Error.");
			e.printStackTrace();
		}
	}

	@Override
	String getTitle() {
		return "Controller Launch Window";
	}

	@Override
	Parent getParent() {
		return parent;
	}
}