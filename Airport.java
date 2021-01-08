import java.util.ArrayList;

public class Airport {

    private String airportICAO;
    private String airportName;
    private AirportSection checkInPlace;
    // An ArrayList that contains objects AirportSection which are the stores of this airport
    private ArrayList<AirportSection> stores;
    // An ArrayList that contains objects AirportSection which are the gates of this airport
    private ArrayList<AirportSection> gates;


    /**
     * Constructor of the class
     * Creates an airport with the specified ICAO, name and checkIn place
     * @param airportICAO A unique code for each airport
     * @param airportName The name of the airport
     * @param checkInPlace An object AirportSection representing the checkIn place of the airport
     *                      It's only one for each airport
     */
    public Airport(String airportICAO, String airportName, AirportSection checkInPlace) {
        this.airportICAO = airportICAO;
        this.airportName = airportName;
        this.checkInPlace = checkInPlace;
        this.stores = new ArrayList<AirportSection>();
        this.gates = new ArrayList<AirportSection>();
    }


    /**
     * Sets the airport's ICAO
     * @param airportICAO String containing the airport's ICAO
     */
    public void setAirportICAO(String airportICAO) {
        this.airportICAO = airportICAO;
    }


    /**
     * Gets the airport's ICAO
     * @return String representing the airport's ICAO
     */
    public String getAirportICAO() {
        return this.airportICAO;
    }


    /**
     * Sets the airport's name
     * @param airportName String containing the airport's name
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }


    /**
     * Gets the airport's name
     * @return String representing the airport's name
     */
    public String getAirportName() {
        return this.airportName;
    }


    /**
     * Adds an object AirportSection to the ArrayList stores
     * Check if this object already exists into the ArrayList stores
     * @param store Object AirportSection containing a store of this airport
     * @see AirportSection
     */
    public void addStore(AirportSection store) {
        if(!this.stores.contains(store)){
            this.stores.add(store);
        } else {
            System.out.println("This store already exists");
        }
    }


    /**
     * Gets the ArrayList stores with all airport's stores
     * @return ArrayList<AirportSection> containing objects AirportSection representing the stores of this airport
     * @see AirportSection
     */
    public ArrayList<AirportSection> getStores() {
        return this.stores;
    }


    /**
     * Adds an object AirportSection to the ArrayList gates
     * Check if this object already exists into the ArrayList gates
     * @param gate Object AirportSection containing a gate of this airport
     * @see AirportSection
     */
    public void addGate(AirportSection gate) {
        if(!this.gates.contains(gate)){
            this.gates.add(gate);
        } else {
            System.out.println("This gate already exists");
        }
    }


    /**
     * Gets the ArrayList gates with all airport's gates
     * @return ArrayList<AirportSection> containing objects AirportSection representing the gates of this airport
     * @see AirportSection
     */
    public ArrayList<AirportSection> getGates() {
        return this.gates;
    }


    /**
     * Sets the airport's checkIn place
     * @param checkInPlace Object AirportSection containing the airport's checkIn place
     * @see AirportSection
     */
    public void setCheckInPlace(AirportSection checkInPlace) {
        this.checkInPlace = checkInPlace;
    }


    /**
     * Gets the airport's checkIn place
     * @return AirportSection representing the airport's checkIn place 
     */
    public AirportSection getCheckInPlace() {
        return this.checkInPlace;
    }

}
