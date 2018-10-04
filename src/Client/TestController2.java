package Client;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class TestController2 extends UIController {
	private static TestController2 me = null;
	private Parent parent;
	private TextField textField;

	private TestController2() {
		try {
			parent = FXMLLoader.load(getClass().getResource("Test2.fxml"));
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}

		textField = (TextField) parent.lookup("#tfield");
		parent.lookup("#printButton").addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				System.out.println(textField.getText());
			}
		});
	}

	public static TestController2 getInstance() {
		if(me == null) {
			me = new TestController2();
		}
		return me;
	}

	@Override
	String getTitle() {
		return "Test controller 2";
	}

	@Override
	Parent getParent() {
		return parent;
	}
}
