import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import  javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class AddAirportStuff {
    protected static TextField textAreaSSN = new TextField();
    protected static TextField textAreaName = new TextField();
    protected static TextField textAreaLastName = new TextField();
    protected static TextField textAreaAddress = new TextField();
    protected static TextField textAreaPhone = new TextField();
    protected static TextField textAreaGate = new TextField();
    protected static TextField textAreaStore = new TextField();
    protected static ComboBox<String> combo3 = new ComboBox<>();
    protected static ComboBox<String> depICAO = new ComboBox<>();
    protected static ComboBox<String> destICAO = new ComboBox<>();
   

    public static void newAirportStuff() {
    	
    	

    	
    	
    	Label labelTypeOfEmployee = SetStyles.createLabels("Please enter the type of employee:", 17,0,
         Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        combo3.setPromptText("Employee Type");
        GridPane.setRowIndex(combo3, 18);
        GridPane.setColumnIndex(combo3, 0);
        javafx.collections.ObservableList<String> list3 = combo3.getItems();
        list3.add("Store Stuff");
        list3.add("Gate Stuff");
        list3.add("CheckIn Stuff");
        
       
    	
    	GridPane gridPane = new GridPane();
        Stage stage = new Stage();
        SetStyles.creatWindow("ADDING A NEW AIRPORT STUFF", 1000,800, stage, gridPane);
        Label ssn = SetStyles.createLabels("Please enter the SSN number of the airport stuff member:", 3,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaSSN,4,0);
        Label name = SetStyles.createLabels("Please enter the First Name of the airport stuff member:", 5,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaName,6,0);
        Label lastName = SetStyles.createLabels("Please enter the Last Name of the airport stuff member:", 7,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaLastName,8,0);
        Label address = SetStyles.createLabels("Please enter the address of the airport stuff member:", 9,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaAddress,10,0);
        Label phone = SetStyles.createLabels("Please enter the phone number of the aiport stuff member:", 11,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaPhone,12,0);
        Label gate = SetStyles.createLabels("Please enter the gate number of the airport stuff member:", 13,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
		SetStyles.setPosition(textAreaGate,14,0);
         Label store = SetStyles.createLabels("Please enter the store number of the airport stuff member:", 15,0,
                 Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
          SetStyles.setPosition(textAreaStore,16,0);
        
        Label dpICAO = SetStyles.createLabels("Please pick passenger's departure airport ICAO:", 19,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      depICAO.setPromptText("Departure airport");
      GridPane.setRowIndex(depICAO, 20);
      GridPane.setColumnIndex(depICAO, 0);
      javafx.collections.ObservableList<String> list = depICAO.getItems();
      Label dsICAO = SetStyles.createLabels("Please pick passenger's destination airport ICAO:", 21,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      destICAO.setPromptText("Departure airport");
      GridPane.setRowIndex(destICAO, 22);
      GridPane.setColumnIndex(destICAO, 0);
      list = destICAO.getItems();
      
     
     
      combo3.setOnAction(event -> {
    	  if (combo3.getValue().equals("Store Stuff") ){
	          combo3.setDisable(false);
	          textAreaGate.setDisable(true);
	          textAreaStore.setDisable(false);
	              
    	  }
    	  else if(combo3.getValue().equals("Gate Stuff")) {
    		  combo3.setDisable(false);
    		  textAreaStore.setDisable(true);
    		  textAreaGate.setDisable(false);
    		  
    		  
    	  }
    	  else if(combo3.getValue().equals("CheckIn Stuff")){
    		  combo3.setDisable(false);
    	  	textAreaGate.setDisable(true);
    	  	textAreaStore.setDisable(true);
    	  }
    	  

      });
      
      
      
        
        
       

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 23, 15);
        okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkSSN(textAreaSSN,stage);
            CheckAddingInput.checkPersonData(textAreaName,textAreaLastName,textAreaAddress,textAreaPhone,stage);
            CheckAddingInput.checkICAO(depICAO,stage);
            CheckAddingInput.checkICAO(destICAO,stage);
        });
        gridPane.getChildren().addAll(ssn,textAreaSSN, name, textAreaName, lastName, textAreaLastName, address,
              textAreaAddress, phone, textAreaPhone, labelTypeOfEmployee , combo3, dpICAO, depICAO,dsICAO
              , destICAO, okButton, gate, textAreaGate, store , textAreaStore)  ;
    }

    public static String getSSN() { return textAreaSSN.getText(); }
    public static String getName() {return textAreaName.getText();}
    public static String getLastName() {return textAreaLastName.getText();}
    public static String getAddress() {return textAreaAddress.getText();}
    public  static String getPhone() {return textAreaPhone.getText();}
    public static String getDpICAO() {return  depICAO.getValue();}
    public static String getDsICAO() {return  destICAO.getValue();}
   


}
