import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Flight {

    // A unique id for each flight
    private static int flightId = 0;
    private Airport departureAirportICAO;
    private Airport destinationAirportICAO;
    private Date departureDate;
    private Date destinationDate;
    // An ArrayList that contains objects Person which are the crew of this flight
    private ArrayList<Person> flightCrew;
    // An ArrayList that contains objects Ticket which are the tickets of this flight
    private ArrayList<Ticket> tickets;


    /**
     * Constructor of the class
     * Creates a flight with the specified gate's name, departure and destination airport's ICAO and flight's date
     * @param departureAirportICAO The code of the airport where flight departs
     * @param destinationAirportICAO The code of the airport where flight arrives
     * @param departureDate The date and time of take off
     * @param destinationDate The date and time of landing
     */
    public Flight(Airport departureAirportICAO, Airport destinationAirportICAO, String departureDate, String destinationDate) {
            this.departureAirportICAO = departureAirportICAO;
            this.destinationAirportICAO = destinationAirportICAO;
            setDepartureDate(departureDate);
            setDestinationDate(destinationDate);   
            this.flightCrew = new ArrayList<Person>();
            this.tickets = new ArrayList<Ticket>();
            // when create a flight object, flight id increase by 1
            ++flightId;
    }


    /**
     * Gets the id of the flight
     * @return int representing the flight id
     */
    public static int getFlightId() {
		return flightId;
	}


    /**
     * Sets the aiport's ICAO where flight departs
     * @param departureAirportICAO Object Airport containing the airport's ICAO
     * @see Airport
     */
    public void setDepartureAirportICAO(Airport departureAirportICAO) {
        this.departureAirportICAO = departureAirportICAO;
    }


    /**
     * Gets the aiport's ICAO where flight departs
     * @return Airport representing the airport's ICAO
     */
    public Airport getDepartureAirportICAO() {
        return this.departureAirportICAO;
    }


    /**
     * Sets the aiport's ICAO where flight arrives
     * @param destinationAirportICAO Object Airport containing the airport's ICAO
     * @see Airport
     */
    public void setDestinationAirportICAO(Airport destinationAirportICAO) {
		this.destinationAirportICAO = destinationAirportICAO;
	}


    /**
     * Gets the aiport's ICAO where flight arrives
     * @return Airport representing the airport's ICAO
     */
    public Airport getDestinationAirportICAO() {
		return this.destinationAirportICAO;
	}


    /**
     * Set the date and time when airplane takes off
     * @param departureDate Sting containing the date and time of this flight's departure
     */
    public void setDepartureDate(String departureDate) {
        // handle ParseException if input has other form
        try {
            // convert String to Date (dd/MM/yyyy HH:mm)
            this.departureDate =  new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(departureDate);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type departureDate in format 'dd/MM/yyyy HH:mm'");
        }
    }


    /**
     * Gets the date and time of this flight's departure
     * @return Date representing the date and time of this flight's departure
     */
    public Date getDepartureDate() {
		return departureDate;
	}


    /**
     * Set the date and time when airplane lands
     * @param destinationDate Sting containing the date and time of this flight's destination
     */
    public void setDestinationDate(String destinationDate) {
        // handle ParseException if input has other form
        try {
            // convert String to Date (dd/MM/yyyy HH:mm)
            this.destinationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(destinationDate);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type destinationDate in format 'dd/MM/yyyy HH:mm'");
        }
    }


    /**
     * Gets the date and time of this flight's destination
     * @return Date representing the date and time of this flight's destination
     */
    public Date getDestinationDate() {
		return destinationDate;
	}


    /**
     * Adds an object Person to the ArrayList flightCrew
     * Check if this object already exists into the ArrayList flightCrew
     * @param employee Object Person containing an employee of this flight
     * @see Person
     */
    public void addFlightCrew(Person employee) {
        if (!this.flightCrew.contains(employee)) {
            this.flightCrew.add(employee);
        } else {
            System.out.println("This employee already exists in this flight");
        }
    }


    /**
     * Gets the ArrayList flightCrew with this flight's crew
     * @return ArrayList<Person> containing objects Person representing the crew of this flight
     * @see Person
     */
    public ArrayList<Person> getFlightCrew() {
        return this.flightCrew;
    }


    /**
     * Adds an object Ticket to the ArrayList tickets
     * Check if this object already exists into the ArrayList tickets
     * @param ticket Object Ticket containing a ticket of this flight
     * @see Ticket
     */
    public void addTicket(Ticket ticket) {
        if(!this.tickets.contains(ticket)) {
            this.tickets.add(ticket);
        } else {
            System.out.println("This ticket is already exists");
        }
    }


    /**
     * Gets the ArrayList tickets with all tickets of this flight
     * @return ArrayList<Ticket> containing objects Ticket representing the tickets of this flight
     * @Ticket
     */
    public ArrayList<Ticket> getTickets() {
        return this.tickets;
    }
}
