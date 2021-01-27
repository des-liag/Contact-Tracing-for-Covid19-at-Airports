package Graphics;

import Airports.ProgramData;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

/**
 * @class Entrance create the main window of our app
 * the user can select either a regular user or the administrator of the app
 */
public class Entrance extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        ProgramData.loadData();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(10);
        /**
         * @see class SetStyles and
         * @see method setBackground
         * create backgroynd for the window
         */
        Background background = SetStyles.setBackground("corona-virus-4984021_960_720.jpg");

        Label welcome = new Label("WELCOME TO THE FIRST APPLICATION FOR COVID-19 IN AIRPORTS AROUND EUROPE!");
        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setFont(Font.font("Times New Roman", 26));
        welcome.setTextFill(Paint.valueOf("black"));

        Label message = new Label("This application tries to look for suspected cases of the virus in the airports around Europe. ");
        Label user = new Label("Select the button USER in order to submit user's data");
        Label admin = new Label("Select the button ADD AIRPORT DATA in order to add data for airports. ");

        setLabel(message, 5, 0, Paint.valueOf("black"), Font.font("Times New Roman", 22));
        setLabel(user, 15, 0, Paint.valueOf("black"), Font.font("Times New Roman", 20));
        setLabel(admin, 16, 0, Paint.valueOf("black"), Font.font("Times New Roman", 20));

        Button userButton = new Button("USER");
        SetStyles.setStyleForButtons(userButton, 70, 200);
        Button addButton = new Button("ADD AIRPORT DATA");
        SetStyles.setStyleForButtons(addButton, 70, 230);

        /**
         * if the user button is selected the user transferred to the main window for users
         * @see class MainWindowForUser
         */
        userButton.setOnMouseClicked(event -> {
        try {
            MainWindowForUser.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        });

        addButton.setOnMouseClicked(event -> {
            primaryStage.close();
//            ProgramData data = new ProgramData();
            AddingData.start();
        });

        gridPane.getChildren().addAll(welcome, message, user, admin, userButton, addButton);
        primaryStage.setTitle("Airport application for COVID-19");
        gridPane.setBackground(background);
        primaryStage.setScene(new Scene(gridPane, 1950, 1000));
        primaryStage.show();
    }

    public void setLabel(Label name, int row, int column, Paint color, Font font) {
        GridPane.setRowIndex(name, row);
        GridPane.setColumnIndex(name, column);
        name.setTextFill(color);
        name.setFont(font);
    }

    public static void main(String[] args) {
        ProgramData.loadData();
        launch(args);
        ProgramData.saveData(); 
    }


}
