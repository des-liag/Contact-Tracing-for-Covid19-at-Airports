import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ticket implements Serializable  {

    private Person passenger;
    private LocalDateTime checkInDateTime;
    private boolean ifLuggage;
    private AirportSection departureGate;
    private AirportSection destinationGate;
    private ArrayList<VisitedStore> departureVisitedStores;

    public Ticket(Person passenger, String checkInDateTime, boolean ifLuggage, AirportSection departureGate, AirportSection destinationGate) {
        this.passenger = passenger;
        setCheckInDateTime(checkInDateTime);
        this.ifLuggage = ifLuggage;
        this.departureGate = departureGate;
        this.destinationGate = destinationGate;
        this.departureVisitedStores = new ArrayList<VisitedStore>();
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }


    public Person getPassenger() {
        return this.passenger;
    }


    public void setCheckInDateTime(String checkInDateTime) {
        this.checkInDateTime = LocalDateTime.parse(checkInDateTime);
    }

    public LocalDateTime getCheckInDateTime() {
		return checkInDateTime;
    }


    public void setIfLuggage(boolean ifLuggage) {
		this.ifLuggage = ifLuggage;
    }


    public boolean getIfLuggage() {
		return ifLuggage;
	}


    public void setDepartureGate(AirportSection departureGate) {
        this.departureGate = departureGate;
    }


    public AirportSection getDepartureGate() {
        return this.departureGate;
    }


    public void setDestinationGate(AirportSection destinationGate) {
        this.destinationGate = destinationGate;
    }


    public AirportSection getDestinationGate() {
        return this.destinationGate;
    }

    
    public void addDepartureVisitedStore(VisitedStore store) {
        if(!this.departureVisitedStores.contains(store)) {
            this.departureVisitedStores.add(store);
        } else {
               System.out.println("This store has already been visited");
        }
    }


    public ArrayList<VisitedStore> getDepartureVisitedStores() {
        return this.departureVisitedStores;
    }

}
