package Client.UI.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReceiptController implements Initializable
{  
  
   @FXML
   private Button mainViewButton;
   private Stage stage;

   public ReceiptController(Stage stage)
   {
      this.stage = stage;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      // TODO Auto-generated method stub

   }

   public void mainViewButton() throws IOException
   {
      FXMLLoader loader = new FXMLLoader(
            getClass().getResource("Main_view.fxml"));
      loader.setController(new MainViewController(stage));
      Parent p1 = loader.load();
      stage.getScene().setRoot(p1);
      stage.sizeToScene();
      ClientEngine.getInstance().emptyCart();
   }

 
}
