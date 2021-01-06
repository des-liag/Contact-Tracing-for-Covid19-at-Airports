import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Flight {

    private static int flightId = 0;
    private Airport departureAirportICAO;
    private Airport destinationAirportICAO;
    private Date flightDate;
    private Date timeDeparture;
    private Date timeDestination;
    ArrayList<Person> flightCrew;
    ArrayList<Ticket> tickets;

    public Flight(
        String gateName, Airport departureAirportICAO, Airport destinationAirportICAO,
        String flightDate, String timeDeparture, String timeDestination) {
            ++flightId;
            this.departureAirportICAO = departureAirportICAO;
            this.destinationAirportICAO = destinationAirportICAO;
            setFlightDate(flightDate);
            setTimeDeparture(timeDeparture);
            setTimeDestination(timeDestination);   
            this.flightCrew = new ArrayList<Person>();
            this.tickets = new ArrayList<Ticket>();
    }

    public static int getFlightId() {
		return flightId;
	}

    public void setDepartureAirportICAO(Airport departureAirportICAO) {
        this.departureAirportICAO = departureAirportICAO;
    }

    public Airport getDepartureAirportICAO() {
        return this.departureAirportICAO;
    }

    public void setDestinationAirportICAO(Airport destinationAirportICAO) {
		this.destinationAirportICAO = destinationAirportICAO;
	}

    public Airport getDestinationAirportICAO() {
		return this.destinationAirportICAO;
	}

    public void setFlightDate(String flightDate) {
        try {
            this.flightDate = new SimpleDateFormat("dd/MM/yyyy").parse(flightDate);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type flighDate in format 'dd/MM/yyyy'");
        }
    }

	public Date getFlightDate() {
		return flightDate;
	}

    public void setTimeDeparture(String timeDeparture) {
        try {
            this.timeDeparture =  new SimpleDateFormat("HH:mm").parse(timeDeparture);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type timeDeparture in format 'HH:mm'");
        }
    }

	public Date getTimeDeparture() {
		return timeDeparture;
	}

    public void setTimeDestination(String timeDestination) {
        try {
            this.timeDestination = new SimpleDateFormat("HH:mm").parse(timeDestination);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type timeDestination in format 'HH:mm'");
        }
    }

	public Date getTimeDestination() {
		return timeDestination;
	}

    public ArrayList<Person> getFlightCrew() {
        return this.flightCrew;
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }
}