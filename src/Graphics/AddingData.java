import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import  javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import  javafx.scene.control.ToggleGroup;
import  javafx.scene.control.RadioButton;


/**
 * @class AddingData creates a window for the administrator
 * and ask to pick what kind of data wants to add.
 */
public class AddingData {

    public static void start() {
       GridPane gridPane = new GridPane();
       Stage stage = new Stage();
       SetStyles.creatWindow("Updating Data",1000,800,stage,gridPane);

       Label message = SetStyles.createLabels("As an administrator you have the right to add data which referrs to airports. ",
                0,0, Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));
       Label instructions = SetStyles.createLabels("Pick from the following choices to add data: ", 1,0,
                Paint.valueOf("black"), Font.font("Arial Rounded MT Bold", 22));

       ToggleGroup group = new ToggleGroup();
       RadioButton airport = SetStyles.createRadioButton("Add Airport",2,0,group);
       RadioButton flight = SetStyles.createRadioButton("Add Flight", 3,0,group);
       RadioButton ticket = SetStyles.createRadioButton("Add Tickets",4,0,group);
       RadioButton flightCrew = SetStyles.createRadioButton("Add Flight Crew",5,0,group);
       RadioButton airportStuff = SetStyles.createRadioButton("Add Airport Stuff",6,0, group);
       RadioButton gate = SetStyles.createRadioButton("Add Gate",7,0, group);
       RadioButton store = SetStyles.createRadioButton("Add Store",8,0,group);
       RadioButton vistStore = SetStyles.createRadioButton("Add Visited Stores",9,0,group);

       airport.setOnMouseClicked(event -> {
           stage.close();
           AddAirport.newAirport();
       });
       ticket.setOnMouseClicked(event -> {
           stage.close();
           AddTicket.newTicket();
       });
       flightCrew.setOnMouseClicked(event -> {
           stage.close();
           AddFlightCrew.newFlightCrew();
       });
       flight.setOnMouseClicked(event -> {
           stage.close();
           AddFlight.addNewFlight();
       });
       vistStore.setOnMouseClicked(event -> {
           stage.close();
           AddVisitedStores.newVisitedStores();
       });
       gate.setOnMouseClicked(event -> {
           stage.close();
           AddGate.newGate();
       });
       store.setOnMouseClicked(event -> {
           stage.close();
           AddStores.newStore();
       });




       gridPane.getChildren().addAll(message, instructions,airport, flight, ticket, flightCrew,
               airportStuff, gate, store, vistStore);

    }

}
