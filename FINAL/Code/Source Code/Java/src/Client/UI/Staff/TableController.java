package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Shared.Order;
import Shared.Seat;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TableController implements Initializable {

	private Stage stage;
	@FXML
	private Button mainMenu;
	@FXML
	private Button btn_Table1;
	@FXML
	private Button btn_Table2;
	@FXML
	private Button btn_Table3;
	@FXML
	private Button btn_Table4;
	@FXML
	private Button btn_Table5;
	@FXML
	private Button btn_Table6;
	@FXML
	private Button btn_Table7;
	@FXML
	private Button btn_Table8;
	@FXML
	private Button btn_Table9;
	@FXML
	private Button btn_Table10;

	public TableController(Stage primaryStage) {
		this.stage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_Table1.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(1, btn_Table1));
		btn_Table2.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(2, btn_Table2));
		btn_Table3.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(3, btn_Table3));
		btn_Table4.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(4, btn_Table4));
		btn_Table5.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(5, btn_Table5));
		btn_Table6.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(6, btn_Table6));
		btn_Table7.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(7, btn_Table7));
		btn_Table8.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(8, btn_Table8));
		btn_Table9.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(9, btn_Table9));
		btn_Table10.addEventHandler(MouseEvent.MOUSE_CLICKED, new StatusChanger(10, btn_Table10));
		btn_Table1.setText(ClientEngine.getInstance().getTableStatus(1));
		btn_Table2.setText(ClientEngine.getInstance().getTableStatus(2));
		btn_Table3.setText(ClientEngine.getInstance().getTableStatus(3));
		btn_Table4.setText(ClientEngine.getInstance().getTableStatus(4));
		btn_Table5.setText(ClientEngine.getInstance().getTableStatus(5));
		btn_Table6.setText(ClientEngine.getInstance().getTableStatus(6));
		btn_Table7.setText(ClientEngine.getInstance().getTableStatus(7));
		btn_Table8.setText(ClientEngine.getInstance().getTableStatus(8));
		btn_Table9.setText(ClientEngine.getInstance().getTableStatus(9));
		btn_Table10.setText(ClientEngine.getInstance().getTableStatus(10));
	}

class StatusChanger implements EventHandler<MouseEvent> {

	private int id;
	private Button buttonInput;
	
	public StatusChanger(int id, Button buttonInput) {
	
		this.id = id;
		this.buttonInput = buttonInput;
		
	}
		@Override
		public void handle(MouseEvent event) {

			if (buttonInput.getText().equals("Available")) {
				ClientEngine.getInstance().changeTableStatus(id, true);
				buttonInput.setText("Occupied");
			} else {
				ClientEngine.getInstance().changeTableStatus(id, false);
				buttonInput.setText("Available");
			}
		}

	}

	public void mainMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
		loader.setController(new StaffMainController(stage));
		Parent p1 = loader.load();
		stage.getScene().setRoot(p1);
		stage.sizeToScene();
	}
}