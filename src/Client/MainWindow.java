package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		UIContainer container = new UIContainer();
		container.setStage(primaryStage);
		container.loadController(new ControllerLaunchPrompt());
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(container));
		primaryStage.show();
	}
<<<<<<< HEAD
	public static void main(String[] args) {
		launch();
	}
=======
	
	
	public static void main(String [] args)
	{
	   launch();
	}
	
>>>>>>> master
}
