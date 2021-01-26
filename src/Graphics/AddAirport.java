package Graphics;

import  javafx.scene.layout.GridPane;
import  javafx.stage.Stage;
import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import  javafx.scene.control.Button;
/**
 * @class AddAirport adding a new airport
 */

public class AddAirport {
    protected static TextField textAreaName = new TextField();
    protected static TextField icao = new TextField();

    public static void newAirport() {
        GridPane gridPane = new GridPane();
        Stage stage = new Stage();
        SetStyles.creatWindow("ADDING A NEW AIRPORT", 700,300, stage, gridPane);

        Label airportName = SetStyles.createLabels("Please enter the name of the airport:", 1,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaName,2,0);
        Label airportICAO = SetStyles.createLabels("Please enter the ICAO code of the airport:",3,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(icao,4,0);
        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 12,20 );
        okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkAirport(textAreaName,icao,stage);
        });

        gridPane.getChildren().addAll(textAreaName,airportName,icao,airportICAO,okButton);

    }

    public static String getName() {
        return textAreaName.getText();
    }
    public static String getICAO() {
        return icao.getText();
    }

}
