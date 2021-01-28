package Graphics;

import Airports.Airport;
import Airports.ProgramData;
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
    protected static DatePicker checkinDate = new DatePicker();
    protected static TextField textAreaTime = new TextField();
    protected static TextField textArealuggage = new TextField();
    protected static TextField textAreaDepGate = new TextField();
    protected static TextField textAreaDestGate = new TextField();
    protected static ComboBox<String> depICAO = new ComboBox<>();
    protected static ComboBox<String> destICAO = new ComboBox<>();
    // array flag saves false or true if the check method / methods from class CheckAddingInput
    // had been done
    static final boolean[] flag = {false,false,false,false,false,false,false,false,false};
    static Stage stage = new Stage();

    public static void newTicket() {

      GridPane gridPane = new GridPane();
      SetStyles.creatWindow("ADDING A NEW TICKET", 1700,1000, stage, gridPane);

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
      Label checkInDate = SetStyles.createLabels("Please enter passenger's check-In date: ", 12,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      GridPane.setRowIndex(checkinDate,13 );
      GridPane.setColumnIndex(checkinDate, 0);
      Label time = SetStyles.createLabels("Please enter passenger's check-In time: ", 14,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      Label warning = SetStyles.createLabels("please type the time in this form: HH:MM:SS",15,
                0, Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
      SetStyles.setPosition(textAreaTime,16,0);

      Label luggage = SetStyles.createLabels("Please enter if passenger has luggage or not (type YES or NO): ",
                17,0, Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textArealuggage,18,0);
      Label dpgate = SetStyles.createLabels("Please enter passenger's departure gate:", 19,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaDepGate,20,0);
      Label dsgate = SetStyles.createLabels("Please enter passenger's destination gate:", 21,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      SetStyles.setPosition(textAreaDestGate,22,0);
      Label dpICAO = SetStyles.createLabels("Please pick passenger's departure airport ICAO:", 0,2,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      depICAO.setPromptText("Departure airport");
      GridPane.setRowIndex(depICAO, 1);
      GridPane.setColumnIndex(depICAO, 2);
      
        ObservableList<String> list1 = depICAO.getItems();
        for (Airport airport : ProgramData.getAirports()) {
            list1.add(airport.getAirportICAO());
        }
      Label dsICAO = SetStyles.createLabels("Please pick passenger's destination airport ICAO:", 2,2,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
      destICAO.setPromptText("Departure airport");
      GridPane.setRowIndex(destICAO, 3);
      GridPane.setColumnIndex(destICAO, 2);
      
        ObservableList<String> list2 = destICAO.getItems();
        for (Airport airport : ProgramData.getAirports()) {
            list2.add(airport.getAirportICAO());
        }

      Button okButton = new Button("OK");
      SetStyles.setStyleForButtons(okButton, 28,3 );
      okButton.setOnMouseClicked(event -> {
          if (CheckAddingInput.checkSSN(textAreaSSN, stage)) {
              flag[0] = false;
          } else flag[0] = true;

          if (CheckAddingInput.checkPersonData(textAreaName,textAreaLastName,textAreaAddress,textAreaPhone,stage)) {
              flag[1] = false;
          } else flag[1] = true;

          if (CheckAddingInput.checkID(textAreaFlightID,stage)) {
              flag[2] = false;
          } else flag[2] = true;

          if (CheckAddingInput.checkLuggage(textArealuggage,stage)) {
              flag[3] = true;
          } else  flag[3] = false;
           try {
               if (CheckAddingInput.checkGate(textAreaDepGate, stage)) {
                   flag[4] = false;
               } else flag[4] = true;
               if (CheckAddingInput.checkGate(textAreaDestGate, stage)) {
                   flag[5] = false;
               } else flag[5] = true;
           } catch (Exception e) {
               e.printStackTrace();
           }
          if (CheckAddingInput.checkICAO(depICAO,stage)) {
              flag[6] = false;
          } else flag[6] = true;

          if (CheckAddingInput.checkICAO(destICAO,stage)) {
              flag[7] = false;
          } else flag[7] = true;

          if (CheckAddingInput.checkEntrance(checkinDate,textAreaTime,stage)) {
              flag[8] = false;
          } else flag[8] = true;
          correctData();
      });

        Button buttonBack = new Button("BACK");
        SetStyles.setStyleForButtons(buttonBack, 28, 5);
        buttonBack.setOnMouseClicked(event -> {
            AddingData.switchWindow((javafx.stage.Stage) buttonBack.getScene().getWindow());
            stage.close();
        });

      gridPane.getChildren().addAll(ssn,textAreaSSN, name, textAreaName, lastName, textAreaLastName, address,
                textAreaAddress, phone, textAreaPhone, id, textAreaFlightID,time,checkInDate, checkinDate,
              textAreaTime, luggage, textArealuggage, dpgate, textAreaDepGate, dsgate, textAreaDestGate,
              dpICAO, depICAO, dsICAO, destICAO,okButton, warning, buttonBack) ;
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
        return checkinDate.getValue().toString();
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
    public static String getTime() {
        return textAreaTime.getText();
    }

    // if all flags are true means all data are correct
    // and close stage
    public static void correctData() {
        if (flag[0] && flag[1] && flag[2] && flag[3] && flag[4] && flag[5] && flag[6] && flag[7] && flag[8]) {
            stage.close();
            ProgramData.addTicket();
        }
    }


}
