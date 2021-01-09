package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception{

        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(10);

//        javafx.scene.layout.BackgroundFill background_fill = new javafx.scene.layout.BackgroundFill(Color.ORANGE,
//             CornerRadii.EMPTY, Insets.EMPTY);
//        javafx.scene.layout.Background backgroundCOLOR = new javafx.scene.layout.Background(background_fill);

        FileInputStream input2 = new FileInputStream("using-video-to-contain-coronavirus.png");
        Image image2 = new Image(input2);
        BackgroundImage backgroundimage = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                        true, true, true, true));

        Background background = new Background(backgroundimage);

        Label labelName = new Label("Please enter your first and last name");
        GridPane.setRowIndex(labelName,1);
        GridPane.setColumnIndex(labelName,2);
        labelName.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        labelName.setTextFill(Paint.valueOf("white"));
        labelName.wrapTextProperty();
        labelName.setFont(Font.font("Arial Rounded MT Bold" ,22));
        TextField textArea = new javafx.scene.control.TextField ();
        GridPane.setRowIndex(textArea,2);
        GridPane.setColumnIndex(textArea,2);

        Label labelVatNumber = new javafx.scene.control.Label("Please enter your Vat Number");
        GridPane.setRowIndex(labelVatNumber,3);
        GridPane.setColumnIndex(labelVatNumber,2);
        labelVatNumber.setAlignment(Pos.TOP_LEFT);
        labelVatNumber.setTextFill(Paint.valueOf("white"));
        labelVatNumber.wrapTextProperty();
        labelVatNumber.setFont(javafx.scene.text.Font.font("Arial Rounded MT Bold" ,22));
        PasswordField ps = new PasswordField ();
        GridPane.setRowIndex(ps,4);
        GridPane.setColumnIndex(ps,2);

        Label labelTest = new Label("Please select the type of the COVID-19 test from the following choices");
        GridPane.setRowIndex(labelTest,5);
        GridPane.setColumnIndex(labelTest,2);
        labelTest.setAlignment(Pos.TOP_LEFT);
        labelTest.setTextFill(Paint.valueOf("white"));
        labelTest.wrapTextProperty();
        labelTest.setFont(Font.font("Arial Rounded MT Bold" ,22));
        ToggleGroup group = new ToggleGroup();
        RadioButton test1 = new RadioButton("Diagnostic test");
        GridPane.setRowIndex(test1,6);
        GridPane.setColumnIndex(test1,2);
        test1.setToggleGroup(group);
        RadioButton test2 = new RadioButton("Rapid test");
        GridPane.setRowIndex(test2,7);
        GridPane.setColumnIndex(test2,2);
        test2.setToggleGroup(group);

        Label labelDay = new Label("Please select the date when you took the result of the test");
        GridPane.setRowIndex(labelDay,8);
        GridPane.setColumnIndex(labelDay,2);
        labelDay.setAlignment(Pos.TOP_LEFT);
        labelDay.setTextFill(Paint.valueOf("white"));
        labelDay.wrapTextProperty();
        labelDay.setFont(Font.font("Arial Rounded MT Bold" ,22));
        DatePicker date = new javafx.scene.control.DatePicker();
        GridPane.setRowIndex(date,9);
        GridPane.setColumnIndex(date,2);
        date.setEditable(false);

        Label labelTypeOfUser = new Label("Please select whether you are a Passenger or an Employee");
        GridPane.setRowIndex(labelTypeOfUser,10);
        GridPane.setColumnIndex(labelTypeOfUser,2);
        labelTypeOfUser.setAlignment(Pos.TOP_LEFT);
        labelTypeOfUser.setTextFill(Paint.valueOf("white"));
        labelTypeOfUser.wrapTextProperty();
        labelTypeOfUser.setFont(Font.font("Arial Rounded MT Bold" ,22));
        ToggleGroup grp = new ToggleGroup();
        RadioButton pas = new RadioButton("Passenger");
        GridPane.setRowIndex(pas,11);
        GridPane.setColumnIndex(pas,2);
        pas.setToggleGroup(group);
        RadioButton emp = new RadioButton("Employee");
        GridPane.setRowIndex(emp,12);
        GridPane.setColumnIndex(emp,2);
        emp.setToggleGroup(group);


        Label labelTypeOfEmployee = new Label("If you are an Employee please select the type of Employee you are");
        GridPane.setRowIndex(labelTypeOfEmployee,13);
        GridPane.setColumnIndex(labelTypeOfEmployee,2);
        labelTypeOfEmployee.setAlignment(Pos.TOP_LEFT);
        labelTypeOfEmployee.setTextFill(Paint.valueOf("white"));
        labelTypeOfEmployee.wrapTextProperty();
        labelTypeOfEmployee.setFont(Font.font("Arial Rounded MT Bold" ,22));
        ComboBox<String> combo2 = new ComboBox<String>();
        combo2.setPromptText("Employee Type");
        GridPane.setRowIndex(combo2,14);
        GridPane.setColumnIndex(combo2,2);
        ObservableList<String> list2 = combo2.getItems();
        list2.add("Flight Crew");
        list2.add("Store Stuff");
        list2.add("Gate Stuff");

          pas.setOnAction(new EventHandler <ActionEvent>() {
              @Override
            public void handle(ActionEvent event) {
              boolean disableButtons = pas.isSelected() || emp.isSelected();
             combo2.setDisable(disableButtons);
            }
          });

        Button okButton = new Button("OK") ;
        GridPane.setColumnIndex(okButton,200);
        GridPane.setRowIndex(okButton,50);
        Button helpButton = new Button("HELP");
        GridPane.setRowIndex(helpButton, 50);
        GridPane.setColumnIndex(helpButton,210);

        helpButton.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dialog dialog = new Dialog<>();
                dialog.initOwner(gridPane.getScene().getWindow());
                dialog.setTitle("HELP");
                dialog.setHeaderText("Information for the user");
                dialog.setContentText("1. Please use this application only if you are or you were positive to COVID-19 within the past month. " +
                        "2. Please enter valid personal data. ");
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                java.util.Optional<javafx.scene.control.ButtonType> result = dialog.showAndWait();
            }
        });

        okButton.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.close();
                GridPane gridPane = new GridPane();
                gridPane.setHgap(1);
                gridPane.setVgap(10);

                FileInputStream input = null;
                try {
                    input = new java.io.FileInputStream("globe-and-airplane-logo-or-icon-vector-5271553.jpg");
                } catch (java.io.FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image = new Image(input);
               BackgroundImage backgroundimage = new BackgroundImage(image,
                       BackgroundRepeat.NO_REPEAT,
                       BackgroundRepeat.NO_REPEAT,
                       BackgroundPosition.DEFAULT,
                       new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                                true, true, true, true));

                Background background = new Background(backgroundimage);
                Label label = new Label("We are processing your data...PLEASE WAIT");
                GridPane.setRowIndex(label, 1);
                GridPane.setColumnIndex(label, 1);
                label.setAlignment(Pos.TOP_CENTER);
                label.setTextFill(Paint.valueOf("black"));
                label.wrapTextProperty();
                label.setFont(Font.font("Arial Rounded MT Bold", 24));
                gridPane.getChildren().addAll(label);
                gridPane.setBackground(background);
                primaryStage.setScene(new javafx.scene.Scene(gridPane, 1000, 800));
                primaryStage.show();
            }
        });





        gridPane.getChildren().addAll(labelName, textArea, labelVatNumber, ps, labelTest, test1, test2,
                labelDay, date,labelTypeOfUser, pas, emp, labelTypeOfEmployee, combo2, okButton,
                helpButton);
        gridPane.setBackground(background);
        primaryStage.setTitle("Airport application for COVID-19");
        primaryStage.setScene(new javafx.scene.Scene(gridPane, 1500, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
