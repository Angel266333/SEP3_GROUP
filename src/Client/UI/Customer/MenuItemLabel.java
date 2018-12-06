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
		int w = 50 - item.name.length();
		w -= ("" + item.price).length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < w;i++) {
			sb.append(' ');
		}
		setText(item.name + sb.toString() + item.price);

		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Alert a = new Alert(Alert.AlertType.NONE);
				a.getButtonTypes().add(ButtonType.OK);
				a.setTitle("Item");
				a.setContentText("ID: " + item.id);
				a.showAndWait();
			}
		});
	}
}
