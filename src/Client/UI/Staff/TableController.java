package Client.UI.Staff;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void onClick1()
	{
	   if(btn_Table1.getText().equals("Occupied"))
      {
         btn_Table1.setText("Avaliable");
      }
      else
      {
         btn_Table1.setText("Occupied");
      }
	   
	}
	public void onClick2()
   {
	   if(btn_Table2.getText().equals("Occupied"))
      {
         btn_Table2.setText("Avaliable");
      }
      else
      {
         btn_Table2.setText("Occupied");
      }
      
   }
	public void onClick3()
   {
	   if(btn_Table3.getText().equals("Occupied"))
      {
         btn_Table3.setText("Avaliable");
      }
      else
      {
         btn_Table3.setText("Occupied");
      }
      
   }
	public void onClick4()
   {
	  
	   if(btn_Table4.getText().equals("Occupied"))
	   {
	      btn_Table4.setText("Avaliable");
	   }
	   else
	   {
	      btn_Table4.setText("Occupied");
	   }
	      
      
   }
	public void onClick5()
   {
	   if(btn_Table5.getText().equals("Occupied"))
      {
         btn_Table5.setText("Avaliable");
      }
      else
      {
         btn_Table5.setText("Occupied");
      }
      
   }
	public void onClick6()
   {
	   if(btn_Table6.getText().equals("Occupied"))
      {
         btn_Table6.setText("Avaliable");
      }
      else
      {
         btn_Table6.setText("Occupied");
      }
      
   }
	public void onClick7()
   {
	   if(btn_Table7.getText().equals("Occupied"))
      {
         btn_Table7.setText("Avaliable");
      }
      else
      {
         btn_Table7.setText("Occupied");
      }
      
   }
	public void onClick8()
   {
	   if(btn_Table8.getText().equals("Occupied"))
      {
         btn_Table8.setText("Avaliable");
      }
      else
      {
         btn_Table8.setText("Occupied");
      }
      
   }
	public void onClick9()
   {
	   if(btn_Table9.getText().equals("Occupied"))
      {
         btn_Table9.setText("Avaliable");
      }
      else
      {
         btn_Table9.setText("Occupied");
      }
      
   }
	public void onClick10()
   {
	   if(btn_Table10.getText().equals("Occupied"))
      {
         btn_Table10.setText("Avaliable");
      }
      else
      {
         btn_Table10.setText("Occupied");
      }
      
   }
	public void mainMenu() throws IOException
	{
	   FXMLLoader loader = new FXMLLoader(getClass().getResource("Staff_Main.fxml"));
      loader.setController(new StaffMainController(stage));
      Parent p1 = loader.load();
      stage.getScene().setRoot(p1);
      stage.sizeToScene();
	}
}
