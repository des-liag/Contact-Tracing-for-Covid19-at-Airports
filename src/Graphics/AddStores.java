package Graphics;

import Airports.Airport;
import Airports.ProgramData;
import javafx.collections.ObservableList;
import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import  javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
/**
 * @class AddStores adding a new store to a specific airport
 */
public class AddStores {
    protected static TextField textAreaStore = new TextField();
    protected static ComboBox<String> icao = new ComboBox<>();
    // array flag saves false or true if the check method / methods from class CheckAddingInput
    // had been done
    static final boolean[] flag = {false, false};
    static Stage stage = new Stage();

    public static void newStore() {
        GridPane gridPane = new GridPane();
        SetStyles.creatWindow("ADDING A NEW STORE IN AN AIRPORT", 700,450, stage, gridPane);

        Label gateName = SetStyles.createLabels("Please enter store name:", 1, 0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setPosition(textAreaStore,2,0);
        Label airportcode = SetStyles.createLabels("Please pick airport ICAO:", 3,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        icao.setPromptText("Airport ICAO");
        GridPane.setRowIndex(icao, 4);
        GridPane.setColumnIndex(icao, 0);
        ObservableList<String> list = icao.getItems();
        for (Airport airport : ProgramData.getAirports()) {
            list.add(airport.getAirportICAO());
        }

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 26, 30);
        okButton.setOnMouseClicked(event -> {
            if (Graphics.CheckAddingInput.checkStore(textAreaStore,stage)) {
                flag[0] = false;
            } else flag[0] = true;

            if (CheckAddingInput.checkICAO(icao,stage)) {
                flag[1] = false;
            } else flag[1] = true;
            correctData();
        });

        gridPane.getChildren().addAll(textAreaStore,icao,gateName,airportcode,okButton);

    }

    public static String getStore() {
        return textAreaStore.getText();
    }
    public static String getICAO() {
        return icao.getValue();
    }
    // if all flags are true means all data are correct
    // and close stage
    public static void correctData() {
        if (flag[0] && flag[1]) {
            stage.close();
            ProgramData.addStore();
        }
    }

}

