import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Airport implements Serializable {

    private String airportICAO;
    private String airportName;
    private ArrayList<AirportSection> stores;
    private ArrayList<AirportSection> gates;
    private AirportSection checkInPlace;


    public Airport(String airportICAO, String airportName) {
        this.airportICAO = airportICAO;
        this.airportName = airportName;
        this.checkInPlace = new AirportSection(airportICAO + "CheckInPlace");
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

    public void addStore(AirportSection store) {
        if(!this.stores.contains(store)){
            this.stores.add(store);
        } else {
            System.out.println("This store is already exists");
        }
    }


    public ArrayList<AirportSection> getStores() {
        return this.stores;
    }


    public void addGate(AirportSection gate) {
        if(!this.gates.contains(gate)){
            this.gates.add(gate);
        } else {
            System.out.println("This gate is already exists");
        }
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

    public AirportSection getStoreByName(String storeName){
        for (AirportSection store : this.getStores()){
            if(store.getSectionName().equals(storeName)){
                return store;
            }
        }
        return null;
    }


}
