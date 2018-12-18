package Client.UI.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Shared.MenuItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
            paneID.addRow(r++, new MenuItemLabel(m), new Label("" + m.price + "DKK"));
            Label l = new Label(m.description);
//            l.setStyle("-fx-font-style: italic");
            paneID.addRow(r++, l);
            StringBuilder sb = new StringBuilder();
            for(String str : m.ingredients) {
                sb.append(str);
                sb.append(", ");
            }
            String s = sb.toString();
            Label ing = new Label(" ");
            if(s.length() != 0) {
				ing = new Label(s.substring(0, s.length() - 2));
			}
            ing.setStyle("-fx-font-style: italic");
            Insets ins = ing.getInsets();
			ins = new Insets(ins.getTop(), ins.getRight(), ins.getBottom() + 10, ins.getLeft());
            ing.setPadding(ins);
            paneID.addRow(r++, ing);
        }
    }

    @FXML
    public void ViewCartButton() throws IOException
    {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddToCart.fxml"));
        AddToCartController atc = new AddToCartController(stage);
        loader.setController(atc);
        AddToCartController.current = loader.load();
        stage.getScene().setRoot(AddToCartController.current);
        stage.sizeToScene();
        atc.loadCart();

    }
}
