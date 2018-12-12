package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Client.ClientEngine;
import Shared.Order;
import Shared.Seat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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

	String a;
	String b;
	String c;
	String d;
	String e;
	String f;
	String g;
	String h;
	String i;
	String j;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		a = ClientEngine.getInstance().getTableStatus(1);
		b = ClientEngine.getInstance().getTableStatus(2);
		c = ClientEngine.getInstance().getTableStatus(3);
		d = ClientEngine.getInstance().getTableStatus(4);
		e = ClientEngine.getInstance().getTableStatus(5);
		f = ClientEngine.getInstance().getTableStatus(6);
		g = ClientEngine.getInstance().getTableStatus(7);
		h = ClientEngine.getInstance().getTableStatus(8);
		i = ClientEngine.getInstance().getTableStatus(9);
		j = ClientEngine.getInstance().getTableStatus(10);
		btn_Table1.setText(a);
		btn_Table2.setText(b);
		btn_Table3.setText(c);
		btn_Table4.setText(d);
		btn_Table5.setText(e);
		btn_Table6.setText(f);
		btn_Table7.setText(g);
		btn_Table8.setText(h);
		btn_Table9.setText(i);
		btn_Table10.setText(j);
	}

// TODO: This method does not work when invoked by specific button instead of repeating of code.
//	public void buttonAction(String buttonReference, String buttonName) {
//		if (a.equals("Occupied") && btn_Table1.getText() == "Occupied") {
//			ClientEngine.getInstance().changeTableStatus(1, false);
//			btn_Table1.setText("Available");
//			a = "Available";
//		} else if (a.equals("Available") && btn_Table1.getText() == "Available") {
//			ClientEngine.getInstance().changeTableStatus(1, true);
//			btn_Table1.setText("Occupied");
//			a = "Occupied";
//		}
//	}
	public void onClick1() {
		// Works properly. --> TRUE in database.
		if (btn_Table1.getText() == "Occupied") {
			ClientEngine.getInstance().changeTableStatus(1, false);
			btn_Table1.setText("Available");
			a = "Available";
		} else if (btn_Table1.getText() == "Available") {
			ClientEngine.getInstance().changeTableStatus(1, true);
			btn_Table1.setText("Occupied");
			a = "Occupied";
		}
		}

	public void onClick2() {
		// Does not work if the Table is set to FALSE in the database.
		System.out.println(b);
		System.out.println(btn_Table2.getText());
		if (btn_Table2.getText() == "Occupied") {
			ClientEngine.getInstance().changeTableStatus(2, false);
			btn_Table2.setText("Available");
			System.out.println("1");
			b = "Available";
		} else if (btn_Table2.getText() == "Available") {
			ClientEngine.getInstance().changeTableStatus(2, true);
			btn_Table2.setText("Occupied");
			System.out.println("2");
			b = "Occupied";
		}
	}
	public void onClick3() {
		// Works properly. --> TRUE in database.
		if (c.equals("Occupied") && btn_Table3.getText() == "Occupied") {
			ClientEngine.getInstance().changeTableStatus(3, false);
			btn_Table3.setText("Available");
			System.out.println("1");
			c = "Available";
		} else if (c.equals("Available") && btn_Table3.getText() == "Available") {
			ClientEngine.getInstance().changeTableStatus(3, true);
			btn_Table3.setText("Occupied");
			System.out.println("2");
			c = "Occupied";
		}
	}

	public void onClick4() {
	}

	public void onClick5() {
	}

	public void onClick6() {
	}

	public void onClick7() {
	}

	public void onClick8() {
	}

	public void onClick9() {
	}

	public void onClick10() {
	}

	public void mainMenu() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
		loader.setController(new StaffMainController(stage));
		Parent p1 = loader.load();
		stage.getScene().setRoot(p1);
		stage.sizeToScene();
	}
}