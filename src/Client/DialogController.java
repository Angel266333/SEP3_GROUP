package Client;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class DialogController extends Alert {
	public DialogController(UIController controller) {
		super(AlertType.NONE);
		getDialogPane().setContent(controller.getParent());
		setTitle(controller.getTitle());
		Window window = getDialogPane().getScene().getWindow();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				window.hide();
			}
		});
	}
}
