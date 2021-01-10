import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String args[]) throws IOException {
        ArrayList<Airport> airports;
        // ArrayList<Person> flightCrew;
        // ArrayList<Person> pasengers;


        // Administrator admin = new Administrator();
        // FileManager manager = new FileManager();
        // airports = manager.
        airports = FileManager.loadAirports("CSVFiles//airports.csv//");
        // flightCrew = manager.getFlightCrew("CSVFiles/people.csv");
        // pasengers = manager.getPassengers("CSVFiles/people.csv");


       
        // for(int i = 0; i < airports.size(); i++) {
            // System.out.println(airports.get(i).getAirportICAO());
            // System.out.println(airports.get(i).getAirportName());
            // System.out.println(airports.get(i).stores);
            // System.out.println(airports.get(i).getGates());
            // System.out.println(airports.get(i).getGates().size());
            // for(int j = 0; j < airports.get(0).getGates().size(); j++){
            //     for (int k = 0; k < airports.get(0).getGates().get(j).getSectionStuff().size(); k++ )
            //         System.out.println(airports.get(0).getGates().get(j).getSectionStuff().get(k).getFullName());
            //     System.out.println("----------------------------------------------------");
            //     System.out.println(airports.get(0).getGates().get(j).getSectionName());

            //     System.out.println("----------------------------------------------------");
            // }
        // }
        for(int j = 0; j < airports.get(0).getStores().size(); j++){
            for (int k = 0; k < airports.get(0).getStores().get(j).getSectionStuff().size(); k++ )
                System.out.println(airports.get(0).getStores().get(j).getSectionStuff().get(k).getFullName());
        }

        // for(int i = 0; i < flightCrew.size(); i++) {
        //     System.out.println(flightCrew.get(i).getFullName());
        // }
        // for(int i = 0; i < pasengers.size(); i++) {
        //     System.out.println(pasengers.get(i).getFullName());
        // }

        // FileManager.loadAirports("airports.csv");

        // if (!admin.loadFlights()){
        //     admin.initializeFromFile();
        // }




        

    // testing
    //     AirportStuff airportStuff1 = new AirportStuff("262046671", "Gianna", "Petsi", "Larisa", "6974832784", "00:00", "8:00");
    //     // System.out.println(airportStuff1.getFullName());
        
    //     // βγάζει ότι να ναι για Date, θέλει αλλαγή το format
    //     AirportStuff airportStuff2 = new AirportStuff("236470957", "Giannis", "Vatsopoulos", "Serres", "6937284638", "08:00", "16:00");
    //     // System.out.println(airportStuff2.getFullName() + airportStuff2.getWorkHours());
    //     // System.out.println(airportStuff2.getStartTime());

    //     AirportSection airportSection1 = new AirportSection("store1");
    //     airportSection1.addSectionStuff(airportStuff1);
    //     airportSection1.addSectionStuff(airportStuff2);

    //     // for (int i = 0; i < airportSection1.getSectionStuff().size(); i++) {
    //     //     System.out.println(airportSection1.getSectionStuff().get(i).getFullName());
    //     // }

    //     // airportSection1.addSectionStuff(airportStuff2);

    //     AirportSection airportSection2 = new AirportSection("checkinplace");

    //     Airport airport1 = new Airport("ATH", "Athens Aiport", airportSection2);

    //     airport1.addStore(airportSection1);

    //     // Τυπώνει τους υπαλλήλους που δουλεύουν στα καταστήματα του airport1
    //     for (int i = 0; i < airport1.stores.size(); i++) {
    //         for (int j = 0; j < airport1.stores.get(i).getSectionStuff().size(); j++) {
    //             System.out.println(airport1.stores.get(i).getSectionStuff().get(j).getFullName());
    //         }
    //     }

    }
}
