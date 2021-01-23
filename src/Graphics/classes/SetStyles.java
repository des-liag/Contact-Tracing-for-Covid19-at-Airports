
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import  javafx.scene.image.Image;
import  javafx.scene.layout.Background;
import  javafx.scene.layout.BackgroundSize;
import  javafx.scene.layout.BackgroundPosition;
import  javafx.scene.layout.BackgroundRepeat;
import  javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import  javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;

public class SetStyles {

    /**
     * @method setStyleForLabel create the appearance of all labels (First and Last name, test, user, date, type of employee)
     * @method setStyleForRadButtons create the appearance of all Radio Buttons (COVID-test and user-type)
     * @method setStyleForText create the appearance of all Text Fields ( field for first and last name)
     * @method setStyleForButtons  create the appearance of all Buttons ( OK and HELP at the main window)
     */

    public static void setStyleForLabel(Label name, int value, Pos position, Paint color, Font font) {
        GridPane.setRowIndex(name,value);
        GridPane.setColumnIndex(name, 2);
        name.setAlignment(position);
        name.wrapTextProperty();
        name.setTextFill(color);
        name.setFont(font);
    }

    public static void setStyleForRadButtons(ToggleGroup gr, int value, RadioButton rb) {
       GridPane.setRowIndex(rb,value);
       GridPane.setColumnIndex(rb,2);
       rb.setToggleGroup(gr);
    }

    public static void setStyleForText (TextField text, int value)  {
       GridPane.setRowIndex(text,value);
       GridPane.setColumnIndex(text,2);
    }

    public static void setStyleForButtons(Button button, int row, int column) {
        GridPane.setRowIndex(button,row);
        GridPane.setColumnIndex(button,column);
    }

    public static Background setBackground(String filePath) {
       FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
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
        return background;
    }

    public static Label createLabels(String text, int row, int column, Paint color, Font font) {
        Label name = new Label(text);
        GridPane.setRowIndex(name, row);
        GridPane.setColumnIndex(name, column);
        name.setTextFill(color);
        name.setFont(font);
        return name;
    }

    public static VBox createVBox(Double space, int row, int column, Label contacts, Label message , ListView<String> list ) {
        VBox vbox = new VBox();
        vbox.setSpacing(space);
        GridPane.setRowIndex(vbox, row);
        GridPane.setColumnIndex(vbox, column);
        vbox.getChildren().addAll(contacts, message, list);
        return vbox;
    }

    public static RadioButton createRadioButton(String name, int row, int column,  ToggleGroup group) {
        RadioButton radioButton = new RadioButton(name);
        GridPane.setRowIndex(radioButton,row);
        GridPane.setColumnIndex(radioButton, column);
        radioButton.setToggleGroup(group);
        return radioButton;
    }

    public static void setPosition(TextField text, int row, int column) {
        GridPane.setRowIndex(text,row);
        GridPane.setColumnIndex(text,column);

    }

    public static void creatWindow(String title, int width, int height, Stage stage, GridPane gridPane) {
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        stage.setTitle(title);
        BackgroundFill background_fill = new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        gridPane.setBackground(background);
        stage.setScene(new Scene(gridPane, width, height));
        stage.show();
    }
}


