package Graphics;

import  javafx.scene.layout.GridPane;
import  javafx.stage.Stage;
import  javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.Scene;
import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

/**
 * @class AddFlight adding a new flight
 */
public class AddFlight {
    // departure airport
    protected static ComboBox<String> depICAO = new ComboBox<>();
    //destination airport
    protected static ComboBox<String> destICAO = new ComboBox<>();
    // departure date and time
    protected static DatePicker depTime = new DatePicker();
    //destination date and time
    protected static DatePicker destTime = new DatePicker();

    public static void addNewFlight() {
        Stage stage = new Stage();
        GridPane gridPane = new GridPane();
        SetStyles.creatWindow("ADDING A NEW FLIGHT", 700,600, stage, gridPane);

        Label dpICAO = SetStyles.createLabels("Please enter departure airport ICAO:", 1,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        depICAO.setPromptText("Departure airport");
        GridPane.setRowIndex(depICAO, 2);
        GridPane.setColumnIndex(depICAO, 0);
        javafx.collections.ObservableList<String> list = depICAO.getItems();

        Label dsICAO = SetStyles.createLabels("Please enter destination airport ICAO:", 3,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        destICAO.setPromptText("Departure airport");
        GridPane.setRowIndex(destICAO, 4);
        GridPane.setColumnIndex(destICAO, 0);
        list = destICAO.getItems();

        Label departure = SetStyles.createLabels("Please enter departure date and time: ", 5,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        Label warning = SetStyles.createLabels("please type next to date the time in this form: THH:MM:SS",6,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
        GridPane.setRowIndex(depTime,7 );
        GridPane.setColumnIndex(depTime, 0);
        depTime.setEditable(true);

        Label destination = SetStyles.createLabels("Please enter destination date and time: ", 8,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
        Label warning2 = SetStyles.createLabels("please type next to date the time in this form: THH:MM:SS",9,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 14));
        GridPane.setRowIndex(destTime,10 );
        GridPane.setColumnIndex(destTime, 0);
        destTime.setEditable(true);

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 26, 18);
        okButton.setOnMouseClicked(event -> {
            CheckAddingInput.checkICAO(depICAO,stage);
            CheckAddingInput.checkICAO(destICAO,stage);
            CheckAddingInput.checkDate(destTime,stage);
            CheckAddingInput.checkDate(depTime,stage);
        });

        gridPane.getChildren().addAll(depICAO,departure, destICAO, destination, depTime, destTime, warning, warning2,
                okButton, dsICAO, dpICAO);
    }

    public static String getDpICAO() {return  depICAO.getValue();}
    public static String getDsICAO() {return  destICAO.getValue();}
    public static String getDestTime() {return destTime.getValue().toString();}
    public static String getDepTime() {return depTime.getValue().toString();}
}
