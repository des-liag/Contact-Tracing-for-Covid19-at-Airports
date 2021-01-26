package Graphics;

import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import  javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;

/**
 * @class AddAirportStuff adding a new employee
 * (Store Stuff, Gate Stuff, CheckIn Stuff)
 */
public class AddAirportStuff {
    protected static TextField textAreaSSN = new TextField();
    protected static TextField textAreaName = new TextField();
    protected static TextField textAreaLastName = new TextField();
    protected static TextField textAreaAddress = new TextField();
    protected static TextField textAreaPhone = new TextField();
    protected static TextField textAreaGate = new TextField();
    protected static TextField textAreaStore = new TextField();
    protected static ComboBox<String> combo3 = new ComboBox<>();
    protected static ComboBox<String> icao = new ComboBox<>();
    protected static  ArrayList <String> workingSchedule = new ArrayList<>();

    public static void newAirportStuff() {
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
        Label phone = SetStyles.createLabels("Please enter the phone number of the airport stuff member:", 11,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaPhone,12,0);
        Label labelTypeOfEmployee = SetStyles.createLabels("Please enter the type of employee:", 13,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        combo3.setPromptText("Employee Type");
        GridPane.setRowIndex(combo3, 14);
        GridPane.setColumnIndex(combo3, 0);
        javafx.collections.ObservableList<String> list3 = combo3.getItems();
        list3.add("Store Stuff");
        list3.add("Gate Stuff");
        list3.add("CheckIn Stuff");
        Label gate = SetStyles.createLabels("Please enter the working gate of the airport stuff member:", 15,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaGate,16,0);
        Label store = SetStyles.createLabels("Please enter the working store of the airport stuff member:", 17,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaStore,18,0);
        Label airportcode = SetStyles.createLabels("Please pick airport ICAO:", 19,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        icao.setPromptText("Airport ICAO");
        GridPane.setRowIndex(icao, 20);
        GridPane.setColumnIndex(icao, 0);
        javafx.collections.ObservableList<String> list = icao.getItems();

        Label schedule = SetStyles.createLabels("Please pick days and enter time for working schedule: ",0,
                3, Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        Label warning = SetStyles.createLabels("please type time in this form: HH:MM",1,3,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
        CheckBox monday = SetStyles.createCheckBox("Monday",2, 3);
        CheckBox tuesday = SetStyles.createCheckBox("Tuesday",3,3);
        CheckBox wednesday = SetStyles.createCheckBox("Wednesday",4,3);
        CheckBox thursday = SetStyles.createCheckBox("Thursday",5,3);
        CheckBox friday = SetStyles.createCheckBox("Friday",6,3);
        CheckBox saturday = SetStyles.createCheckBox("Saturday",7,3);
        CheckBox sunday = SetStyles.createCheckBox("Sunday",8,3);
        TextField m = new TextField();
        SetStyles.setPosition(m,2,4);
        TextField tu = new TextField();
        SetStyles.setPosition(tu,3,4);
        TextField w = new TextField();
        SetStyles.setPosition(w,4,4);
        TextField th = new TextField();
        SetStyles.setPosition(th,5,4);
        TextField f = new TextField();
        SetStyles.setPosition(f,6,4);
        TextField st = new TextField();
        SetStyles.setPosition(st,7,4);
        TextField sn = new TextField();
        SetStyles.setPosition(sn,8,4);

        workingSchedule.add(monday.getText());
        workingSchedule.add(m.getText());
        workingSchedule.add(tuesday.getText());
        workingSchedule.add(tu.getText());
        workingSchedule.add(wednesday.getText());
        workingSchedule.add(w.getText());
        workingSchedule.add(thursday.getText());
        workingSchedule.add(th.getText());
        workingSchedule.add(friday.getText());
        workingSchedule.add(f.getText());
        workingSchedule.add(saturday.getText());
        workingSchedule.add(sunday.getText());
        workingSchedule.add(sn.getText());

        combo3.setOnAction(event -> {
            switch (combo3.getValue()) {
                case "Store Stuff":
                    combo3.setDisable(false);
                    textAreaGate.setDisable(true);
                    textAreaStore.setDisable(false);
                    break;

                case "Gate Stuff":
                    combo3.setDisable(false);
                    textAreaStore.setDisable(true);
                    textAreaGate.setDisable(false);
                    break;

                case "CheckIn Stuff":
                    combo3.setDisable(false);
                    textAreaGate.setDisable(true);
                    textAreaStore.setDisable(true);
                    break;
            }
        });

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 23, 15);
        okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkSSN(textAreaSSN,stage);
            CheckAddingInput.checkPersonData(textAreaName,textAreaLastName,textAreaAddress,textAreaPhone,stage);
            CheckAddingInput.checkICAO(icao,stage);
            CheckAddingInput.checkScheduleDate(stage,monday,tuesday,wednesday,thursday,friday,saturday,sunday);
            CheckAddingInput.checkScheduleTime(stage,m,tu,w,th,f,st,sn);
            if (combo3.getValue().equals("Store Stuff")) {
                CheckAddingInput.checkStore(textAreaStore,stage);
            } else if (combo3.getValue().equals("Gate Stuff")) {
                try {
                    CheckAddingInput.checkGate(textAreaGate, stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        gridPane.getChildren().addAll(ssn, textAreaSSN, name, textAreaName, lastName, textAreaLastName, address,
                textAreaAddress, phone, textAreaPhone, labelTypeOfEmployee , combo3, gate, textAreaGate,
                store , textAreaStore,icao,airportcode,okButton );
    }

    public static String getSSN() { return textAreaSSN.getText(); }
    public static String getName() {return textAreaName.getText();}
    public static String getLastName() {return textAreaLastName.getText();}
    public static String getAddress() {return textAreaAddress.getText();}
    public  static String getPhone() {return textAreaPhone.getText();}
    public static String getICAO() { return icao.getValue(); }
    public static ArrayList getSchedule () { return workingSchedule;}
}
