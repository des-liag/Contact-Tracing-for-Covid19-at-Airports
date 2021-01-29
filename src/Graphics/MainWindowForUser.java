package Graphics;

import Airports.ProgramData;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.Scene;


 public class MainWindowForUser {
    protected static TextField textArea1 = new TextField();
    protected static TextField textArea2 = new TextField();
    protected static PasswordField ps = new PasswordField();
    protected static ToggleGroup grp = new ToggleGroup();
    protected static ToggleGroup group = new ToggleGroup();
    protected static RadioButton test1 = new RadioButton("Diagnostic test");
    protected static RadioButton test2 = new RadioButton("Rapid test");
    protected static DatePicker date = new DatePicker();
    protected static RadioButton pas = new RadioButton("Passenger");
    protected static RadioButton emp = new RadioButton("Employee");
    protected static ComboBox<String> combo2 = new ComboBox<>();
    protected static ComboBox<String> icao = new ComboBox<>();

    public static TextField getTextArea1() {
        return textArea1;
    }

    public static TextField getTextArea2() {
        return textArea2;
    }

    public static ComboBox getICAO() { return icao;}

    public static PasswordField getPs() {
        return ps;
    }

    public static ToggleGroup getGrp() {
        return grp;
    }

    public static ToggleGroup getGroup() {
        return group;
    }

    public static RadioButton getTest1() {
        return test1;
    }

    public static RadioButton getTest2() {
        return test2;
    }

    public static DatePicker getDate() {
        return date;
    }

    public static RadioButton getPas() {
        return pas;
    }

    public static RadioButton getEmp() {
        return emp;
    }

    public static ComboBox<String> getCombo2() {
        return combo2;
    }
    
    

    public static void start(javafx.stage.Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(10);

        /**
         * @see class SetStyles and
         * @see method setBackground
         * create backgroynd for the window
         */
        Background background = SetStyles.setBackground("Ζωγραφική 3D 9_1_2021 8_41_53 μμ 2.png");

        /**
         * @fields labelFName, labelLName, labelSSNNumber, labelTest, labelDay,
         * labelTypeOfUser,labelTypeOfEmployee, labelWorkAirport
         * ask the user to enter his data.
         * Creation of the main window
         */

        Label labelFName = new Label("Please enter first name");
        SetStyles.setStyleForLabel(labelFName, 1, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForText(textArea1, 2);

        Label labelLName = new Label("Please enter last name");
        SetStyles.setStyleForLabel(labelLName, 3, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForText(textArea2, 4);

        Label labelSSNNumber = new Label("Please enter SSN Number");
        SetStyles.setStyleForLabel(labelSSNNumber, 5, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        GridPane.setRowIndex(ps, 6);
        GridPane.setColumnIndex(ps, 0);


        Label labelTest = new Label("Please select the type of the COVID-19 test from the following choices");
        SetStyles.setStyleForLabel(labelTest, 7, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForRadButtons(group, 8, test1);
        SetStyles.setStyleForRadButtons(group, 9, test2);


        Label labelDay = new Label("Please select the date of the results of the test");
        SetStyles.setStyleForLabel(labelDay, 10, Pos.TOP_LEFT,Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        GridPane.setRowIndex(date, 11);
        GridPane.setColumnIndex(date, 0);
        date.setEditable(false);

        Label labelTypeOfUser = new Label("Please select Passenger or Employee");
        SetStyles.setStyleForLabel(labelTypeOfUser, 12, Pos.TOP_LEFT,Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        SetStyles.setStyleForRadButtons(grp, 13, pas);
        SetStyles.setStyleForRadButtons(grp, 14, emp);

        Label labelTypeOfEmployee = new Label("Please select a type of Employee");
        SetStyles.setStyleForLabel(labelTypeOfEmployee, 15, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        combo2.setPromptText("Employee Type");
        GridPane.setRowIndex(combo2, 16);
        GridPane.setColumnIndex(combo2, 0);
        javafx.collections.ObservableList<String> list2 = combo2.getItems();
        list2.add("Flight Crew");
        list2.add("Store Stuff");
        list2.add("Gate Stuff");
        list2.add("CheckIn Stuff");

        Label labelWorkAirport = new Label("Please enter the ICAO of the working airport ");
        SetStyles.setStyleForLabel(labelWorkAirport, 17, Pos.TOP_LEFT, Paint.valueOf("white"), Font.font("Arial Rounded MT Bold", 22));
        icao.setPromptText("Airport ICAO");
        GridPane.setRowIndex(icao, 18);
        GridPane.setColumnIndex(icao, 0);

        ObservableList<String> list = icao.getItems();
        for (Airports.Airport airport : ProgramData.getAirports()) {
            list.add(airport.getAirportICAO());
        }

        // END OF CREATION OF THE MAIN WINDOW

        /**
         * @method pas.setOnAction inactive the comboBox of employee type
         * and the employee working airport ,if the user is a passenger
         */
        pas.setOnAction(event -> {
            combo2.setDisable(true);
            icao.setDisable(true);

        });

        /**
         * @method emp.setOnAction actives the comboBox of employee type
         * and the employee working airport, if the user is an employee
         */
        emp.setOnAction(event -> {
            combo2.setDisable(false);
            icao.setDisable(false);

        });

        /**
         * create buttons: OK for submit data
         *                HELP information about the application
         */
        Button okButton = new Button("OK");
        SetStyles.setStyleForButtons(okButton, 40, 200);
        Button helpButton = new Button("HELP");
        SetStyles.setStyleForButtons(helpButton, 40, 210);
        /**
         * @field helpButton If the HELP button is clicked show the window for the information
         */

        helpButton.setOnMouseClicked(event ->
            HelpInformation.start(gridPane));

        okButton.setOnMouseClicked(event -> {
            OkButtonHandle.checkInput(primaryStage);
            OkButtonHandle.ifNotNull(primaryStage);
        });
        Button buttonBack = new Button("BACK");
        SetStyles.setStyleForButtons(buttonBack, 40, 220);
        buttonBack.setOnMouseClicked(event -> {
            Entrance.switchWindow((javafx.stage.Stage) buttonBack.getScene().getWindow(), new Entrance());

        });

        gridPane.getChildren().addAll(labelFName, labelLName, textArea1, textArea2, labelSSNNumber, ps, labelTest, test1, test2,
                labelDay, date, labelTypeOfUser, pas, emp, labelTypeOfEmployee, combo2, okButton,
                helpButton, labelWorkAirport, icao, buttonBack);
        gridPane.setBackground(background);
        primaryStage.setTitle("Positive case");
        primaryStage.setScene(new Scene(gridPane, 1950, 1000));
        primaryStage.show();
    }

 }


