package Graphics;

import Airports.ProgramData;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
/**
 * @class AddAirport adding a new airport
 */

public class AddAirport {
    protected static TextField textAreaName = new TextField();
    protected static TextField icao = new TextField();
    static final boolean[] flag = {false};
    static Stage stage = new Stage();

    public static void newAirport() {
        GridPane gridPane = new GridPane();
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
           if (CheckAddingInput.checkAirport(textAreaName,icao,stage)) {
               flag[0] = true;
           } else flag[0] =false;
           correctData();
        });

//        ProgramData.addAirport();
        gridPane.getChildren().addAll(textAreaName,airportName,icao,airportICAO,okButton);

    }

    public static String getName() {
        return textAreaName.getText();
    }
    public static String getICAO() {
        return icao.getText();
    }

    public static void correctData() {
        if (flag[0] && !textAreaName.getText().equals("") && !icao.getText().equals("")) {
            stage.close();
            ProgramData.addAirport();
        }
    }
}
