import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Flight {

    private static int flightId = 0;
    private Airport departureAirportICAO;
    private Airport destinationAirportICAO;
    private Date departureDate;
    private Date destinationDate;
    ArrayList<Person> flightCrew;
    ArrayList<Ticket> tickets;

    public Flight(String gateName, Airport departureAirportICAO, Airport destinationAirportICAO, String departureDate, String destinationDate) {
            ++flightId;
            this.departureAirportICAO = departureAirportICAO;
            this.destinationAirportICAO = destinationAirportICAO;
            setDepartureDate(departureDate);
            setDestinationDate(destinationDate);   
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

    public void setDepartureDate(String departureDate) {
        try {
            this.departureDate =  new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(departureDate);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type departureDate in format 'dd/MM/yyyy HH:mm'");
        }
    }

	public Date getDepartureDate() {
		return departureDate;
	}

    public void setDestinationDate(String destinationDate) {
        try {
            this.destinationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(destinationDate);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type destinationDate in format 'dd/MM/yyyy HH:mm'");
        }
    }

	public Date getDestinationDate() {
		return destinationDate;
	}

    public void addFlightCrew(Person employee) {
        if (!this.flightCrew.contains(employee)) {
            this.flightCrew.add(employee);
        } else {
            System.out.println("This employee already exists in this flight");
        }
    }

    public ArrayList<Person> getFlightCrew() {
        return this.flightCrew;
    }

    public void addTicket(Ticket ticket) {
        if(!this.tickets.contains(ticket)) {
            this.tickets.add(ticket);
        } else {
            System.out.println("This ticket is already exists");
        }
    }

    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }
}
