package Client.UI.Customer;

import Client.UI.UIController;
import Shared.MenuItem;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ControllerMainView extends UIController {
	Parent parent;
	VBox itemList;
	Button search;

	public ControllerMainView() {
		try {
			parent = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
			itemList = (VBox) parent.lookup("#VBoxId");
			search = (Button) parent.lookup("#SearchButton");
			search.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					System.out.println("click");
				}
			});
			MenuItem m = new MenuItem(1, "Cheese", "Cheesy cheese", true, 100);
			if(search == null) {
				System.out.println("dldl");
			}
//			itemList.getChildren().add(new MenuItemLabel(m));
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
