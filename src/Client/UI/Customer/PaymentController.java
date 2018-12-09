package Client.UI.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PaymentController implements Initializable
{
   @FXML
   private Button backButton; 
   @FXML
   private Button payButton;
   
   
   public Stage stage;
   
   public PaymentController(Stage stage)
   {
      this.stage = stage;
   }
   
   
   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      // TODO Auto-generated method stub
      
   }

   public void backButton() throws IOException 
   {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AddToCart.fxml"));
      loader.setController(new AddToCartController(stage));
      Parent p1 = loader.load();
      stage.getScene().setRoot(p1);
   }
   
   public void payButton() throws IOException
   {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Receipt.fxml"));
      loader.setController(new ReceiptController(stage));
      Parent p1 = loader.load();
      stage.getScene().setRoot(p1);
      stage.sizeToScene();
   }

}
