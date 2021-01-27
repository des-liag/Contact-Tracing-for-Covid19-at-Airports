package Graphics;

import Airports.ProgramData;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OkButtonHandle  {
    private static boolean checkSSN = false;
    private static boolean checkICAO = false;
    /**
     * @method checkInput check if any data is null
     * and create a warning window which inform the user
     * what field he left null
     */
    public static void checkInput(Stage primaryStage)  {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Null Data");
        if (MainWindowForUser.textArea1.getText().equals("")) {
            alert.setContentText("You forgot to enter First name");
            java.util.Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                primaryStage.show();
            }
        }
        if (MainWindowForUser.textArea2.getText().equals("")) {
            alert.setContentText("You forgot to enter Last name");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                primaryStage.show();
            }
        }
        if (MainWindowForUser.ps.getText().equals("")) {
            alert.setContentText("You forgot to enter SSN");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                primaryStage.show();
            }
            //check if the SSN have 9 digits and
            //if not ,create a warning window to inform the user
        } else if (MainWindowForUser.ps.getText().length() != 9) {
            checkSSN = true;
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Invalid Data");
            alert2.setContentText("SSN number should have ONLY 9 digits");
            java.util.Optional<ButtonType> result2 = alert2.showAndWait();
            if (result2.isPresent() && (result2.get() == ButtonType.OK)) {
                alert2.close();
                primaryStage.show();
            }
        } else checkSSN = false;
        if (MainWindowForUser.date.getValue() == null) {
            alert.setContentText("You forgot to enter date");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                primaryStage.show();
            }
        }
        if (MainWindowForUser.group.getSelectedToggle() == null) {
            alert.setContentText("You forgot to pick test type ");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                primaryStage.show();
            }
        }
        if (MainWindowForUser.grp.getSelectedToggle() == null) {
            alert.setContentText("You forgot to pick user type ");
            java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
            if (result.isPresent() && (result.get() == ButtonType.OK)) {
                alert.close();
                primaryStage.show();
            }
        }
        if (MainWindowForUser.emp.isSelected()) {
            if (MainWindowForUser.textArea3.getText().equals("")) {
                alert.setContentText("You forgot to enter ICAO for airport");
                java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    alert.close();
                    primaryStage.show();
                }
            }
            //check if the ICAO have 4 capital letters and
            // if not, create a warning window to inform the user
            int count = 0;
            char ch;
            if (MainWindowForUser.textArea3.getText().length() == 4 ) {
                for (int i = 0; i < MainWindowForUser.textArea3.getText().length(); i++) {
                    ch = MainWindowForUser.textArea3.getText().charAt(i);
                    if (Character.isUpperCase(ch)) {
                        count++;
                    }
                }
                if (count == 4) {
                    checkICAO = true;
                }
            } else  {
                Alert alert3 = new Alert(Alert.AlertType.WARNING);
                alert3.setTitle("Invalid Data");
                alert3.setContentText("The ICAO of the airport should have ONLY 4 CAPITAL LETTERS");
                java.util.Optional<javafx.scene.control.ButtonType> result3 = alert3.showAndWait();
                if (result3.isPresent() && (result3.get() == ButtonType.OK)) {
                    alert3.close();
                    primaryStage.show();
                }
            }
            if (MainWindowForUser.combo2.getValue() == null) {
                alert.setContentText("You forgot to pick employee type ");
                java.util.Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
                if (result.isPresent() && (result.get() == ButtonType.OK)) {
                    alert.close();
                    primaryStage.show();
                }
            }
        }
    }

    /**
     * @method ifNotNull checking if all data are properly filled in and SSN and ICAO constrains are applied
     * call method start
     */
    public static void ifNotNull(Stage primaryStage) {
        if (MainWindowForUser.textArea1.getText() != null && MainWindowForUser.textArea2.getText() != null && MainWindowForUser.ps.getText() != null
                && MainWindowForUser.date.getValue() != null && MainWindowForUser.group.getSelectedToggle() != null
                && MainWindowForUser.grp.getSelectedToggle() != null && !checkSSN ) {
            if (MainWindowForUser.pas.isSelected()) {
                start(primaryStage);
            } else if (checkICAO && MainWindowForUser.combo2.getValue() != null) {
                   start(primaryStage);
            }
        }
    }

    /**
     * @method start create a confirmation window so the user can be sure about sending his data
     * If user press OK , a new waiting scene is created
     * If user press CANCEL he can go back to the main window
     */
    public static void start(Stage primaryStage)  {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Send your data");
        alert.setContentText("Are you sure? Press OK to confirm, or Cancel to back out.");
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            primaryStage.close();
            searchForTracers();
        } else alert.close();
    }
    

    public static void searchForTracers() {
        if(MainWindowForUser.getPas().isSelected()) {
            ProgramData.searchForPassenger();
        } else if(MainWindowForUser.getCombo2().getValue().equals("Flight Crew")) {
            ProgramData.searchForFlightCrew();
        } else if(MainWindowForUser.getCombo2().getValue().equals("CheckIn Stuff")) {
            ProgramData.searchForCheckInStuff();
        } else if(MainWindowForUser.getCombo2().getValue().equals("Store Stuff")){
            ProgramData.searchForStroreStuff();
        } else if(MainWindowForUser.getCombo2().getValue().equals("Gate Stuff")){
            ProgramData.searchForGateStuff();
        }
    }
}


