package sample;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class HelpInformation {

    public static void start(GridPane gridPane) {
        Dialog dialog = new Dialog<>();
        dialog.initOwner(gridPane.getScene().getWindow());
        dialog.setTitle("HELP");
        dialog.setHeaderText("Information for the user");
        dialog.setContentText("Please use this application only if you are or you were positive to COVID-19 within the past month. " +
                "Also keep in mind that your SSN number must have ONLY 9 digits. Employees should know the ICAO of their working " +
                "airport which consists of 4 capital letters. Finally you have to fill in ALL the required data in order to have " +
                "results for your COVID contacts. ");
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
    }
}
