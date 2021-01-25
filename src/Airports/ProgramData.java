package Airports;

import Graphics.AddVisitedStores;
import Graphics.AddTicket;
import Graphics.AddStores;
import  Graphics.AddGate;
import Graphics.AddAirport;
import Graphics.AddFlightCrew;
import Graphics.AddFlight;
import Graphics.MainWindowForUser;
import Graphics.Output;
import java.time.*;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProgramData implements Serializable {
    
    // An arrayList with the data of Airport Objects
    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    // An arrayList with the data of Person flightCrew Objects
    private static ArrayList<Person> flightCrew = new ArrayList<Person>();
    // An arrayList with the data of Person passengers Objects
    private static ArrayList<Person> pasengers = new ArrayList<Person>();
    // An arrayList with the data of Flight Objects
    private static ArrayList<Flight> flights = new ArrayList<Flight>();
    
    // An arrayList that will be filled when we are searching for close contacts
    private static ArrayList<Person> closeContacts =  new ArrayList<Person>();
    // An arrayList that will be filled when we are searching for casual contacts
    private static ArrayList<Person> casualContacts =  new ArrayList<Person>();
    // A temp arrayList with Person Objects
    private static ArrayList<Person> contacts =  new ArrayList<Person>();

    
    //The ssn that user has typed
    private static String ssn = MainWindowForUser.getPs().getText();
    // The date of results of the positive test
    private static LocalDate positiveDate = LocalDate.parse(MainWindowForUser.getDate().getValue().toString());
    // The date of today in order to compare the dates
    private static LocalDate nowDate = LocalDate.parse("2020-12-30"); //LocalDate.now()
    // The last day that we have to search for tracers
    private static LocalDate lastSearchDate;
    
    /**
     * Constructor of the class
     * If there aren't data to binary files, calls the methods initializeFromFile and saveData
     */
    public ProgramData() {
        if (!this.load()) {
            initializeFromFile();
            saveData();
        }
    }

    /**
     * Constructor of the class
     * @param initializeFromFiles
     */
    public ProgramData(boolean initializeFromFiles) {
        if (initializeFromFiles) {
            initializeFromFile();
            saveData();
        } else {
            if (!this.load()) {
                initializeFromFile();
                saveData();
            }
        }
    }

    /**
     * Gets the ArrayList airports with all airports' data
     * @return ArrayList<Airport> containing Airport objects representing the aiports' data 
     */
    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    /**
     * Gets the ArrayList flightCrew with all flightCrew's data
     * @return ArrayList<Person> containing Person objects representing the flightCrew's data 
     */
    public static ArrayList<Person> getFlightCrew() {
        return flightCrew;
    }

    /**
     * Gets the ArrayList pasengers with all pasengers' data
     * @return ArrayList<Person> containing Person objects representing the pasengers' data 
     */
    public static ArrayList<Person> getPasengers() {
        return pasengers;
    }

    /**
     * Gets the ArrayList flights with all flights' data
     * @return ArrayList<Flight> containing Person objects representing the flights' data 
     */
    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    /**
     * Gets the ssn that user has typed
     * @return String ssn
     */
    public static String getSsn() {
        return ssn;
    }


    /**
     * Gets the positiveDate 
     * @return LocalDate representing the date of the covid-19 test
     */
    public static LocalDate getPositiveDate() {
        return positiveDate;
    }

    /**
     * Sets the positiveDate
     * @param positiveDate LocalDate containing the date of the covid-19 test
     */
    public static void setPositiveDate(LocalDate positiveDate) {
        ProgramData.positiveDate = positiveDate;
    }

    /**
     * Gets the nowDate
     * @return LocalDate representing the date of today
     */
    public static LocalDate getNowDate() {
        return nowDate;
    }

    /**
     * Sets the nowDate
     * @param nowDate LocalDate containing the date of today
     */
    public static void setNowDate(LocalDate nowDate) {
        ProgramData.nowDate = nowDate;
    }

    /**
     * Gets the lastSearchDate
     * @return LocalDate representing the last Date that we have to search for tracers
     */
    public static LocalDate getLastSearchDate() {
        return lastSearchDate;
    }

    /**
     * Sets the lastSearchDate
     * @param lastSearchDate LocalDate containing the last Date that we have to search for tracers
     */
    public static void setLastSearchDate(LocalDate lastSearchDate) {
        ProgramData.lastSearchDate = lastSearchDate;
    }

    /**
     * Initialize the ArrayLists with data from CSV files
     */
    public void initializeFromFile() {
        airports = FileManager.loadAirports("CSVFiles//airports.csv//");
        flightCrew = FileManager.getFlightCrew("CSVFiles//people.csv//");
        pasengers = FileManager.getPassengers("CSVFiles//people.csv//");
        flights = FileManager.loadFlights("CSVFiles//flights.csv//", airports, flightCrew, pasengers);
    }

    /**
     * When initilization is completed save the data to binary files
     */
    public void saveData() {
        this.saveObject(airports, "airports");
        this.saveObject(flightCrew, "flightCrew");
        this.saveObject(pasengers, "passengers");
        this.saveObject(flights, "flights");
    }

    /**
     * Save data to a file
     * @param object An Object we want to save to a file
     * @param fileName The name of the file we want to save the object
     */
    private void saveObject(Object object, String fileName) {
       ObjectOutputStream oos = null;
       FileOutputStream fout = null;
       try {
            fout = new FileOutputStream(fileName + ".ser", false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if(oos != null){
                try{
                    oos .close();
                    return;
                } catch(IOException e){
                    e.printStackTrace();
                } 
            }
        }
    }

    /**
     * The results of call loadObjects get into the ArrayLists
     * @return
     */
    @SuppressWarnings ("unchecked")
    public boolean load(){
        airports = (ArrayList<Airport>) loadObject("airports");
        flightCrew = (ArrayList<Person>) loadObject("flightCrew");
        pasengers = (ArrayList<Person>) loadObject("passengers");
        flights = (ArrayList<Flight>) loadObject("flights");
        if(airports == null | flightCrew == null | pasengers == null | flights == null){
            return false;
        }
        return true;
    }

    /**
     * Load a file
     * @param fileName the name of the file
     * @return Object containing the file's data
     */
    private Object loadObject(String fileName){
        ObjectInputStream objectinputstream = null;
        Object tempList = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName + ".ser");
            objectinputstream = new ObjectInputStream(streamIn);
            tempList = objectinputstream.readObject();
        } catch (Exception fileNotFoundException) {
            return null;
        }
        finally {
            if(objectinputstream != null){
                try{
                    objectinputstream .close();
                } catch(IOException e){
                    System.out.println("createDirectory failed:" + e);
                } 
            }
        }
	return tempList;
    }

    /**
     * Search for close and casual contacts when the positive case is passenger
     */
    public static void searchForPassenger() {
        if (checkDate()) {
            calculateDays();
            int sumFlights = getFlights().size() - 1;
            LocalDate flightDate;
            for (int id = sumFlights; id >= 0; id--) {
                flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                    contacts = getFlights().get(id).findCloseContactsOfPassenger(ssn);
                    if (contacts.size() != 0) {
                        for (int i = 0; i < contacts.size(); i++ ) {
                            if (!closeContacts.contains(contacts.get(i))) {
                                closeContacts.add(contacts.get(i));
                            }
                        }
                    }
                    contacts = getFlights().get(id).findCasualContactsOfPassenger(ssn);
                    if (contacts.size() != 0) {
                        for (int i = 0; i < contacts.size(); i++ ) {
                            if (!casualContacts.contains(contacts.get(i))) {
                                casualContacts.add(contacts.get(i));
                            }
                        }
                    }
                }
            }
            if (closeContacts.size() == 0 && casualContacts.size() == 0) {
                System.out.println("There are no tickets for the passenger with ssn " + ssn);
                System.out.println("So, there aren't possible tracers");
            } else {
                System.out.println("Close: " + closeContacts  + "\ncasual: " + casualContacts );
            }
            try {
                Output.contacts(closeContacts, casualContacts);
            } catch (NullPointerException e) {
                //empty arraylists
            }

        }
    }

    /**
     * Search for close contacts when the positive case is working as flight crew
     */
    public static void searchForFlightCrew() {
        if (checkDate()) {
            calculateDays();
            int sumFlights = getFlights().size() - 1;
            LocalDate flightDate;
            for (int id = sumFlights; id >= 0; id--) {
                flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                    if (getFlights().get(id).ifExistsFlightCrew(ssn)) {
                        contacts = getFlights().get(id).findCloseContactsOfFlightCrew(ssn);
                        for (int i = 0; i < contacts.size(); i++ ) {
                            if (!closeContacts.contains(contacts.get(i))) {
                                closeContacts.add(contacts.get(i));
                            }
                        }
                    }
                }
            }
            try {
                Output.contacts(closeContacts, casualContacts);
            } catch (NullPointerException e) {
                //empty arraylists
            }

        }
    }

    /**
     * Search for close contacts when the positive case is working as checkIn stuff
     */
    public static void searchForCheckInStuff() {
        if (checkDate()) {
            calculateDays();
            Airport workingAirport = getWorkingAirport();
            if (workingAirport != null) {
                AirportStuff checkInEmployee;
                checkInEmployee = workingAirport.getCheckInPlace().getAirportStuffBySSN(ssn);
                if (checkInEmployee != null) {
                    int sumFlights = getFlights().size() - 1;
                    LocalDate flightDate;
                    LocalDateTime flightDateTime;
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                        flightDateTime = getFlights().get(id).getDepartureDateTime();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            if (checkInEmployee.isWorking(flightDateTime)) {
                                contacts = getFlights().get(id).findCloseContactsOfCheckInStuff(checkInEmployee);
                                for (int i = 0; i < contacts.size(); i++ ) {
                                    if (!closeContacts.contains(contacts.get(i))) {
                                        closeContacts.add(contacts.get(i));
                                    }
                                }
                            }
                        }
                    }
                    if (closeContacts.size() == 0) {
                        System.out.println("There are no tracers");
                    } else {
                        System.out.println(closeContacts);
                    }
                    try {
                        Output.contacts(closeContacts, casualContacts);
                    } catch (NullPointerException e) {
                        //empty arraylists
                    }
                } else {
                    Output.employeeNotFound();
                }  
            }
        }
    }
  
    /**
     * Search for casual contacts when the positive case is working as store stuff
     */
    public static void searchForStroreStuff() {
        if (checkDate()) {
            calculateDays();
            Airport workingAirport = getWorkingAirport();
            if (workingAirport != null) {
                AirportSection store;
                store = workingAirport.getWorkingStore(ssn);
                if (store != null) {
                    AirportStuff storeEmployee;
                    storeEmployee = store.getAirportStuffBySSN(ssn);
                    int sumFlights = getFlights().size() - 1;
                    LocalDate flightDate;
                    LocalDateTime flightDateTime;
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                        flightDateTime = getFlights().get(id).getDepartureDateTime();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            if (storeEmployee.isWorking(flightDateTime)) {
                                contacts = getFlights().get(id).findCasualContactsOfStoreStuff(storeEmployee, store);
                                System.out.println(contacts.size());
                                for (int i = 0; i < contacts.size(); i++ ) {
                                    if (!casualContacts.contains(contacts.get(i))) {
                                        casualContacts.add(contacts.get(i));
                                    }
                                }
                            }
                        }
                    }
                    if (casualContacts.size() == 0) {
                        System.out.println("There are no tracers");
                    } else {
                        System.out.println(casualContacts);
                    }
                    try {
                        Output.contacts(closeContacts, casualContacts);
                    } catch (NullPointerException e) {
                        //empty arraylists
                    }
                } else {
                    Output.employeeNotFound();
                }
            }
        }
    }

    /**
     * Search for casual contacts when the positive case is working as gate stuff
     */
    public static void searchForGateStuff() {
        if (checkDate()) {
            calculateDays();
            Airport workingAirport = getWorkingAirport();
            if (workingAirport != null) {
                AirportSection gate;
                gate = workingAirport.getWorkingGate(ssn);
                if (gate != null) {
                    AirportStuff gateEmployee;
                    gateEmployee = gate.getAirportStuffBySSN(ssn);
                    int sumFlights = getFlights().size() - 1;
                    LocalDate flightDate;
                    LocalDateTime flightDateTime;
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                        flightDateTime = getFlights().get(id).getDepartureDateTime();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            if (gateEmployee.isWorking(flightDateTime)) {
                                contacts = getFlights().get(id).findCasualContactsOfGateStuff(gate);
                                for (int i = 0; i < contacts.size(); i++ ) {
                                    if (!casualContacts.contains(contacts.get(i))) {
                                        casualContacts.add(contacts.get(i));
                                    }
                                }
                            }
                        }
                    }
                    if (casualContacts.size() == 0) {
                        System.out.println("There are no tracers");
                    } else {
                        System.out.println(casualContacts);
                    }
                    try {
                        Output.contacts(closeContacts, casualContacts);
                    } catch (NullPointerException e) {
                        //empty arraylists
                    }
                } else {
                    Output.employeeNotFound();
                }
            }
        }
    }
    
    /**
     * Gets the working airport of an employee
     * @return Airport representing the airport where employee is working
     */
    private static Airport getWorkingAirport() {
        String icao = MainWindowForUser.getTextArea3().getText();
        for (Airport airport : getAirports()) {
            if (airport.getAirportICAO().equals(icao)) {
                return airport;
            }
        }
        Output.icaoNotFound();
        return null;
    }
    
    /**
     * Check if have passed more than 30 days since the covid-19 test
     * @return boolean true if haven't passed more that 30 days
     */
    private static boolean checkDate() {
        if (positiveDate.plusDays(30).compareTo(nowDate) > 0) {
            return true;
        } else {
            Output.dateOutOfBounds();
            return false;
        }
    }
    
    /**
     * Calsulate how many days we have to search for tracers depending on covid-19 type of test
     */
    private static void calculateDays() {
        // Type of covid-19 test
        String testType;
        if (MainWindowForUser.getTest1().isSelected()) {
            testType = "molecular";
        } else {
            testType = "rapid";
        }
        // If test is molecular, we search for flight up to 7 days before without the day of the test
        int daysBack = 8;
        if (testType.equals("rapid")) {
            // If test is rapid, we search for flight up to 6 days before, plus the day of the test
            daysBack = 7;
            setPositiveDate(ProgramData.positiveDate.plusDays(1));
        }
        setLastSearchDate(ProgramData.positiveDate.minusDays(daysBack));
    }
    
    public static ArrayList<Person> baggageReclaimArea(Flight flight) {
        ArrayList<Person> passengers = new ArrayList<Person>();
        ProgramData programData = new ProgramData();
        int sumFlights = programData.getFlights().size() - 1;
        
        for (int id = sumFlights; id >= 0; id--) {
            Flight flight2 = programData.getFlights().get(id);
            if (flight.getFlightId() != flight2.getFlightId() && flight2.getDestinationAirport().equals(flight.getDestinationAirport())) {
                LocalDate date1 = flight.getDestinationDateTime().toLocalDate();
                LocalDate date2 =  flight2.getDestinationDateTime().toLocalDate();
                if (date1.equals(date2)) {
                    if (flight2.getDestinationDateTime().isAfter(flight.getDestinationDateTime().minusMinutes(31)) &&
                        flight2.getDestinationDateTime().isBefore(flight.getDestinationDateTime().plusMinutes(30))) {
                        for (Ticket ticket : flight2.getTickets()) {
                            if(ticket.getIfLuggage()) {
                                passengers.add(ticket.getPassenger());
                            }
                        }
                    }
                }
            }
        }
        return passengers;
    }

    public static void data() {
        ProgramData programData = new ProgramData();
    }

    public static void clear() {
        if (!contacts.isEmpty()) {
            contacts.clear();
        }
         else if(!closeContacts.isEmpty()) {
            closeContacts.clear();
        } else if(!casualContacts.isEmpty()) {
            casualContacts.clear();
        }
    }

    /**
     * Add new flight to binary file flights
     * @return true or false depending on successful or failed addition
     */
    public static boolean addFlight() {
        String depICAO = AddFlight.getDpICAO();
        String destICAO = AddFlight.getDsICAO();
        String departureDateTime = AddFlight.getDepTime();
        String destinationDateTime = AddFlight.getDestTime();
              
        Airport departureAirport = null;
        Airport destinationAirport = null;

        for (Airport airport : getAirports()) {
            if (airport.getAirportICAO().equals(depICAO)) {
                departureAirport = airport;
            } else if(airport.getAirportICAO().equals(destICAO)) {
                destinationAirport = airport;
            }
        }

        if(departureAirport != null && destinationAirport != null) {
            Flight flight = new Flight(departureAirport, destinationAirport, departureDateTime, destinationDateTime);
            return true;
        }
        return false;
    }
    
    /**
     * Add flightCrew to the arrayList of a specific flight 
     * If this Person doesn't already exist, he is also added to binary file people
     * @return true or false depending on successful or failed addition
     */
    public static boolean addFlightCrew() {
        String ssn = AddFlightCrew.getSSN();
        String name = AddFlightCrew.getName();
        String lastName = AddFlightCrew.getLastName();
        String address = AddFlightCrew.getAddress();
        String phone = AddFlightCrew.getPhone();
        String flightId = AddFlightCrew.getID();
                
        int id = Integer.parseInt(flightId);
        boolean exists = false;
        Person flightCrew = null;
                
        for(Flight flight: getFlights()) {
            if(flight.getFlightId() == id) {
                exists =  true;
            break;
            }
        }
                
        if (exists) {
            for(Person crew: getFlightCrew()) {
                if(crew.getSSN().equals(ssn)) {
                    flightCrew = crew;
                    break;
                }
            }
            if(flightCrew == null) {
                flightCrew = new Person(ssn, name, lastName, address, phone);
            }
            getFlights().get(id).addFlightCrew(flightCrew);
            return true;
        }
        return false;
    }
    
    /**
     * Add new ticket to a the arrayList of a specific flight and save it to binary file flightsData
     * If passenger of the ticket doesn't already exist, he is also added to binary file people
     * @return true or false depending on successful or failed addition
     */
    public static boolean addTicket(){
        String pasSSN = AddTicket.getSSN();
        String pasName = AddTicket.getName();
        String pasLastName = AddTicket.getLastName();
        String pasAddress = AddTicket.getAddress(); 
        String pasPhone = AddTicket.getPhone();
        String flightId = AddTicket.getID();
        String checkInDateTime = AddTicket.getCheckIn();
        String luggage = AddTicket.getLuggage();
        String depGateName = AddTicket.getDpgate();
        String destGateName = AddTicket.getDsgate();
        String depIcao = AddTicket.getDpICAO();
        String destIcao = AddTicket.getDsICAO();
                
        int flightIdInt = Integer.parseInt(flightId);
        Boolean ifLuggage = Boolean.parseBoolean(luggage);
        boolean exists = false;
        AirportSection departureGate = null;
        AirportSection destinationGate = null;
        Person pass = null;
        boolean existsT = false;
                
                
        for(Flight flight: getFlights()) {
            if(flight.getFlightId() == flightIdInt) {
                exists =  true;
                break;
            }
        }

        if (exists) {
            for(Airport airport: getAirports()) {
                if (airport.getAirportICAO().equals(depIcao)) {
                    for (AirportSection section : airport.getGates()) {
                        if (section.getSectionName().equals(depGateName)) {
                            departureGate = section;
                        }
                    }
                } else if (airport.getAirportICAO().equals(destIcao)) {
                    for (AirportSection section : airport.getGates()) {
                        if (section.getSectionName().equals(destGateName)) {
                            destinationGate = section;
                        }
                    }
                }
            }
            if(departureGate != null && destinationGate != null) {
                for (Person passenger : getPasengers()) {
                    if(passenger.getSSN().equals(pasSSN)) {
                        pass = passenger;
                        break;
                    }
                }
                if(pass == null) {
                    pass = new Person(pasSSN, pasName, pasLastName, pasAddress, pasPhone);
                }
                for (Ticket ticket : getFlights().get(flightIdInt).getTickets()) {
                    if (ticket.getPassenger().getSSN().equals(pass.getSSN())) {
                        existsT = true;
                    }
                }
                if(!existsT) {
                    getFlights().get(flightIdInt).addTicket(
                        new Ticket(pass, checkInDateTime, ifLuggage, departureGate, destinationGate));
                    return true;
                }
            
            }
        }
        return false;
    }

    /**
     * Add a visited store to the arrayList of a specific ticket
     * @return true or false depending on successful or failed addition
     */
    public static boolean addVisitedStore() {
        String idString = null;
        String passengerSSN = null;
        String entranceDateTime = null;
        String storeName = null;
                
        boolean exists = false;
        boolean existsStore = false;
        int idInt = Integer.parseInt(idString);
        AirportSection airportStore = null;
                
        for (Airport airport : getAirports()) {
            for(AirportSection section : airport.getStores()) {
                if(section.getSectionName().equals(storeName)) {
                    airportStore = section;
                    existsStore = true;
                }
            }
        }
        if(existsStore) {
            for(Flight flight: getFlights()) {
                if(flight.getFlightId() == idInt) {
                    for (Ticket ticket : flight.getTickets()) {
                        if(ticket.getPassenger().getSSN().equals(passengerSSN)) {
                            for (VisitedStore store : ticket.getDepartureVisitedStores()) {
                                if (store.getStore().getSectionName().equals(storeName)) {
                                    exists = true;
                                }
                            }
                            if(!exists) {
                                ticket.addDepartureVisitedStore(new VisitedStore(entranceDateTime, airportStore));
                                return true;
                            }
                        }
                    }   
                }
            }
        }
        return false;
    }
       

}




    

    
 
    
    

    

    
   