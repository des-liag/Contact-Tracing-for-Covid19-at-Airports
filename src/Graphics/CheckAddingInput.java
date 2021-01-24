package Graphics;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import  javafx.scene.layout.GridPane;
import  javafx.stage.Stage;
import javafx.scene.Scene;
import  javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import  javafx.scene.control.Label;
import  javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * @class CheckAddingInput check if the input from the administrator
 * is in the correct form and not null
 */
public class CheckAddingInput {

    static boolean checkSSN = false;
    static boolean checkICAO = false;

    public static void checkSSN(TextField textField, Stage stage) {
        if (textField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null Data");
            alert.setContentText("You forgot to enter SSN");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        if (textField.getText().length() != 9) {
            checkSSN = true;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Data");
            alert.setContentText("SSN number should have ONLY 9 digits");
            java.util.Optional<ButtonType> result2 = alert.showAndWait();
            if (result2.isPresent() && (result2.get() == javafx.scene.control.ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        } else {
            checkSSN = false;
        }
    }

    public static void checkAirport(TextField name, TextField icao,  Stage stage) {
        if (name.getText().equals("") || icao.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null Data");
            alert.setContentText("You forgot to enter data for the airport");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        int count = 0;
        char ch;
        if (icao.getText().length() == 4) {
            for (int i = 0; i < icao.getText().length(); i++) {
                ch = icao.getText().charAt(i);
                if (Character.isUpperCase(ch)) {
                    count++;
                }
            }
            if (count == 4) {
                checkICAO = true;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Data");
            alert.setContentText("The ICAO of the airport should have ONLY 4 CAPITAL LETTERS");
            java.util.Optional<ButtonType> result3 = alert.showAndWait();
            if (result3.isPresent() && (result3.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
    }

    public static void checkPersonData(TextField name, TextField lastname, TextField address, TextField phone, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null Data");
        if (name.getText().equals("")) {
            alert.setContentText("You forgot to enter First name");
            java.util.Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        if (lastname.getText().equals("")) {
            alert.setContentText("You forgot to enter Last name");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        if (address.getText().equals("")) {
            alert.setContentText("You forgot to enter address");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        if (phone.getText().equals("")) {
            alert.setContentText("You forgot to enter phone number");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }

    }

    public static void checkID(TextField id, Stage stage) {
        if (id.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null Data");
            alert.setContentText("You forgot to enter ID number");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
    }

    public static void checkLuggage(TextField luggage, Stage stage) {
        if (luggage.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null Data");
            alert.setContentText("You forgot to enter luggage");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        if (!luggage.getText().equals("TRUE") || luggage.getText().equals("FALSE")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Data");
            alert.setContentText("Please enter TRUE OR FALSE for luggage ");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
    }

    public static void checkGate(TextField gate, Stage stage) {
        if (gate.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Null Data");
            alert.setContentText("You forgot to enter gate");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
        char ch = gate.getText().charAt(1);
        if (gate.getText().length() == 1 || Character.isUpperCase(ch)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Data");
            alert.setContentText("The gate should have ONLY 1 CAPITAL LETTERS");
            java.util.Optional<ButtonType> result3 = alert.showAndWait();
            if (result3.isPresent() && (result3.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
    }

    public static void checkICAO(ComboBox icao, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null Data");
        if (icao.getValue() == null) {
            alert.setContentText("You forgot to pick airport ICAO");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
    }

    public static void checkDate(DatePicker date, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null Data");
        if (date.getValue() == null) {
            alert.setContentText("You forgot to enter date");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }

    }

    public static void checkStore(TextField store, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null Data");
        if (store.getText().equals("")) {
            alert.setContentText("You forgot to enter store");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == javafx.scene.control.ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
      //  char c = store.getText().getChars();
    }

    public static void checkStuff(ComboBox stuff, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null Data");
        if (stuff.getValue() == null) {
            alert.setContentText("You forgot to pick employee type ");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                stage.show();
            }
        }
    }
    /**
     * @param situation defines if the addition have been completed
     * show up suitable message for the administrator
     */
    public static void message(boolean situation) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Stage stage = new Stage();
        BackgroundFill background_fill = new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        gridPane.setBackground(background);
        stage.setScene(new Scene(gridPane, 800, 400));
        Label pressEXIT = SetStyles.createLabels("Press X to the top right of the window to leave the application ",
                4, 0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 18));

        if (situation) {
            stage.setTitle("COMPLETED");
            Label message = SetStyles.createLabels("The add had successfully completed! ",
                        3, 0,
                        Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
                gridPane.getChildren().addAll(message, pressEXIT);

        } else {
            stage.setTitle("Error");
            Label message = SetStyles.createLabels("Seems that something went wrong! ",
                    3, 0,
                    Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
            gridPane.getChildren().addAll(message, pressEXIT);

        }
        stage.show();
    }

}






