package Client;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class TestController extends UIController {
	private static TestController me = null;
	Parent parent;

	private TestController() {
		try {

			parent = FXMLLoader.load(getClass().getResource("ServerViewMain.fxml"));

			parent = FXMLLoader.load(getClass().getResource("ServerMainView.fxml"));

		} catch(IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static TestController getInstance() {
		if(me == null) {
			me = new TestController();
		}
		return me;
	}

	@Override
	String getTitle() {
		return "Test";
	}

	@Override
	Parent getParent() {
		return parent;
	}
}
