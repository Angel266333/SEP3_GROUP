package Client.UI.Customer;

import Shared.MenuItem;
import javafx.scene.control.Label;

public class MenuItemLabel extends Label {
	MenuItem item;

	public MenuItemLabel(MenuItem item) {
		this.item = item;
		setText(item.name);
	}
}
