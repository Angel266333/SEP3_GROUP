package Client.UI;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UIContainer extends GridPane {
	private Stage stage;

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void loadController(UIController controller) {
		controller.setContainer(this);
		getChildren().setAll(controller.getParent());
		stage.setTitle(controller.getTitle());
		stage.sizeToScene();
	}
}
