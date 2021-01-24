package Graphics;

import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import  javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class AddFlightCrew {
    protected static TextField textAreaSSN = new TextField();
    protected static TextField textAreaName = new TextField();
    protected static TextField textAreaLastName = new TextField();
    protected static TextField textAreaAddress = new TextField();
    protected static TextField textAreaPhone = new TextField();
    protected static TextField textAreaFlightID = new TextField();

    public static void newFlightCrew() {

        GridPane gridPane = new GridPane();
        Stage stage = new Stage();
        SetStyles.creatWindow("ADDING A NEW FLIGHT CREW", 1000,800, stage, gridPane);
        Label ssn = SetStyles.createLabels("Please enter the SSN number of the flight crew member:", 3,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaSSN,4,0);
        Label name = SetStyles.createLabels("Please enter the First Name of the flight crew member:", 5,0,
               Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaName,6,0);
        Label lastName = SetStyles.createLabels("Please enter the Last Name of the flight crew member:", 7,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaLastName,8,0);
        Label address = SetStyles.createLabels("Please enter the address of the flight crew member:", 9,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaAddress,10,0);
        Label phone = SetStyles.createLabels("Please enter the phone number of the flight crew member:", 11,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaPhone,12,0);
        Label id = SetStyles.createLabels("Please enter the Flight ID of the flight crew member:", 13,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaFlightID,14,0);

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 40, 30);
        okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkSSN(textAreaSSN,stage);
            CheckAddingInput.checkPersonData(textAreaName,textAreaLastName,textAreaAddress,textAreaPhone,stage);
            CheckAddingInput.checkID(textAreaFlightID,stage);
        });
        gridPane.getChildren().addAll(ssn,textAreaSSN, name, textAreaName, lastName, textAreaLastName, address,
              textAreaAddress, phone, textAreaPhone, id, textAreaFlightID, okButton)  ;
    }

    public static String getSSN() { return textAreaSSN.getText(); }
    public static String getName() {return textAreaName.getText();}
    public static String getLastName() {return textAreaLastName.getText();}
    public static String getAddress() {return textAreaAddress.getText();}
    public  static String getPhone() {return textAreaPhone.getText();}
    public static String getID() {return textAreaFlightID.getText();}


}
