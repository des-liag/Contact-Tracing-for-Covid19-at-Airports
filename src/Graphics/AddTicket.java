package Graphics;

import Airports.Airport;
import Airports.ProgramData;
import static Graphics.AddFlight.destICAO;
import javafx.collections.ObservableList;
import  javafx.scene.layout.GridPane;
import  javafx.stage.Stage;
import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import  javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;


/**
 * @class AddTicket adding a new ticket
 */
public class AddTicket {
    /**
     * this fields are all the necessary data for the creation
     * of a new ticket
     */
    protected static TextField textAreaSSN = new TextField();
    protected static TextField textAreaName = new TextField();
    protected static TextField textAreaLastName = new TextField();
    protected static TextField textAreaAddress = new TextField();
    protected static TextField textAreaPhone = new TextField();
    protected static TextField textAreaFlightID = new TextField();
    protected static DatePicker checkinTime = new DatePicker();
    protected static TextField textArealuggage = new TextField();
    protected static TextField textAreaDepGate = new TextField();
    protected static TextField textAreaDestGate = new TextField();
    protected static ComboBox<String> depICAO = new ComboBox<>();
    protected static ComboBox<String> destICAO = new ComboBox<>();

    public static void newTicket() {
      Stage stage = new Stage();
      GridPane gridPane = new GridPane();
      SetStyles.creatWindow("ADDING A NEW TICKET", 1200,1200, stage, gridPane);

      Label ssn = SetStyles.createLabels("Please enter passenger's SSN number:", 0,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaSSN,1,0);
      Label name = SetStyles.createLabels("Please enter passenger's First Name :", 2,0,
                Paint.valueOf("black"),Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaName,3,0);
      Label lastName = SetStyles.createLabels("Please enter passenger's Last Name :", 4,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaLastName,5,0);
      Label address = SetStyles.createLabels("Please enter passenger's address :", 6,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaAddress,7,0);
      Label phone = SetStyles.createLabels("Please enter passenger's phone number :", 8,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaPhone,9,0);
      Label id = SetStyles.createLabels("Please enter passenger's Flight ID :", 10,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaFlightID,11,0);
      Label checkInTime = SetStyles.createLabels("Please enter passenger's check-In time: ", 12,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      Label warning = SetStyles.createLabels("please type next to date the time in this form: THH:MM:SS",13,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
      GridPane.setRowIndex(checkinTime,14 );
      GridPane.setColumnIndex(checkinTime, 0);
      checkinTime.setEditable(true);
      Label luggage = SetStyles.createLabels("Please enter if passenger has luggage or not (type TRUE or FALSE): ",
                15,0, Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textArealuggage,16,0);
      Label dpgate = SetStyles.createLabels("Please enter passenger's departure gate:", 17,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaDepGate,18,0);
      Label dsgate = SetStyles.createLabels("Please enter passenger's destination gate:", 19,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaDestGate,20,0);
      Label dpICAO = SetStyles.createLabels("Please pick passenger's departure airport ICAO:", 21,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      depICAO.setPromptText("Departure airport");
      GridPane.setRowIndex(depICAO, 22);
      GridPane.setColumnIndex(depICAO, 0);
      
        ObservableList<String> list1 = depICAO.getItems();
        for (Airport airport : ProgramData.getAirports()) {
            list1.add(airport.getAirportICAO());
        }
      Label dsICAO = SetStyles.createLabels("Please pick passenger's destination airport ICAO:", 23,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      destICAO.setPromptText("Departure airport");
      GridPane.setRowIndex(destICAO, 24);
      GridPane.setColumnIndex(destICAO, 0);
      
        ObservableList<String> list2 = destICAO.getItems();
        for (Airport airport : ProgramData.getAirports()) {
            list2.add(airport.getAirportICAO());
        }

      Button okButton = new Button("OK");
      SetStyles.setStyleForButtons(okButton, 26, 30);
      okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkSSN(textAreaSSN,stage);
            CheckAddingInput.checkPersonData(textAreaName,textAreaLastName,textAreaAddress,textAreaPhone,stage);
            CheckAddingInput.checkID(textAreaFlightID,stage);
            CheckAddingInput.checkLuggage(textArealuggage,stage);
           try {
               CheckAddingInput.checkGate(textAreaDepGate,stage);
               CheckAddingInput.checkGate(textAreaDestGate,stage);
           } catch (Exception e) {
               e.printStackTrace();
           }
            CheckAddingInput.checkICAO(depICAO,stage);
            CheckAddingInput.checkICAO(destICAO,stage);
            CheckAddingInput.checkDate(checkinTime, stage);
        });

      gridPane.getChildren().addAll(ssn,textAreaSSN, name, textAreaName, lastName, textAreaLastName, address,
                textAreaAddress, phone, textAreaPhone, id, textAreaFlightID, checkInTime, checkinTime, luggage,
                textArealuggage, dpgate, textAreaDepGate, dsgate, textAreaDestGate, dpICAO, depICAO,
                dsICAO, destICAO,okButton, warning)  ;
    }

    public static String getSSN() { 
        return textAreaSSN.getText(); 
    }
    public static String getName() {
        return textAreaName.getText();
    }
    public static String getLastName() {
        return textAreaLastName.getText();
    }
    public static String getAddress() {
        return textAreaAddress.getText();
    }
    public  static String getPhone() {
        return textAreaPhone.getText();
    }
    public static String getID() {
        return textAreaFlightID.getText();
    }
    public static String getCheckIn() {
        return checkinTime.getValue().toString();
    }
    public static String getLuggage() {
        return textArealuggage.getText();
    }
    public static String getDpgate() {
        return textAreaDepGate.getText();
    }
    public static String getDsgate() {
        return textAreaDestGate.getText();
    }
    public static String getDpICAO() {
        return  depICAO.getValue();
    }
    public static String getDsICAO() {
        return  destICAO.getValue();
    }

}
