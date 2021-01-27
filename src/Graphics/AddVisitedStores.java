package Graphics;

import Airports.ProgramData;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import  javafx.scene.layout.GridPane;
import  javafx.stage.Stage;
import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import  javafx.scene.control.Button;
/**
 * @class AddVisitedStores adding a new store which a passenger can visit
 */

public class AddVisitedStores {
    protected static TextField textAreaSSN = new TextField();
    protected static DatePicker entranceDate = new DatePicker();
    protected static TextField textAreaFlightID = new TextField();
    protected static TextField storeName = new TextField();
    protected static TextField  entranceTime = new TextField();
    // array flag saves false or true if the check method / methods from class CheckAddingInput
    // had been done
    static final boolean[] flag = {false,false,false,false};
    static Stage stage = new Stage();

    public static void newVisitedStores () {

        GridPane gridPane = new GridPane();
        SetStyles.creatWindow("ADDING A NEW VISITED STORE",900,700,stage,gridPane);

        Label ssn = SetStyles.createLabels("Please enter passenger's SSN number:", 1,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaSSN,2,0);
        Label id = SetStyles.createLabels("Please enter ticket's Flight ID :", 3,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaFlightID,4,0);
        Label entrance = SetStyles.createLabels("Please enter passenger's entrance date in the store: ", 5,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        GridPane.setRowIndex(entranceDate,6 );
        GridPane.setColumnIndex(entranceDate, 0);
        Label time = SetStyles.createLabels("Please enter passenger's entrance time in the store: ", 7,0,
                 Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        Label warning = SetStyles.createLabels("please type the time in this form: HH:MM:SS",8,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
        SetStyles.setPosition(entranceTime,9,0);
        Label store = SetStyles.createLabels("Please enter the store which the passenger visited:", 10,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(storeName,11,0);

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 35,25);
        okButton.setOnMouseClicked(event -> {
            if (CheckAddingInput.checkSSN(textAreaSSN, stage)) {
                flag[0] = false;
            } else flag[0] = true;

            if (CheckAddingInput.checkID(textAreaFlightID,stage)) {
                flag[1] = false;
            } else flag[1] = true;

            if (CheckAddingInput.checkEntrance(entranceDate, entranceTime,stage)) {
                flag[2] = false;
            } else flag[2] = true;
            if (CheckAddingInput.checkStore(storeName,stage)) {
                flag[3] = false;
            } else flag[3] = true;
            correctData();
        });

        gridPane.getChildren().addAll(ssn,textAreaSSN,id,textAreaFlightID,entrance, entranceDate,
                warning,storeName,store, time, entranceTime, okButton);
    }

    public static String getEntrance() {
        return entranceDate.getValue().toString();
    }
    public static String getID() {
        return textAreaFlightID.getText();
    }
    public static String getSSN() { 
        return textAreaSSN.getText(); 
    }
    public static String getStore() {
        return storeName.getText();
    }
    public static String getTime() {
        return entranceTime.getText();
    }
    // if all flags are true means all data are correct
    // and close stage
    public static void correctData() {
        if (flag[0] && flag[1] && flag[2] && flag[3]) {
            stage.close();
            ProgramData.addVisitedStore();
        }
    }

}
