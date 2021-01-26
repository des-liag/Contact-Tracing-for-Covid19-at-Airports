package Graphics;

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
    protected static DatePicker entranceTime = new DatePicker();
    protected static TextField textAreaFlightID = new TextField();
    protected static TextField storeName = new TextField();

    public static void newVisitedStores () {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        SetStyles.creatWindow("ADDING A NEW VISITED STORE",900,700,stage,gridPane);

        Label ssn = SetStyles.createLabels("Please enter passenger's SSN number:", 1,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaSSN,2,0);
        Label id = SetStyles.createLabels("Please enter passenger's Flight ID :", 3,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaFlightID,4,0);
        Label entrance = SetStyles.createLabels("Please enter passenger's entrance time in the store: ", 5,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        Label warning = SetStyles.createLabels("please type next to date the time in this form: THH:MM:SS",6,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
        GridPane.setRowIndex(entranceTime,7 );
        GridPane.setColumnIndex(entranceTime, 0);
        entranceTime.setEditable(true);
        Label store = SetStyles.createLabels("Please enter the store which the passenger visited:", 8,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(storeName,9,0);

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 35,25);
        okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkSSN(textAreaSSN,stage);
            CheckAddingInput.checkID(textAreaFlightID,stage);
            CheckAddingInput.checkDate(entranceTime,stage);
            CheckAddingInput.checkStore(storeName,stage);
        });

        gridPane.getChildren().addAll(ssn,textAreaSSN,id,textAreaFlightID,entrance, entranceTime,
                warning,storeName,store, okButton);
    }

    public static String getEntrance() {
        return entranceTime.getValue().toString();
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
}
