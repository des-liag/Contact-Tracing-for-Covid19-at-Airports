import java.util.ArrayList;

public class Airport {

    private String airportICAO;
    private String airportName;
    ArrayList<AirportSection> stores;
    ArrayList<AirportSection> gates;
    private AirportSection checkInPlace;

    public Airport(String airportICAO, String airportName, AirportSection checkInPlace) {
        this.airportICAO = airportICAO;
        this.airportName = airportName;
        this.checkInPlace = checkInPlace;
        this.stores = new ArrayList<AirportSection>();
        this.gates = new ArrayList<AirportSection>();
    }

    public void setAirportICAO(String airportICAO) {
        this.airportICAO = airportICAO;
    }

    public String getAirportICAO() {
        return this.airportICAO;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getAirportName() {
        return this.airportName;
    }

    public ArrayList<AirportSection> getStores() {
        return this.stores;
    }

    public ArrayList<AirportSection> getGates() {
        return this.gates;
    }

    public void setCheckInPlace(AirportSection checkInPlace) {
        this.checkInPlace = checkInPlace;
    }

    public AirportSection getCheckInPlace() {
        return this.checkInPlace;
    }

}
