package Airports;

import static Airports.ProgramData.getFlights;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Flight implements Serializable {

    // A unique id for each flight
    private static int sumFlights = 0;
    private int flightId = 0;
    private Airport departureAirport;
    private Airport destinationAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime destinationDateTime;
    // An ArrayList that contains objects Person which are the crew of this flight
    private ArrayList<Person> flightCrew;
    // An ArrayList that contains objects Ticket which are the tickets of this flight
    private ArrayList<Ticket> tickets;

    /**
     * Constructor of the class
     * Creates a flight with the specified gate's name, departure and destination airport's ICAO and flight's date
     * @param departureAirport The airport where flight departs
     * @param destinationAirport The airport where flight arrives
     * @param departureDateTime The date and time of take off
     * @param destinationDateTime The date and time of landing
     */
    public Flight(Airport departureAirport, Airport destinationAirport, String departureDateTime, String destinationDateTime) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        setDepartureDateTime(departureDateTime);
        setDestinationDateTime(destinationDateTime);
        this.flightCrew = new ArrayList<Person>();
        this.tickets = new ArrayList<Ticket>();
        flightId = sumFlights += 1;
    }

    /**
     * Gets the id of the flight
     * @return int representing the flight id
     */
    public int getFlightId() {
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
     * @param departureDateTime String containing the date and time of this flight's departure
     */
    public void setDepartureDateTime(String departureDateTime) {
		// Convert String departureDateTime to LocalDateTime
        this.departureDateTime = LocalDateTime.parse(departureDateTime);
    }

    /**
     * Gets the date and time of this flight's departure
     * @return LocalDateTime representing the date and time of this flight's departure
     */
    public LocalDateTime getDepartureDateTime() {
		return departureDateTime;
	}

    /**
     * Set the date and time when airplane lands
     * @param destinationDateTime String containing the date and time of this flight's destination
     */
    public void setDestinationDateTime(String destinationDateTime) {
            this.destinationDateTime = LocalDateTime.parse(destinationDateTime);
    }

    /**
     * Gets the date and time of this flight's destination
     * @return LocalDateTime representing the date and time of this flight's destination
     */
    public LocalDateTime getDestinationDateTime() {
		return destinationDateTime;
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
        for (Ticket ticket : this.getTickets()){
            if(ticket.getPassenger().getSSN().equals(ssn)){
                return ticket;
            }
        }
        return null;
    }

    /**
     * Finds the close contacts of a specific passenger
     * Finds the AirportStuff employees, the FlightCrew employees and the other passengers that had close contact with a specific passenger
     * @param ssn String containing the ssn of a passenger that we search for his contacts
     * @return ArrayList<Person> containing objects Person representing the close contacts of this passenger
     * @see Person
     */
    public ArrayList<Person> findCloseContactsOfPassenger(String ssn){
        ArrayList<Person> closeContacts = new ArrayList<Person>();
        Ticket ticket =  this.getTicketBySSN(ssn);
 
        if (ticket != null) {
            LocalDateTime time = ticket.getCheckInDateTime();
            
            //finds the employee that works in checkIn place the time that passenger did the check-in
            for(AirportStuff employee : this.getDepartureAirport().getCheckInPlace().getSectionStuff()){
                if(employee.isWorking(time)){
                    closeContacts.add(employee);
                }
            }

            //finds the crew that work in this flight
            for(Person employee : this.getFlightCrew()){
                closeContacts.add(employee);
            }
            //finds the other passengers that were in this flight
            for(Person passenger : this.getPasengers()){
                if (!passenger.getSSN().equals(ssn)) {
                    closeContacts.add(passenger);
                }
            }
        }
        return closeContacts;
    }

    /**
     * Finds the casual contacts of a specific passenger
     * Finds the AirportStuff store employees and the other passengers with luggage that had casual contact with a specific passenger
     * @param ssn String containing the ssn of a passenger that we search for his contacts
     * @return ArrayList<Person> containing objects Person representing the casual contacts of this passenger
     * @see Person
     */
    public ArrayList<Person> findCasualContactsOfPassenger(String ssn){
        ArrayList<Person> casualContacts = new ArrayList<Person>();
        LocalDateTime flightDateTime = this.getDepartureDateTime();

        Ticket ticket = this.getTicketBySSN(ssn);
        if (ticket != null) {
            //finds the employee(s) that work in gate where the flight gone
            for(AirportStuff employee : ticket.getDepartureGate().getSectionStuff()){
                if(employee.isWorking(flightDateTime)){
                    casualContacts.add(employee);
                }
            }
            //finds the employee(s) that work in store where passenger visited
            if (!ticket.getDepartureVisitedStores().isEmpty()) {
                for(VisitedStore visitedStore : ticket.getDepartureVisitedStores()){
                    for(AirportStuff employee : visitedStore.getStore().getSectionStuff()){
                        if(employee.isWorking(visitedStore.getEntranceDateTime())){
                            casualContacts.add(employee);
                        }
                    }
                }   
            }
            //finds the passengers from other flights that have luggage and were in baggage reclaim area in the same time
            if (ticket.getIfLuggage()) {
                ArrayList<Person> peopleWithLuggage = new ArrayList<Person>();
                peopleWithLuggage = ProgramData.baggageReclaimArea(this);
                if(peopleWithLuggage != null) {
                    for(Person person : peopleWithLuggage) {
                        casualContacts.add(person);
                    }
                }


            }
        }
        return casualContacts;
    }


    /**
     * Gets an ArrayList with all the passengers
     * @return ArrayList<Person> containing all the passengers
     * @see Person
     */
    public ArrayList<Person> getPasengers(){
        ArrayList<Person> passengers = new ArrayList<Person>();
        for(Ticket ticket : this.getTickets()){
            passengers.add(ticket.getPassenger());
        }
        return passengers;
    }

    /**
     * Gets all the close contacts of a FlightCrew member
     * Finds all the other FlightCrew members that work with this employee and all the passengers that were in the flight this passenger works
     * @param ssn String containing the ssn of employee that we search for his close contacts
     * @return ArrayList<Person> containing objects Person representing the close contacts of this flight crew member
     */
    public ArrayList<Person> findCloseContactsOfFlightCrew(String ssn) {
        ArrayList<Person> closeContacts = new ArrayList<Person>();
        for(Person flightCrew : this.getFlightCrew()) {
            if(!flightCrew.getSSN().equals(ssn)) {
                closeContacts.add(flightCrew);
            }
        }
        for(Person passenger : this.getPasengers()){
            closeContacts.add(passenger);
        }
        return closeContacts;
    }

    /**
     * Gets the close contacts of a specific CheckInStuff employee
     * @param employee AirportStuff containing the employee we search for his close contacts
     * @return ArrayList<Person> containing objects Person representing the passengers that are close contacts of this check in employee
     */
    public ArrayList<Person> findCloseContactsOfCheckInStuff(AirportStuff employee, Airport airport) {
        ArrayList<Person> closeContacts = new ArrayList<Person>();
        LocalTime time1 = LocalTime.parse("00:00");
        LocalTime time2 = LocalTime.parse("08:00");
        LocalTime time3 = LocalTime.parse("16:00");
        for (Ticket ticket : this.getTickets()) {
            LocalDateTime dateTime = ticket.getCheckInDateTime();
            if (employee.isWorking(dateTime)) {
                closeContacts.add(ticket.getPassenger());
                
                //in order to find the employee in the shift change
                LocalTime checkInTime = dateTime.toLocalTime();
                if(checkInTime.equals(time1) || checkInTime.equals(time2) || checkInTime.equals(time3)) {
                    for (AirportStuff stuff : airport.getCheckInPlace().getSectionStuff()) {
                        if (!stuff.getSSN().equals(employee.getSSN())) {
                            if(stuff.isWorking(dateTime)) {
                                closeContacts.add(stuff);
                            }
                        }
                    }
                }
            }
        }

        return closeContacts;
    }

    /**
     * Finds if a FlightCrew employee exists
     * @param ssn String containing the ssn of a person that we want to see if he is FlightCrew
     * @return boolean depending on if this person exists as a flight crew member
     */
    public boolean ifExistsFlightCrew(String ssn) {
        for(Person person : this.getFlightCrew()) {
            if (person.getSSN().equals(ssn)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets casual contacts of a specific employee that works in a store
     * @param employee AirportStuff representing the employee that we search for his casual contacts
     * @return ArrayList<Person> containing objects Person representing the casual contacts of this employee
     */
    public ArrayList<Person> findCasualContactsOfStoreStuff(AirportStuff employee, AirportSection store) {
        ArrayList<Person> casualContacts = new ArrayList<Person>();
        ArrayList<Person> contact;
        for (Ticket ticket : this.getTickets()) {
            contact = ticket.getContactFromVisitedStores(employee, store);
            for (Person person : contact) {
                casualContacts.add(person);
            }

        }
        return casualContacts;
    }

    /**
     * Gets casual contacts of a specific employee that works at a gate
     * @param gate AirportSection containing the gate this employee works
     * @return ArrayList<Person> containing objects Person representing the passengers that passed through this gate
     */
    public ArrayList<Person> findCasualContactsOfGateStuff(AirportSection gate, AirportStuff employee, LocalDateTime dateTime) {
        ArrayList<Person> casualContacts = new ArrayList<Person>();
        if (this.getTickets().get(0).getDepartureGate().getSectionName().equals(gate.getSectionName())) {
            for (Ticket ticket : this.getTickets()) {
                casualContacts.add(ticket.getPassenger());
                System.out.println("INNN");
            }
        }
        LocalTime time = dateTime.toLocalTime();
        LocalTime time1 = LocalTime.parse("00:00");
        LocalTime time2 = LocalTime.parse("08:00");
        LocalTime time3 = LocalTime.parse("16:00");
        //in order to find the employee in the shift change
        if(time.equals(time1) || time.equals(time2) || time.equals(time3)) {
            for (AirportStuff stuff : gate.getSectionStuff()) {
                if (!stuff.getSSN().equals(employee.getSSN())) {
                    if(stuff.isWorking(dateTime)) {
                        casualContacts.add(stuff);
                    }
                }
            }
        }
        return casualContacts;
    }
}