package Airports;

import Graphics.CheckAddingInput;
import Graphics.AddVisitedStores;
import Graphics.AddTicket;
import Graphics.AddStores;
import Graphics.AddGate;
import Graphics.AddAirport;
import Graphics.AddFlightCrew;
import Graphics.AddFlight;
import Graphics.AddAirportStuff;
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
    private static ArrayList<Person> passengers = new ArrayList<Person>();
    // An arrayList with the data of Flight Objects
    private static ArrayList<Flight> flights = new ArrayList<Flight>();
    
    /**
     * Constructor of the class
     * If there aren't data to binary files, calls the methods initializeFromFile and saveData
     */
//    public ProgramData() {
//        if (!this.load()) {
//            initializeFromFile();
//            saveData();
//        }
//    }
    
    public static void loadData(){
         if (!load()) {
            initializeFromFile();
            saveData();
        }
    }

    /**
     * Constructor of the class
     * @param initializeFromFiles
     */
//    public ProgramData(boolean initializeFromFiles) {
//        if (initializeFromFiles) {
//            initializeFromFile();
//            saveData();
//        } else {
//            if (!this.load()) {
//                initializeFromFile();
//                saveData();
//            }
//        }
//    }

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
        return passengers;
    }

    /**
     * Gets the ArrayList flights with all flights' data
     * @return ArrayList<Flight> containing Person objects representing the flights' data 
     */
    public static ArrayList<Flight> getFlights() {
        return flights;
    }


    /**
     * Initialize the ArrayLists with data from CSV files
     */
    public static void initializeFromFile() {
        airports = FileManager.loadAirports("CSVFiles//airports.csv//");
        flightCrew = FileManager.getFlightCrew("CSVFiles//people.csv//");
        passengers = FileManager.getPassengers("CSVFiles//people.csv//");
        flights = FileManager.loadFlights("CSVFiles//flights.csv//", airports, flightCrew, passengers);
    }

    /**
     * When initilization is completed save the data to binary files
     */
    public static void saveData() {
        saveObject(airports, "airports");
        saveObject(flightCrew, "flightCrew");
        saveObject(passengers, "passengers");
        saveObject(flights, "flights");
    }

    /**
     * Save data to a file
     * @param object An Object we want to save to a file
     * @param fileName The name of the file we want to save the object
     */
    private static void saveObject(Object object, String fileName) {
       ObjectOutputStream oos = null;
       FileOutputStream fout = null;
       try {
            fout = new FileOutputStream(fileName + ".ser", false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(object);
//            if(object.equals("airports")) {
//                airports.add((Airport)object);
//            }
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
    public static boolean load(){
        airports = (ArrayList<Airport>) loadObject("airports");
        flightCrew = (ArrayList<Person>) loadObject("flightCrew");
        passengers = (ArrayList<Person>) loadObject("passengers");
        flights = (ArrayList<Flight>) loadObject("flights");
        if(airports == null | flightCrew == null | passengers == null | flights == null){
            return false;
        }
        return true;
    }

    /**
     * Load a file
     * @param fileName the name of the file
     * @return Object containing the file's data
     */
    private static Object loadObject(String fileName){
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
        ArrayList<Person> closeContacts =  new ArrayList<Person>();
        ArrayList<Person> casualContacts =  new ArrayList<Person>();
        ArrayList<Person> contacts =  new ArrayList<Person>();
        //The ssn that user has typed
        String ssn = MainWindowForUser.getPs().getText();
        // The date of results of the positive test
        LocalDate posDate = LocalDate.parse(MainWindowForUser.getDate().getValue().toString());
        
        if (checkDate(posDate)) {
            LocalDate[] dates =  calculateDays(posDate);
            LocalDate positiveDate = dates[0];
            LocalDate lastSearchDate = dates[1];
            int sumFlights = getFlights().size() - 1;
            LocalDate flightDate;
            for (int id = sumFlights; id >= 0; id--) {
                flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                    contacts = getFlights().get(id).findCloseContactsOfPassenger(ssn);
                    if (!contacts.isEmpty()) {
                        for (int i = 0; i < contacts.size(); i++ ) {
                            if (!closeContacts.contains(contacts.get(i))) {
                                closeContacts.add(contacts.get(i));
                            }
                        }
                    } 
                    contacts = getFlights().get(id).findCasualContactsOfPassenger(ssn);
                    if (!contacts.isEmpty()) {
                        for (int i = 0; i < contacts.size(); i++ ) {
                            if (!casualContacts.contains(contacts.get(i))) {
                                casualContacts.add(contacts.get(i));
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
     * Search for close contacts when the positive case is working as flight crew
     */
    public static void searchForFlightCrew() {
        ArrayList<Person> closeContacts =  new ArrayList<Person>();
        ArrayList<Person> casualContacts =  new ArrayList<Person>();
        ArrayList<Person> contacts =  new ArrayList<Person>();
        //The ssn that user has typed
        String ssn = MainWindowForUser.getPs().getText();
        // The date of results of the positive test
        LocalDate posDate = LocalDate.parse(MainWindowForUser.getDate().getValue().toString());
        
        if (checkDate(posDate)) {
            LocalDate[] dates =  calculateDays(posDate);
            LocalDate positiveDate = dates[0];
            LocalDate lastSearchDate = dates[1];
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
        ArrayList<Person> closeContacts =  new ArrayList<Person>();
        ArrayList<Person> casualContacts =  new ArrayList<Person>();
        ArrayList<Person> contacts =  new ArrayList<Person>();
        //The ssn that user has typed
        String ssn = MainWindowForUser.getPs().getText();
        // The date of results of the positive test
        LocalDate posDate = LocalDate.parse(MainWindowForUser.getDate().getValue().toString());
        
        if (checkDate(posDate)) {
            LocalDate[] dates =  calculateDays(posDate);
            LocalDate positiveDate = dates[0];
            LocalDate lastSearchDate = dates[1];
            Airport workingAirport = getWorkingAirport();
            if (workingAirport != null) {
                AirportStuff checkInEmployee;
                checkInEmployee = workingAirport.getCheckInPlace().getAirportStuffBySSN(ssn);
                if (checkInEmployee != null) {
                    int sumFlights = getFlights().size() - 1;
                    LocalDate flightDate;
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            if (workingAirport.getAirportICAO().equals(getFlights().get(id).getDepartureAirport().getAirportICAO())) {
                                contacts = getFlights().get(id).findCloseContactsOfCheckInStuff(checkInEmployee, workingAirport);
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
        ArrayList<Person> closeContacts =  new ArrayList<Person>();
        ArrayList<Person> casualContacts =  new ArrayList<Person>();
        ArrayList<Person> contacts =  new ArrayList<Person>();
        //The ssn that user has typed
        String ssn = MainWindowForUser.getPs().getText();
        // The date of results of the positive test
        LocalDate posDate = LocalDate.parse(MainWindowForUser.getDate().getValue().toString());
        if (checkDate(posDate)) {
            LocalDate[] dates =  calculateDays(posDate);
            LocalDate positiveDate = dates[0];
            LocalDate lastSearchDate = dates[1];
            Airport workingAirport = getWorkingAirport();
            if (workingAirport != null) {
                AirportSection workingStore;
                workingStore = workingAirport.getWorkingStore(ssn);
                if (workingStore != null) {
                    AirportStuff storeEmployee;
                    storeEmployee = workingStore.getAirportStuffBySSN(ssn);
                    int sumFlights = getFlights().size() - 1;
                    LocalDate flightDate;
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = getFlights().get(id).getDepartureDateTime().toLocalDate();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            if (workingAirport.getAirportICAO().equals(getFlights().get(id).getDepartureAirport().getAirportICAO())) {
                                contacts = getFlights().get(id).findCasualContactsOfStoreStuff(storeEmployee, workingStore);
                                for (int i = 0; i < contacts.size(); i++ ) {
                                    if (!casualContacts.contains(contacts.get(i))) {
                                        casualContacts.add(contacts.get(i));
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
        ArrayList<Person> closeContacts =  new ArrayList<Person>();
        ArrayList<Person> casualContacts =  new ArrayList<Person>();
        ArrayList<Person> contacts =  new ArrayList<Person>();
        //The ssn that user has typed
        String ssn = MainWindowForUser.getPs().getText();
        // The date of results of the positive test
        LocalDate posDate = LocalDate.parse(MainWindowForUser.getDate().getValue().toString());
        if (checkDate(posDate)) {
            LocalDate[] dates =  calculateDays(posDate);
            LocalDate positiveDate = dates[0];
            LocalDate lastSearchDate = dates[1];
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
                            if (workingAirport.getAirportICAO().equals(getFlights().get(id).getDepartureAirport().getAirportICAO())) {
                                if (gateEmployee.isWorking(flightDateTime)) {
                                    contacts = getFlights().get(id).findCasualContactsOfGateStuff(gate,gateEmployee,flightDateTime);
                                    for (int i = 0; i < contacts.size(); i++ ) {
                                        if (!casualContacts.contains(contacts.get(i))) {
                                            casualContacts.add(contacts.get(i));
                                        }
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
    private static boolean checkDate(LocalDate positiveDate) {
        // The date of today in order to compare the dates
        LocalDate nowDate = LocalDate.parse("2020-02-27"); //LocalDate.now()
        if (positiveDate.plusDays(30).compareTo(nowDate) > 0) {
            return true;
        } else {
            Output.dateOutOfBounds();
            return false;
        }
    }
    
    /**
     * Calsulate how many days we have to search for tracers depending on covid-19 type of test
     * @return LocaDate[] representing the prositive date and the last searching date
     */
    private static LocalDate[] calculateDays(LocalDate positiveDate) {
        LocalDate[] dates = new LocalDate[2];
        dates[0] = positiveDate;
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
            //positiveDate
            dates[0] = positiveDate.plusDays(1);
        }
        //lastSearchDate: the last day that we have to search for tracers
        dates[1] = positiveDate.minusDays(daysBack);
        return dates;
    }
    
    /**
     * Searches for passengers that were in the same time in the baggage reclaim area to get their luggages
     * Passengers get their luggages only from the destination's airport
     * @param flight Flight containing the flight in which we refer to
     * @return ArrayList<Person> representing all the passengers that were found in the searching
     */
    public static ArrayList<Person> baggageReclaimArea(Flight flight) {
        ArrayList<Person> passengers = new ArrayList<Person>();
        int sumFlights = getFlights().size() - 1;
        
        for (int id = sumFlights; id >= 0; id--) {
            Flight flight2 = getFlights().get(id);
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


    /**
     * Adds new flight to binary file flights only if the airports of departure and destination of flight exist
     * flag: true or false depending on successful or failed addition
     */
    public static void addFlight() {
        String depICAO = AddFlight.getDpICAO();
        String destICAO = AddFlight.getDsICAO();
        String departureDate = AddFlight.getDpDate();
        String destinationDate = AddFlight.getDsDate();
        String departureTime = AddFlight.getDpTime();
        String destinationTime = AddFlight.getDsTime();
        
        String departureDateTime = departureDate + "T" + departureTime;
        String destinationDateTime = destinationDate + "T" + destinationTime;
        
        boolean flag = false;      
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
            flights.add(flight);
            flag = true;
        }
        CheckAddingInput.message(flag);
    }
    
    /**
     * Adds flightCrew to the arrayList of a specific flight 
     * If this Person doesn't already exist, he is also added to binary file people
     * flag: true or false depending on successful or failed addition
     */
    public static void addFlightCrew() {
        String ssn = AddFlightCrew.getSSN();
        String name = AddFlightCrew.getName();
        String lastName = AddFlightCrew.getLastName();
        String address = AddFlightCrew.getAddress();
        String phone = AddFlightCrew.getPhone();
        String flightId = AddFlightCrew.getID();
        
        boolean flag = false;
        int id = Integer.parseInt(flightId);
        boolean exists = false;
        Person crew = null;
                
        for(Flight flight: getFlights()) {
            if(flight.getFlightId() == id) {
                exists = true;
                break;
            }
        }

        if (exists) {
            for(Person fCrew: getFlightCrew()) {
                if(fCrew.getSSN().equals(ssn)) {
                    crew = fCrew;
                    break;
                }
            }
            if(crew == null) {
                crew = new Person(ssn, name, lastName, address, phone);
                flightCrew.add(crew);
                System.out.println("INNNNNNN");
            }
            getFlights().get(id).addFlightCrew(crew);
            System.out.println("MESAAAAAA");
            flag = true;
        }
         CheckAddingInput.message(flag);
    }
    
    /**
     * Adds new ticket to a the arrayList of a specific flight and save it to binary file flightsData
     * If passenger of the ticket doesn't already exist, he is also added to binary file people
     * flag: true or false depending on successful or failed addition
     */
    public static void addTicket(){
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
        boolean flag = false;
                
                
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
                    passengers.add(pass);
                }
                for (Ticket ticket : getFlights().get(flightIdInt).getTickets()) {
                    if (ticket.getPassenger().getSSN().equals(pass.getSSN())) {
                        existsT = true;
                    }
                }
                if(!existsT) {
                    getFlights().get(flightIdInt).addTicket(
                        new Ticket(pass, checkInDateTime, ifLuggage, departureGate, destinationGate));
                    flag = true;
                }
            
            }
        }
         CheckAddingInput.message(flag);
    }

    /**
     * Adds a visited store to the arrayList of a specific ticket, only if exists in file sections
     * It is also added to the binary file visitedStores
     * flag: true or false depending on successful or failed addition
     */
    public static void addVisitedStore() {
        String idString = AddVisitedStores.getID();
        String passengerSSN = AddVisitedStores.getSSN();
        String entranceDateTime = AddVisitedStores.getEntrance();
        String storeName = AddVisitedStores.getStore();
                
        boolean exists = false;
        boolean existsStore = false;
        boolean flag = false;
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
                                flag = true;
                            }
                        }
                    }   
                }
            }
        }
         CheckAddingInput.message(flag);
    }

    /**
     * Adds new airport to binary files airports
     * flag: true or false depending on successful or failed addition
     */
    public static void addAirport() {
        String icao = AddAirport.getICAO();
        String name = AddAirport.getName();

        boolean exists = false;
        boolean flag = false;
        
        for (Airport airport : getAirports()) {
            if(airport.getAirportICAO().equals(icao)) {
                exists = true;
            }
        }
        if(!exists) {
            Airport airport = new Airport(icao, name);
            airports.add(airport);
            System.out.println("INNN");
            flag = true;
        }
        CheckAddingInput.message(flag);
    }

    /**
     * Adds a store to the arrayList of a specific airport only if not already exist to file sections
     * It is also added to binary file sections
     * flag: true or false depending on successful or failed addition
     */
    public static void addStore() {
        String icao = AddStores.getICAO();
        String name = AddStores.getStore();
        
        boolean exists = false;
        boolean flag = false;
        
        for (Airport airport : getAirports()) {
            if(airport.getAirportICAO().equals(icao)) {
                for (AirportSection store : airport.getStores()) {
                    if(store.getSectionName().equals(name)) {
                        exists = true;
                    }
                }
                if(!exists) {
                    airport.addStore(new AirportSection(name));
                    flag = true;
                }
            }
        }
         CheckAddingInput.message(flag);
    }

    /**
     * Adds a gate to the arrayList of a specific airport only if not already exist to file sections
     * It is also added to binary file sections
     * flag: true or false depending on successful or failed addition
     */
    public static void addGate() {
        String icao = AddGate.getICAO();
        String name = AddGate.getGate();
        
        boolean exists = false;
        boolean flag = false;
        
        for (Airport airport : getAirports()) {
            if(airport.getAirportICAO().equals(icao)) {
                for (AirportSection gate : airport.getGates()) {
                    if(gate.getSectionName().equals(name)) {
                        exists = true;
                    }
                }
                if(!exists) {
                    airport.addGate(new AirportSection(name));
                    flag = true;
                }
            }
        }
        CheckAddingInput.message(flag);
    }
  
    public static void addCheckInStuff() {
        String icao = AddAirportStuff.getICAO();
        String ssn = AddAirportStuff.getSSN();
        String name = AddAirportStuff.getName();
        String lastName = AddAirportStuff.getLastName();
        String address = AddAirportStuff.getAddress();
        String phone = AddAirportStuff.getPhone();
        ArrayList<String> workHours = new  ArrayList<String>();
        
        boolean flag = false;
        
        if(!ifExistsStuff(ssn)) {
            for(Airport airport : getAirports()) {
                if(airport.getAirportICAO().equals(icao)) {
                    AirportStuff stuff = new AirportStuff(ssn, name, lastName, address, phone);
                    airport.getCheckInPlace().addSectionStuff(stuff);
                    flag = true;
                    for(int i = 1; i <= 7; i++) {
                        DayOfWeek day = DayOfWeek.of(i);
                        LocalTime startTime = LocalTime.parse(workHours.get(i - 1));
                        LocalTime endTime = startTime.plusHours(8);
                        airport.getCheckInPlace().getAirportStuffBySSN(ssn).addWorkHours(day,startTime, endTime);
                    }  
                }
            }
        }
        CheckAddingInput.message(flag);
    }


    public static boolean ifExistsStuff(String ssn) {
        for (Airport airport : getAirports()) {
            for(AirportStuff stuff : airport.getCheckInPlace().getSectionStuff()) {
                if(stuff.getSSN().equals(ssn)) {
                    return true;
                }
            }
            for (AirportSection store : airport.getStores()) {
                for(AirportStuff stuff : store.getSectionStuff()) {
                    if(stuff.getSSN().equals(ssn)) {
                        return true;
                    }
                }
            }
            for (AirportSection gate : airport.getGates()) {
                for(AirportStuff stuff : gate.getSectionStuff()) {
                    if(stuff.getSSN().equals(ssn)) {
                        return true;
                    }
                }
            }           
        }
        return false;
    }

    }


    

    
 
    
    

    

    
   