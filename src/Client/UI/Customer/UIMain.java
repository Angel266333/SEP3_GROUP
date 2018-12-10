package Client.UI.Customer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UIMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_view.fxml"));
		loader.setController(new MainViewController(primaryStage));
		Parent p = loader.load();
		Scene s = new Scene(p);
//		s.getStylesheets().add(getClass().getResource("material-fx-v0_3.css").toExternalForm());
		primaryStage.setScene(s);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
