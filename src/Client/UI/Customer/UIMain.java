package Client.UI.Customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent p = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
		primaryStage.setScene(new Scene(p));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
