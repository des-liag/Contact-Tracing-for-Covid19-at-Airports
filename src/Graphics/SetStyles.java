package sample;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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



}


