package sample;
import javafx.scene.control.*;



public class Output {
    /**
     * @method employeeNotFound create error window
     * to inform the user that this employee does not exist
     */
    public static void employeeNotFound()  {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data Not Found");
        if (MainWindowForUser.emp.isSelected()) {
            alert.setContentText("We can not found employee with SSN " + sample.MainWindowForUser.ps.getText() +
                    " working on airport with " +sample.MainWindowForUser.textArea3.getText() + " ICAO " +
                    "               Please press OK to leave the application");
        }
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            alert.close();
        }
    }
    /**
     * @method icaoNotFound create error window
     * to inform the user that this ICAO does not exist
     */
    public static void icaoNotFound() {
       Alert alert = new Alert(Alert.AlertType.ERROR);
       alert.setTitle("Data Not Found");
       alert.setContentText("We can not found airport with " + sample.MainWindowForUser.textArea3.getText() + " ICAO " +
               "     Please press OK to leave the application");
       java.util.Optional<ButtonType> result = alert.showAndWait();
       if (result.isPresent() && (result.get() == ButtonType.OK)) {
           alert.close();
       }
    }
    /**
     * @method dateOutOfBounds create error window
     * to inform the user that the date of the test
     * took place over the past month
     */

    public static void dateOutOfBounds() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data Not Found");
        alert.setContentText("Has been a long time since this COVID-19 test took place. There is no risk of contagion." +
                "  Please press OK to leave the application ");
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            alert.close();
        }
    }





}





