
import javafx.scene.control.*;
import java.util.ArrayList;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Output {

    /**
     * @method employeeNotFound create error window
     * to inform the user that this employee does not exist
     */
    public static void employeeNotFound()  {
        createAlert("We can not found employee with SSN " + MainWindowForUser.ps.getText() +
                " working on airport with " + MainWindowForUser.textArea3.getText() + " ICAO " +
                "               Please press OK to exit the application");
    }
    /**
     * @method icaoNotFound create error window
     * to inform the user that this ICAO does not exist
     */
    public static void icaoNotFound() {
      createAlert("We can not found airport with " + MainWindowForUser.textArea3.getText() + " ICAO " +
              "     Please press OK to exit the application");
    }
    /**
     * @method dateOutOfBounds create error window
     * to inform the user that the date of the test
     * took place over the past month
     */

    public static void dateOutOfBounds() {
        createAlert("Has been a long time since this COVID-19 test took place. There is no risk of contagion." +
               "          Please press OK to exit the application ");
    }

    /**
     *
     * @param closeContacts contains people that should be informed such as high risk tracers
     * @param casualContacts contains people that should be informed such as low risk tracers
     * suitable messages if the lists with contacts are null
     */
    public static void contacts(ArrayList<String> closeContacts, ArrayList<String> casualContacts) {
        GridPane gridPane = new GridPane();
        Stage stage = new Stage();
        SetStyles.creatWindow("CONTACTS TO BE INFORMED",1000,800,stage,gridPane);

        ListView<String> listViewCloseContacts = new ListView<>();
        ListView<String> listViewCasualContacts = new ListView<>();

        if (MainWindowForUser.pas.isSelected()) {
            if (casualContacts.size() != 0 && closeContacts.size() != 0) {
                for (String closecontacts : closeContacts) {
                    listViewCloseContacts.getItems().add(closecontacts);
                }
                for (String casualcontacts : casualContacts) {
                    listViewCasualContacts.getItems().add(casualcontacts);
                }
                Label labelclose = SetStyles.createLabels("CLOSE CONTACTS", 0, 0, Paint.valueOf("black"),
                        Font.font("Arial Rounded MT Bold", 24));
                Label message = SetStyles.createLabels("HIGH RISK OF CONTAGION!", 1, 0, Paint.valueOf("red"),
                        Font.font("Arial Rounded MT Bold", 18));
                VBox vbox3 = SetStyles.createVBox(5.0, 2, 1,  labelclose, message, listViewCloseContacts);

                Label labelcasual = SetStyles.createLabels("CASUAL CONTACTS", 0, 4, Paint.valueOf("black"),
                        Font.font("Arial Rounded MT Bold", 24));
                Label message2 = SetStyles.createLabels("LOW RISK OF CONTAGION!", 1, 4, Paint.valueOf("green"),
                        Font.font("Arial Rounded MT Bold", 18));
                VBox vbox4 = SetStyles.createVBox(5.0, 2, 4,  labelcasual, message2, listViewCasualContacts);
                gridPane.getChildren().addAll(vbox3, vbox4);

            } else {
                createAlert("There are not tickets for passenger with SSN " +MainWindowForUser.ps.getText() +
                        " ,so there is no risk of contagion");
                }
        }
        if (MainWindowForUser.combo2.getValue().equals("Flight Crew") || MainWindowForUser.combo2.getValue().equals("CheckIn Stuff")) {
            if (closeContacts.size() != 0) {
                for (String closecontacts : closeContacts) {
                    listViewCloseContacts.getItems().add(closecontacts);
                }
                Label labelclose = SetStyles.createLabels("CLOSE CONTACTS", 0, 0, Paint.valueOf("black"),
                        Font.font("Arial Rounded MT Bold", 24));
                Label message = SetStyles.createLabels("HIGH RISK OF CONTAGION!", 1, 0, Paint.valueOf("red"),
                        Font.font("Arial Rounded MT Bold", 18));

                VBox vbox1 = SetStyles.createVBox(5.0, 2, 0, labelclose, message, listViewCloseContacts);
                gridPane.getChildren().addAll(vbox1);

            } else
                if (MainWindowForUser.combo2.getValue().equals("Flight Crew")) {
                createAlert("There are not flights, containing the employee with SSN " + MainWindowForUser.ps.getText() +
                        " that need to be informed.                                       " +
                        "                        Please press OK to exit the application");
            } else {
                createAlert("There are not contacts to be informed. No risk of contagion                             " +
                        "                              Please press OK to exit the application");
                }
        } else if (MainWindowForUser.combo2.getValue().equals("Gate Stuff") || MainWindowForUser.combo2.getValue().equals("Store Stuff")) {
            if (casualContacts.size() != 0) {
                for (String casualcontacts : casualContacts) {
                    listViewCasualContacts.getItems().add(casualcontacts);
                }
                Label labelcasual = SetStyles.createLabels("CASUAL CONTACTS", 0, 4, Paint.valueOf("black"),
                        Font.font("Arial Rounded MT Bold", 24));
                Label message = SetStyles.createLabels("LOW RISK OF CONTAGION!", 1, 4, Paint.valueOf("green"),
                        Font.font("Arial Rounded MT Bold", 18));

                VBox vbox2 = SetStyles.createVBox(5.0, 2, 4, labelcasual, message, listViewCasualContacts);
                gridPane.getChildren().addAll(vbox2);

            } else {
                if (MainWindowForUser.combo2.getValue().equals("Store Stuff")) {
                    createAlert("There are not contacts to be informed. No risk of contagion                             " +
                            "                              Please press OK to exit the application");
                } else if (MainWindowForUser.combo2.getValue().equals("Gate Stuff")) {
                    createAlert("There are not contacts to be informed. No risk of contagion                             " +
                            "                              Please press OK to exit the application");
                }
            }
        }
    }

    public static void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data Not Found");
        alert.setContentText(message);
        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && (result.get() == ButtonType.OK)) {
            alert.close();
        }
    }


}










