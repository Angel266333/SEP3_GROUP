package Client.UI.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PaymentController implements Initializable
{
   @FXML
   private Button backButton; 
   @FXML
   private Button payButton;
   @FXML
   private GridPane gridPane;
   
   
   public static int menuItems;
   
   public Stage stage;
   
   public PaymentController(Stage stage)
   {
      this.stage = stage;
   }
   
   
   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      try
      {
          menuItems = ClientEngine.getInstance().getPrice();
      }
      catch (Exception e)
      {
          new Alert(Alert.AlertType.ERROR, "Connection problems", ButtonType.OK)
                  .showAndWait();
      }
      
   }
   public void totalSum()
   {
      int count= 0 ; 
      int sum = ClientEngine.getInstance().getPrice();
      
      gridPane.addRow(0,new Label("" + sum));
      
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
