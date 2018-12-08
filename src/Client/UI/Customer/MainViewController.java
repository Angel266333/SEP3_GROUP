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
import javafx.scene.control.*;
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

    @FXML
    private TextField searchTextField;

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
    	paneID.getChildren().clear();
        int r = 0;
        for (MenuItem m : ClientEngine.getInstance().searchMenuItems(menuItems, searchTextField.getText()))
        {
            paneID.addRow(r++, new MenuItemLabel(m), new Label("" + m.price));
            System.out.println(m.price);
        }
    }

    @FXML
    public void ViewCartButton() throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddToCart.fxml"));
        AddToCartController atc = new AddToCartController(stage);
        loader.setController(atc);
        Parent p = loader.load();
        stage.getScene().setRoot(p);
        atc.loadCart();

    }
}
