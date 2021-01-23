import java.io.IOException;
import java.time.*;
import java.util.ArrayList;

public class Main {


    
    enum TypeOfPerson {PASSENGER, FLIGHTCREW, CHECKINSTUFF, STORESTUFF, GATESTUFF };


    public static void main(String args[]) throws IOException {

        ProgramData programData = new ProgramData(true);
        
        // System.out.println(DayOfWeek.FRIDAY.getClass().getName());
        // System.out.println("------------------------------------");
        // ProgramData programData = new ProgramData(true);
        // for(Flight flight : programData.getFlights()) {
        //     System.out.println(flight.getFlightId());
        // }
        // for (int i = 0; i < programData.getFlights().size(); i++) {
        //     System.out.println(programData.getFlights().get(i).getFlightId());
        // }

        // admin.getAirports().get(0).setAirportICAO("PAOKARA");
        // admin.saveData();
        // System.out.println( admin.getAirports().get(0).getAirportICAO());

        // The user's ssn
        // String ssn = "960004856"; // ΔΕΝ ΕΧΕΙ VISITEDSTORES
        // String ssn = "000000000"; //ΔΕΝ ΥΠΑΡΧΕΙ
        // String ssn = "732937483"; //EXEI VISITEDSTORES
        // String ssn = "236578456"; //flightCrew
        // String ssn = "172893875"; //checkInStuff
        // String ssn = "273890473"; //storestuff
        // String ssn = "205981874";
        String ssn = "273259630"; //gatestuff
        // The date of results of the positive test
        LocalDate positiveDate = LocalDate.parse("2020-10-28");
        // System.out.println(positiveDate);
        // LocalDate nowDate = positiveDate;//LocalDate.now()
        LocalDate nowDate = LocalDate.parse("2020-11-02");
        // Type of test
        String testType = "rapid";
        // String testType = "molecular";
        String icao = "LATI"; //if airporttStuff
        // String icao = "ATH";
        // Type of person
        TypeOfPerson typePerson;
        // if passenger
        // typePerson = TypeOfPerson.PASSENGER;
        // if flight crew
        // typePerson = TypeOfPerson.FLIGHTCREW;
        // if airport stuff
        // if check in stuff
        // typePerson = TypeOfPerson.CHECKINSTUFF;
        // if store stuff
        // typePerson = TypeOfPerson.STORESTUFF;
        // if gate stuff
        typePerson = TypeOfPerson.GATESTUFF;
        
        ArrayList<Person> closeContacts = new ArrayList<Person>();
        ArrayList<Person> casualContacts = new ArrayList<Person>();
        ArrayList<Person> contacts = new ArrayList<Person>();

        // Check if have passed more than 30 days since the test
        if(positiveDate.plusDays(30).compareTo(nowDate) > 0){
            // If test was molecular, we search for flight until 7 days before without the day of the test
            int daysBack = 8;
            if(testType.equals("rapid")){
                // If test was rapid, we search for flight until 6 days before, plus the day of the test
                daysBack = 7;
                positiveDate = positiveDate.plusDays(1);
            }
            LocalDate lastSearchDate = positiveDate.minusDays(daysBack);
            LocalDate flightDate;
            LocalDateTime flightDateTime;
            Airport workingAirport = null;
            int sumFlights = programData.getFlights().size() - 1;
        
            switch(typePerson) {
                case PASSENGER:
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = programData.getFlights().get(id).getDepartureDateTime().toLocalDate();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            contacts = programData.getFlights().get(id).findCloseContactsOfPassenger(ssn);
                            if (contacts.size() != 0) {
                                for (int i = 0; i < contacts.size(); i++ ) {
                                    if (!closeContacts.contains(contacts.get(i))) {
                                        closeContacts.add(contacts.get(i));
                                    }
                                }
                            }
                            contacts = programData.getFlights().get(id).findCasualContactsOfPassenger(ssn);
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
                    break;
                case FLIGHTCREW:
                    for (int id = sumFlights; id >= 0; id--) {
                        flightDate = programData.getFlights().get(id).getDepartureDateTime().toLocalDate();
                        if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                            if (programData.getFlights().get(id).ifExistsFlightCrew(ssn)) {
                                contacts = programData.getFlights().get(id).findCloseContactsOfFlightCrew(ssn);
                                for (int i = 0; i < contacts.size(); i++ ) {
                                    if (!closeContacts.contains(contacts.get(i))) {
                                        closeContacts.add(contacts.get(i));
                                    }
                                }
                            }
                        }
                    }
                    if (closeContacts.size() == 0) {
                        System.out.println("There are no flights containing the employee with ssn " + ssn);
                    } else {
                        System.out.println(closeContacts);
                    }
                break;
                case CHECKINSTUFF:
                    for (Airport airport : programData.getAirports()) {
                        if (airport.getAirportICAO().equals(icao)) {
                            workingAirport = airport;
                            break;
                        }
                    }
                    if (workingAirport != null) {
                        AirportStuff checkInEmployee;
                        checkInEmployee = workingAirport.getCheckInPlace().getAirportStuffBySSN(ssn);
                        if (checkInEmployee != null) {
                            for (int id = sumFlights; id >= 0; id--) {
                                flightDate = programData.getFlights().get(id).getDepartureDateTime().toLocalDate();
                                flightDateTime = programData.getFlights().get(id).getDepartureDateTime();
                                if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                                    if (checkInEmployee.isWorking(flightDateTime)) {
                                        contacts = programData.getFlights().get(id).findCloseContactsOfCheckInStuff(checkInEmployee);
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
                        } else {
                            System.out.println("This employee seems to not work to this airport");
                        }  
                    } else {
                        System.out.println("There isn't airport with this ICAO");
                    }
                break;
                case STORESTUFF:
                    for (Airport airport : programData.getAirports()) {
                        if (airport.getAirportICAO().equals(icao)) {
                            workingAirport = airport;
                            break;
                        }
                    }
                    if (workingAirport != null) {
                        AirportSection store;
                        store = workingAirport.getWorkingStore(ssn);
                        if (store != null) {
                            AirportStuff storeEmployee;
                            storeEmployee = store.getAirportStuffBySSN(ssn);
                            for (int id = sumFlights; id >= 0; id--) {
                                flightDate = programData.getFlights().get(id).getDepartureDateTime().toLocalDate();
                                flightDateTime = programData.getFlights().get(id).getDepartureDateTime();
                                if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                                    if (storeEmployee.isWorking(flightDateTime)) {
                                        contacts = programData.getFlights().get(id).findCasualContactsOfStoreStuff(storeEmployee, store);
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
                        } else {
                            System.out.println("This employee seems to not work to this airport");
                        }
                    } else {
                        System.out.println("There isn't airport with this ICAO");
                    }
                break;
                case GATESTUFF:
                    for (Airport airport : programData.getAirports()) {
                        if (airport.getAirportICAO().equals(icao)) {
                            workingAirport = airport;
                            break;
                        }
                    }
                    if (workingAirport != null) {
                        AirportSection gate;
                        gate = workingAirport.getWorkingGate(ssn);
                        if (gate != null) {
                            AirportStuff gateEmployee;
                            gateEmployee = gate.getAirportStuffBySSN(ssn);
                            for (int id = sumFlights; id >= 0; id--) {
                                flightDate = programData.getFlights().get(id).getDepartureDateTime().toLocalDate();
                                flightDateTime = programData.getFlights().get(id).getDepartureDateTime();
                                if (flightDate.isAfter(lastSearchDate) && flightDate.isBefore(positiveDate)) {
                                    if (gateEmployee.isWorking(flightDateTime)) {
                                        contacts = programData.getFlights().get(id).findCasualContactsOfGateStuff(gate);
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
                        } else {
                            System.out.println("This employee seems to not work to this airport");
                        }
                    } else {
                        System.out.println("There isn't airport with this ICAO");
                    }
                break;
            }           
        } else {
            System.out.println("It's been a long time since you have done the covid-19 test.");
        }
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
}