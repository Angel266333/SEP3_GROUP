package Client.UI.Customer;

import Shared.MenuItem;
import javafx.scene.control.Label;

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
	}
}
