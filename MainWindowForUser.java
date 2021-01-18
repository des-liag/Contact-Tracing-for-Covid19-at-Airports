package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.io.FileInputStream;

 public class MainWindowForUser {
    protected static TextField textArea1 = new TextField();
    protected static TextField textArea2 = new TextField();
    protected static TextField textArea3 = new TextField();
    protected static PasswordField ps = new PasswordField();
    protected static ToggleGroup grp = new ToggleGroup();
    protected static ToggleGroup group = new ToggleGroup();
    protected static RadioButton test1 = new RadioButton("Diagnostic test");
    protected static RadioButton test2 = new RadioButton("Rapid test");
    protected static DatePicker date = new DatePicker();
    protected static RadioButton pas = new RadioButton("Passenger");
    protected static RadioButton emp = new RadioButton("Employee");
    protected static ComboBox<String> combo2 = new ComboBox<>();

    public static void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(10);

        // input an image to use as a background

        FileInputStream input = new FileInputStream("Ζωγραφική 3D 9_1_2021 8_41_53 μμ 2.png");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                        true, true, true, true));

        Background background = new Background(backgroundimage);

        /**
         * @fields labelFName, labelLName, labelSSNNumber, labelTest, labelDay,
         * labelTypeOfUser,labelTypeOfEmployee, labelWorkAirport
         * ask the user to enter his data.
         * Creation of the main window
         */

        Label labelFName = new Label("Please enter your first name");
        SetStyles.setStyleForLabel(labelFName, 1, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForText(textArea1, 2);

        Label labelLName = new Label("Please enter your last name");
        SetStyles.setStyleForLabel(labelLName, 3, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForText(textArea2, 4);

        Label labelSSNNumber = new Label("Please enter your SSN Number");
        SetStyles.setStyleForLabel(labelSSNNumber, 5, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        GridPane.setRowIndex(ps, 6);
        GridPane.setColumnIndex(ps, 2);


        Label labelTest = new Label("Please select the type of the COVID-19 test from the following choices");
        SetStyles.setStyleForLabel(labelTest, 7, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForRadButtons(group, 8, test1);
        SetStyles.setStyleForRadButtons(group, 9, test2);

        Label labelDay = new Label("Please select the date when you took the result of the test");
        SetStyles.setStyleForLabel(labelDay, 10, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        GridPane.setRowIndex(date, 11);
        GridPane.setColumnIndex(date, 2);
        date.setEditable(false);

        Label labelTypeOfUser = new Label("Please select whether you are a Passenger or an Employee");
        SetStyles.setStyleForLabel(labelTypeOfUser, 12, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForRadButtons(grp, 13, pas);
        SetStyles.setStyleForRadButtons(grp, 14, emp);

        Label labelTypeOfEmployee = new Label("If you are an Employee please select the type of Employee you are");
        SetStyles.setStyleForLabel(labelTypeOfEmployee, 15, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        combo2.setPromptText("Employee Type");
        GridPane.setRowIndex(combo2, 16);
        GridPane.setColumnIndex(combo2, 2);
        ObservableList<String> list2 = combo2.getItems();
        list2.add("Flight Crew");
        list2.add("Store Stuff");
        list2.add("Gate Stuff");
        list2.add("Check-in stuff");

        Label labelWorkAirport = new Label("If you are an Employee please enter the ICAO of the airport that you are working in ");
        SetStyles.setStyleForLabel(labelWorkAirport, 17, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForText(textArea3, 18);

        // END OF CREATION OF THE MAIN WINDOW

        /**
         * @method pas.setOnAction inactive the comboBox of employee type
         * and the employee working airport ,if the user is a passenger
         */

        pas.setOnAction(event -> {
            if (pas.isSelected()) {
                combo2.setDisable(true);
                textArea3.setDisable(true);
            }
        });
        /**
         * @method emp.setOnAction actives the comboBox of employee type
         * and the employee working airport, if the user is an employee
         */

        emp.setOnAction(event -> {
            if (emp.isSelected()) {
                combo2.setDisable(false);
                textArea3.setDisable(false);
            }
        });
        /**
         * create buttons: OK so the user submit his data
         *                HELP so the user can see information about the application
         */

        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 40, 200);
        Button helpButton = new Button("HELP");
        SetStyles.setStyleForButtons(helpButton, 40, 210);
        /**
         * @field helpButton If the HELP button is clicked show the window for the information
         */

        helpButton.setOnMouseClicked(event ->
                sample.HelpInformation.start(gridPane));

        okButton.setOnMouseClicked(event -> {
            sample.OkButtonHandle.checkInput(primaryStage);
            sample.OkButtonHandle.ifNotNull(primaryStage);

        });

        gridPane.getChildren().addAll(labelFName, labelLName, textArea1, textArea2, labelSSNNumber, ps, labelTest, test1, test2,
                labelDay, date, labelTypeOfUser, pas, emp, labelTypeOfEmployee, combo2, okButton,
                helpButton, labelWorkAirport, textArea3);
        gridPane.setBackground(background);
        primaryStage.setTitle("Airport application for COVID-19");
        primaryStage.setScene(new Scene(gridPane, 1950, 1000));
        primaryStage.show();
    }
 }


