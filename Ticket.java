import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ticket {

    private Person passenger;
    private Date checkInTime;
    private boolean ifLuggage;
    private AirportSection departureGateName;
    private AirportSection destinationGateName;
    ArrayList<VisitedStore> departureVisitedStores;

    public Ticket(Person passenger, String checkInTime, boolean ifLuggage, AirportSection departureGateName, AirportSection destinationGateName) {
        this.passenger = passenger;
        setCheckInTime(checkInTime);
        this.ifLuggage = ifLuggage;
        this.departureGateName = departureGateName;
        this.destinationGateName = destinationGateName;
        this.departureVisitedStores = new ArrayList<VisitedStore>();
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public Person getPassenger() {
        return this.passenger;
    }

    public void setCheckInTime(String checkInTime) {
        try {
        this.checkInTime = new SimpleDateFormat("HH:mm").parse(checkInTime);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type checkInTime in format 'HH:mm'");
        }
    }

    public Date getCheckInTime() {
		return checkInTime;
    }

    public void setIfLuggage(boolean ifLuggage) {
		this.ifLuggage = ifLuggage;
    }

	public boolean getIfLuggage() {
		return ifLuggage;
	}

    public void setDepartureGateName(AirportSection departureGateName) {
        this.departureGateName = departureGateName;
    }

    public AirportSection getDepartureGateName() {
        return this.departureGateName;
    }

    public void setDestinationGateName(AirportSection destinationGateName) {
        this.destinationGateName = destinationGateName;
    }

    public AirportSection getDestinationGateName() {
        return this.destinationGateName;
    }

    public ArrayList<VisitedStore> getDepartureVisitedStores() {
        return this.departureVisitedStores;
    }

}
