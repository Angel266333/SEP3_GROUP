package Client.UI.Customer;

import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewController implements Initializable
{
   @FXML
   private VBox vBoxId;

   @FXML
   private ScrollPane scp2;

   @FXML
   private Button SearchButton;

   @FXML
   private GridPane paneID;

   @FXML
   private Button ViewCartButton;

   public static MenuItem[] menuItems;

   private Stage stage;

   public MainViewController(Stage stage)
   {
      this.stage = stage;
   }

   @Override
   public void initialize(URL location, ResourceBundle resources)
   {
      try
      {
         menuItems = ClientEngine.getInstance().getMenu();
      }
      catch (Exception e)
      {
         new Alert(Alert.AlertType.ERROR, "Connection problems", ButtonType.OK)
               .showAndWait();
      }
   }

   public void loadMenuItems()
   {
      int r = 0;
      for (MenuItem m : menuItems)
      {
         paneID.addRow(r++, new MenuItemLabel(m));
         System.out.println(m.price);
      }
   }

   // @FXML
   // public void ViewCartButton(ActionEvent e) throws IOException
   // {
   //
   // FXMLLoader loader = new
   // FXMLLoader(getClass().getResource("AddToCart.fxml"));
   // loader.setController(new AddToCartController(stage));
   // stage.setScene(new Scene(loader.load()));
   //
   // }
}
