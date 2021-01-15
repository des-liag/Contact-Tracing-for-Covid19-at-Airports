import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Flight implements Serializable {

    private static int flightId = 0;
    private Airport departureAirport;
    private Airport destinationAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime destinationDateTime;
    private ArrayList<Person> flightCrew;
    private ArrayList<Ticket> tickets;

    public Flight(Airport departureAirport, Airport destinationAirport, String departureDateTime, String destinationDateTime) {
            this.departureAirport = departureAirport;
            this.destinationAirport = destinationAirport;
            setDepartureDateTime(departureDateTime);
            setDestinationDateTime(destinationDateTime);   
            this.flightCrew = new ArrayList<Person>();
            this.tickets = new ArrayList<Ticket>();
            ++flightId;
    }

    public int getFlightId() {
		return flightId;
	}

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDepartureAirport() {
        return this.departureAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

    public Airport getDestinationAirport() {
		return this.destinationAirport;
	}

    public void setDepartureDateTime(String departureDateTime) {
        this.departureDateTime = LocalDateTime.parse(departureDateTime);
    }

    public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

    public void setDestinationDateTime(String destinationDateTime) {
            this.destinationDateTime = LocalDateTime.parse(destinationDateTime);
    }

    public LocalDateTime getDestinationDateTime() {
		return destinationDateTime;
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

    public String getTravel() {
        return getDepartureAirport().getAirportICAO() + "-" + getDestinationAirport().getAirportICAO();
    }

    public Ticket getTicketBySSN(String ssn){
        for (Ticket ticket : getTickets()){
            if(ticket.getPassenger().getSSN().equals(ssn)){
                return ticket;
            }
        }
        return null;
    }

}