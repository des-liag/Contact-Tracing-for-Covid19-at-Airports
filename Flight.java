import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Flight implements Serializable {

    // A unique id for each flight
    private static int flightId = 0;
    private Airport departureAirport;
    private Airport destinationAirport;
    private Date departureDate;
    private Date destinationDate;
    // An ArrayList that contains objects Person which are the crew of this flight
    private ArrayList<Person> flightCrew;
    // An ArrayList that contains objects Ticket which are the tickets of this flight
    private ArrayList<Ticket> tickets;


    /**
     * Constructor of the class
     * Creates a flight with the specified gate's name, departure and destination airport's ICAO and flight's date
     * @param departureAirport The airport where flight departs
     * @param destinationAirport The airport where flight arrives
     * @param departureDate The date and time of take off
     * @param destinationDate The date and time of landing
     */
    public Flight(Airport departureAirport, Airport destinationAirport, String departureDate, String destinationDate) {
            this.departureAirport = departureAirport;
            this.destinationAirport = destinationAirport;
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
     * Sets the aiport where flight departs
     * @param departureAirport Object Airport containing the airport of departure
     * @see Airport
     */
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }


    /**
     * Gets the aiport where flight departs
     * @return Airport representing the airport of departure
     */
    public Airport getDepartureAirport() {
        return this.departureAirport;
    }


    /**
     * Sets the aiport where flight arrives
     * @param destinationAirport Object Airport containing the airport of destination
     * @see Airport
     */
    public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}


    /**
     * Gets the aiport where flight arrives
     * @return Airport representing the airport of destination
     */
    public Airport getDestinationAirport() {
		return this.destinationAirport;
	}


    /**
     * Set the date and time when airplane takes off
     * @param departureDate Sting containing the date and time of this flight's departure
     */
    public void setDepartureDate(String departureDate) {
        // handle ParseException if input has other form
        try {
            // TODO convert String to Date (dd/MM/yyyy HH:mm)
            this.departureDate =  new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(departureDate);
        } catch(ParseException e) {
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
            // TODO convert String to Date (dd/MM/yyyy HH:mm)
            this.destinationDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(destinationDate);
        } catch(ParseException e) {
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


    /**
     * Gets the travel of the flight
     * @return String representing the departure and destination of the flight
     */
    public String getTravel() {
        return getDepartureAirport().getAirportICAO() + "-" + getDestinationAirport().getAirportICAO();
    }

    /**
     * Gets the ticket of a specific passenger
     * @param ssn String containing the ssn of passenger that we search for his ticket
     * @return Ticket representing the ticket of passenger with this ssn
     * @see Ticket
     */
    public Ticket getTicketBySSN(String ssn){
        for (Ticket ticket : getTickets()){
            if(ticket.getPassenger().getSSN().equals(ssn)){
                return ticket;
            }
        }
        return null;
    }

}
