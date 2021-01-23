package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.FileInputStream;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

/**
 * @class Entrance create the main window of our app
 * the user can select either a regular user or the administrator of the app
 */

public class Entrance extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(10);

        FileInputStream input = new FileInputStream("corona-virus-4984021_960_720.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
               BackgroundRepeat.NO_REPEAT,
               BackgroundRepeat.NO_REPEAT,
               BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, javafx.scene.layout.BackgroundSize.AUTO,
                        true, true, true, true));

        Background background = new Background(backgroundimage);

        Label welcome = new Label("WELCOME TO THE FIRST APPLICATION FOR COVID-19 IN AIRPORTS AROUND EUROPE!");

        welcome.setAlignment(Pos.TOP_CENTER);
        welcome.setFont(Font.font("Times New Roman",26));
        welcome.setTextFill(Paint.valueOf("black"));

        Label message = new Label("This application tries to look for suspected cases of the virus in the airports around Europe. ");
        Label user = new Label("Select the button USER in order to submit user's data" );
        Label admin = new Label("Select the button ADMINISTRATOR in order to add data. ");

        setLabel(message,5,0,Paint.valueOf("black"),Font.font("Times New Roman",22));
        setLabel(user,15,0,Paint.valueOf("black"),Font.font("Times New Roman",20));
        setLabel(admin,16,0,Paint.valueOf("black"),Font.font("Times New Roman",20));

        ToggleGroup group = new ToggleGroup();
        Button userButton = new Button("USER");
        sample.SetStyles.setStyleForButtons(userButton, 70, 200);
        Button adminButton = new Button("ADMINISTRATOR");
        sample.SetStyles.setStyleForButtons(adminButton, 70, 230);

        /**
         * if the user button is selected the user transferred to the main window for users
         * @see class MainWindowForUser
         */

        userButton.setOnMouseClicked(event -> {
            try {
                sample.MainWindowForUser.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        gridPane.getChildren().addAll(welcome, message, user, admin, userButton,adminButton);
        primaryStage.setTitle("Airport application for COVID-19");
        gridPane.setBackground(background);
        primaryStage.setScene(new Scene(gridPane, 1950, 1000));
        primaryStage.show();

    }

    public void setLabel(Label name, int row, int column, Paint color, Font font) {
        GridPane.setRowIndex(name,row);
        GridPane.setColumnIndex(name,column);
        name.setTextFill(color);
        name.setFont(font);

    }
}
