import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class HelpInformation {

    public static void start(GridPane gridPane) {
        Dialog dialog = new Dialog<>();
        dialog.initOwner(gridPane.getScene().getWindow());
        dialog.setTitle("HELP");
        dialog.setHeaderText("Information for the user");
        dialog.setContentText("Keep in mind that SSN number must have ONLY 9 digits. For Employees ICAO of their working " +
                "airport is required and consists of 4 capital letters. You have to fill in ALL the required data in order to have " +
                "results for COVID contacts." +
                "Finally the date of the test should be within the past month" );
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.showAndWait();
    }
}
