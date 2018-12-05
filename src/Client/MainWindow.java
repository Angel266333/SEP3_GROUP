package Client;

import Client.UI.ControllerGetMenuUI;
import Client.UI.ControllerLaunchPrompt;
import Client.UI.Customer.ControllerMainView;
import Client.UI.UIContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		UIContainer container = new UIContainer();
		container.setStage(primaryStage);
		container.loadController(new ControllerMainView());
//		container.loadController(new ControllerGetMenuUI());
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(container));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}