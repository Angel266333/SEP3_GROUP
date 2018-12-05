package Client.UI;

import java.io.IOException;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class ControllerGetMenuUI extends UIController {
	Parent parent;
	Button btn0_getMenu;
	TextArea tars0_getMenu;

	public ControllerGetMenuUI() {

		try {
			parent = FXMLLoader.load(getClass().getResource("getMenuUIClient.fxml"));
			btn0_getMenu = (Button) parent.lookup("#btn0_getMenu");
			tars0_getMenu = (TextArea) parent.lookup("#tars0_getMenu");

			btn0_getMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					MenuItem[] menuItems = new MenuItem[0];
					try {
						menuItems = ClientEngine.getInstance().getMenu();
					} catch(IOException e) {
						e.printStackTrace();
					}
					for (MenuItem m : menuItems) {
						tars0_getMenu.appendText(m.toString() + '\n');
					}
				}

			});

		} catch (IOException e) {
			System.out.println("Could not load resource! Error.");
			e.printStackTrace();

		}
	}

	@Override
	String getTitle() {
		return "getMenuUI";
	}

	@Override
	Parent getParent() {
		return parent;
	}

}
