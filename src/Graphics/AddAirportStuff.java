package Graphics;

import Airports.Airport;
import Airports.ProgramData;
import javafx.collections.ObservableList;
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
    protected static String[] workingSchedule = new String[7];
    protected static TextField m = new TextField();
    protected static TextField tu = new TextField();
    protected static TextField w = new TextField();
    protected static TextField th = new TextField();
    protected static  TextField f = new TextField();
    protected static TextField st = new TextField();
    protected static TextField sn = new TextField();
    // array flag saves false or true if the check method / methods from class CheckAddingInput
    // had been done
    static final boolean[] flag = {false,false,false,false,false,false,false,false,false};
    static Stage stage = new Stage();

    public static void newAirportStuff() {
        GridPane gridPane = new GridPane();
        SetStyles.creatWindow("ADDING A NEW AIRPORT STUFF", 1700,1000, stage, gridPane);

        Label ssn = SetStyles.createLabels("Please enter the SSN number of the airport stuff member:", 0,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaSSN,1,0);
        Label name = SetStyles.createLabels("Please enter the First Name of the airport stuff member:", 2,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaName,3,0);
        Label lastName = SetStyles.createLabels("Please enter the Last Name of the airport stuff member:", 4,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaLastName,5,0);
        Label address = SetStyles.createLabels("Please enter the address of the airport stuff member:", 6,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaAddress,7,0);
        Label phone = SetStyles.createLabels("Please enter the phone number of the airport stuff member:", 8,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaPhone,9,0);
        Label labelTypeOfEmployee = SetStyles.createLabels("Please enter the type of employee:", 10,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        combo3.setPromptText("Employee Type");
        GridPane.setRowIndex(combo3, 11);
        GridPane.setColumnIndex(combo3, 0);
        javafx.collections.ObservableList<String> list3 = combo3.getItems();
        list3.add("Store Stuff");
        list3.add("Gate Stuff");
        list3.add("CheckIn Stuff");
        Label gate = SetStyles.createLabels("Please enter the working gate of the airport stuff member:", 12,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaGate,13,0);
        Label store = SetStyles.createLabels("Please enter the working store of the airport stuff member:", 14,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaStore,15,0);
        Label airportcode = SetStyles.createLabels("Please pick airport ICAO:", 16,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        icao.setPromptText("Airport ICAO");
        GridPane.setRowIndex(icao, 17);
        GridPane.setColumnIndex(icao, 0);
        
        ObservableList<String> list = icao.getItems();
        for (Airport airport : ProgramData.getAirports()) {
            list.add(airport.getAirportICAO());
        }

        Label schedule = SetStyles.createLabels("Please pick days and start time for working schedule: ",0,
                3, Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        Label warning = SetStyles.createLabels("please type time in this form: HH:MM and if you have a day off type NULL",1,3,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
        CheckBox monday = SetStyles.createCheckBox("Monday",2, 3);
        CheckBox tuesday = SetStyles.createCheckBox("Tuesday",3,3);
        CheckBox wednesday = SetStyles.createCheckBox("Wednesday",4,3);
        CheckBox thursday = SetStyles.createCheckBox("Thursday",5,3);
        CheckBox friday = SetStyles.createCheckBox("Friday",6,3);
        CheckBox saturday = SetStyles.createCheckBox("Saturday",7,3);
        CheckBox sunday = SetStyles.createCheckBox("Sunday",8,3);

        SetStyles.setPosition(m,2,4);
        SetStyles.setPosition(tu,3,4);
        SetStyles.setPosition(w,4,4);
        SetStyles.setPosition(th,5,4);
        SetStyles.setPosition(f,6,4);
        SetStyles.setPosition(st,7,4);
        SetStyles.setPosition(sn,8,4);

        workingSchedule[0] = m.getText();
        workingSchedule[1] = tu.getText();
        workingSchedule[2] = w.getText();
        workingSchedule[3] = th.getText();
        workingSchedule[4] = f.getText();
        workingSchedule[5] = st.getText();
        workingSchedule[6] = sn.getText();
        System.out.println(workingSchedule[0]);


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
        SetStyles.setStyleForButtons(okButton, 30, 4);
        okButton.setOnMouseClicked(event -> {
            if (CheckAddingInput.checkSSN(textAreaSSN, stage)) {
                flag[0] = false;
            } else flag[0] = true;

            if (CheckAddingInput.checkPersonData(textAreaName,textAreaLastName,textAreaAddress,textAreaPhone,stage)) {
               flag[1] = false;
            } else flag[1] = true;

            if (CheckAddingInput.checkICAO(icao,stage)) {
                flag[2] = false;
            } else flag[2] = true;

            if (CheckAddingInput.checkScheduleDate(stage,monday,tuesday,wednesday,thursday,friday,saturday,sunday)) {
                flag[3] = false;
            } else flag[3] = true;

            if (CheckAddingInput.checkScheduleTime(stage,m,tu,w,th,f,st,sn)) {

                flag[4] = false;
            } else flag[4] = true;

            if(CheckAddingInput.checkStuff(combo3,stage)) {
                flag[5] = false;
            } else flag[5] = true;

            if (combo3.getValue().equals("Store Stuff")) {
                if (CheckAddingInput.checkStore(textAreaStore,stage)) {
                    flag[6] = false;
                } else {
                    flag[6] = true;
                }

            } else if (combo3.getValue().equals("Gate Stuff")) {
                try {
                    if (CheckAddingInput.checkGate(textAreaGate, stage)) {
                        flag[7] = false;
                    } else flag[7] = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else flag[8] = true;
            correctData();
        });

        gridPane.getChildren().addAll(ssn, textAreaSSN, name, textAreaName, lastName, textAreaLastName, address,
                textAreaAddress, phone, textAreaPhone, labelTypeOfEmployee , combo3, gate, textAreaGate,
                store , textAreaStore,icao,airportcode, schedule, monday, tuesday, wednesday, thursday, friday,
                saturday, sunday, m, tu, w, th, f, st, sn, warning,okButton );
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
    public static String getPhone() {
        return textAreaPhone.getText();
    }
    public static String getICAO() {
        return icao.getValue();
    }

    public static String getM() {
        return m.getText();
    }

    public static TextField getTu() {
        return tu;
    }

    public static TextField getW() {
        return w;
    }

    public static TextField getTh() {
        return th;
    }

    public static TextField getF() {
        return f;
    }

    public static TextField getSt() {
        return st;
    }

    public static TextField getSn() {
        return sn;
    }
    public static String getStore() {
        return textAreaStore.getText();
    }
    public static String getGate() {
        return textAreaGate.getText();
    }
    public static String[] getSchedule() {
        return workingSchedule;
    }

    // if all flags are true means all data are correct
    // and close stage
    public static void correctData() {
        if (flag[0] && flag[1] && flag[2] && flag[3] && flag[4] && flag[5]) {
            stage.close();
            if (flag[6]) {
                ProgramData.addStoreStuff();
            } else if (flag[7]) {
                ProgramData.addGateStuff();
            } else if (flag[8]) {
                ProgramData.addCheckInStuff();
            }
        }
    }
}
