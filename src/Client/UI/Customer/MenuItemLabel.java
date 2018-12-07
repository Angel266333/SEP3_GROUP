package Client.UI.Customer;

import Shared.MenuItem;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MenuItemLabel extends Label {
	MenuItem item;

	public MenuItemLabel(MenuItem item) {
		this.item = item;
		setText(item.name);

		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			//TODO make window with details open
			@Override
			public void handle(MouseEvent event) {
				Alert a = new Alert(Alert.AlertType.NONE, "id: " + item.id, ButtonType.OK);
				a.setTitle("MenuItem");
				a.showAndWait();
			}
		});
	}
}
