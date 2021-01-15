import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {

    public static ArrayList<Airport> loadAirports(String fileName) {

        ArrayList<Airport> airports = new ArrayList<Airport>();
        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                String icao = values[0];
                String name = values[1];
                airports.add(new Airport(icao, name));
            }
        } catch (Exception e) {
            System.out.println("\nLoad failed.\n");
            System.exit(1);
        }

        Mymap<String[], AirportSection> gatesMap = new  Mymap<String[], AirportSection>();
        Mymap<String[], AirportSection> storesMap = new  Mymap<String[], AirportSection>();
        Mymap<String[], AirportSection> checkInPlacesMap = new  Mymap<String[], AirportSection>();
        ArrayList<String[]> sectionsFile = loadStringsFile("CSVFiles//sections.csv//");


        for(int  i= 0; i < sectionsFile.size(); i++){
            String sectionId = sectionsFile.get(i)[0];
            String iciao = sectionsFile.get(i)[1];
            String typeOfSection = sectionsFile.get(i)[2];
            String name = sectionsFile.get(i)[3];

            String[] key = new String[]{sectionId, iciao};
            if(typeOfSection.equals("GATE")){
                gatesMap.put(key, new AirportSection(name));
            } else if(typeOfSection.equals("STORE")) {
                storesMap.put(key, new AirportSection(name));
            } else {
                checkInPlacesMap.put(key, new AirportSection(name));
            }
        }


        ArrayList<String[]> workHoursFile = loadStringsFile("CSVFiles//workHours.csv//");
        HashMap<Integer, HashMap<DayOfWeek, LocalTime[]>> workHoursMap = new HashMap<Integer, HashMap<DayOfWeek, LocalTime[]>>();

        for (int j = 0; j < workHoursFile.size(); j++) {
            int id = Integer.parseInt(workHoursFile.get(j)[0]);
            int hours = Integer.parseInt(workHoursFile.get(j)[8]);

            workHoursMap.put(id,new HashMap<DayOfWeek, LocalTime[]>());

            for (int index = 1; index <= 7; index++) {
                DayOfWeek day = DayOfWeek.of(index);
                String startTime =  workHoursFile.get(j)[index];
                if(!startTime.equals("NULL")){
                    LocalTime startTimeDate = LocalTime.parse(startTime);
                    LocalTime endTimeDate = LocalTime.parse(startTime).plusHours(hours);
                    workHoursMap.get(id).put(day, new LocalTime[]{startTimeDate, endTimeDate});
                }
            }
        }

        ArrayList<String[]> peopleFile = loadStringsFile("CSVFiles//people.csv//");

        for (int i=0; i < peopleFile.size(); i++) {
            String ssn = peopleFile.get(i)[0];
            String name = peopleFile.get(i)[2];
            String lastName = peopleFile.get(i)[3];
            String address = peopleFile.get(i)[4];
            String phone = peopleFile.get(i)[5];
            String type = peopleFile.get(i)[6];
            String workHoursString = peopleFile.get(i)[7];

            String sectionIdString = peopleFile.get(i)[8];

            if(type.equals("AIRPORTSTUFF")) {
                int workHoursId = Integer.parseInt(workHoursString);
                AirportStuff sectionEmployee  = new AirportStuff(ssn, name, lastName, address, phone);
                HashMap<DayOfWeek, LocalTime[]> workHoursOfEmployeeMap =  workHoursMap.get(workHoursId);

                for(DayOfWeek day : workHoursOfEmployeeMap.keySet()){
                    sectionEmployee.addWorkHours(day, workHoursOfEmployeeMap.get(day)[0], workHoursOfEmployeeMap.get(day)[1]);
                }

                if(gatesMap.containsKey1(sectionIdString)){
                    gatesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                } else if(storesMap.containsKey1(sectionIdString)) {
                    storesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                } else {
                    checkInPlacesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                }
            }
        }

        //Import gates to airports
        for(String[] key : gatesMap.getKeysArrayList()){
            for(int k = 0; k < airports.size(); k++){
                if(airports.get(k).getAirportICAO().equals(key[1])) {
                    airports.get(k).addGate(gatesMap.get(key));
                }
            }
        }

        //Import stores to airports
        for(String[] key : storesMap.getKeysArrayList()){
            for(int k=0;k < airports.size();k++){
                if(airports.get(k).getAirportICAO().equals(key[1])){
                    airports.get(k).addStore(storesMap.get(key));
                }
            }
        }

         //Import checkin places to airports
        for(String[] key : checkInPlacesMap.getKeysArrayList()){
            for(int k=0;k < airports.size();k++){
                if(airports.get(k).getAirportICAO().equals(key[1])){
                    airports.get(k).setCheckInPlace(checkInPlacesMap.get(key));
                }
            }
        }
        return airports;        
    }

    public static ArrayList<Flight> loadFlights(
        String fileName, ArrayList<Airport> airportsFromFile, ArrayList<Person> flightCrewFromFile, ArrayList<Person> passengersFromFile ) {

        ArrayList<Flight> flights = new ArrayList<Flight>();
        ArrayList<Airport> airports = airportsFromFile;
        ArrayList<Person> flightCrew = flightCrewFromFile;
        ArrayList<Person> passengers = passengersFromFile;

        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                String depICAOString = values[1];
                String destICAOString = values[2];
                String depDateString = values[3];
                String destDateString = values[4];

                Airport depAirport = null;
                Airport destAirport = null;

                for(int i = 0; i < airports.size(); i++) {
                    if(airports.get(i).getAirportICAO().equals(depICAOString)){
                        depAirport = airports.get(i);
                    } else if(airports.get(i).getAirportICAO().equals(destICAOString)) {
                        destAirport = airports.get(i);
                    }
                }
                flights.add(new Flight(depAirport, destAirport, depDateString, destDateString));
            }

        } catch (Exception e) {
            System.out.println("\nLoad failed.\n");
            System.exit(1);
        }

        ArrayList<String[]> flightsDataFile = loadStringsFile("CSVFiles//flightsData.csv//");

        for(int i = 0; i < flightsDataFile.size(); i++) {
            String ssn = flightsDataFile.get(i)[0];
            int flightId = Integer.parseInt(flightsDataFile.get(i)[1]) - 1;
            String personType = flightsDataFile.get(i)[2];
            String checkInTime = flightsDataFile.get(i)[3];
            Boolean ifLuggage = Boolean.parseBoolean(flightsDataFile.get(i)[4]);
            String depGateName = flightsDataFile.get(i)[5];
            String destGateName = flightsDataFile.get(i)[6];

            AirportSection depGate = null;
            AirportSection destGate = null;

            ArrayList <AirportSection> thisDepAirportGates = flights.get(flightId).getDepartureAirport().getGates();
            ArrayList <AirportSection> thisDestAirportGates = flights.get(flightId).getDestinationAirport().getGates();


            if(personType.equals("FLIGHTCREW")){
                for (int j = 0; j < flightCrew.size(); j++) {
                    if ((flightCrew.get(j).getSSN()).equals(ssn)){
                        flights.get(flightId).addFlightCrew(flightCrew.get(j));
                    }
                }
            } else if (personType.equals("PASSENGER")) {
                for (int j = 0; j < passengers.size(); j++) {
                    if ((passengers.get(j).getSSN()).equals(ssn)) {
                        for(int k = 0; j < flights.get(flightId).getDepartureAirport().getGates().size(); k++) {
                            if (flights.get(flightId).getDepartureAirport().getGates().get(k).getSectionName().equals(depGateName)) {
                                depGate = thisDepAirportGates.get(k);
                                break;
                            } 
                        }
                        for(int k = 0; k < thisDestAirportGates.size(); k++) {
                            if (thisDestAirportGates.get(k).getSectionName().equals(destGateName)) {
                                destGate = thisDestAirportGates.get(k);
                                break;
                            } 
                        }

                        Ticket ticket = new Ticket(passengers.get(j), checkInTime, ifLuggage, depGate, destGate);
                        Mymap<String[], String[]> visitedStoresMap = loadVisitedStoresMap();

                        for(String[] value : visitedStoresMap.getValuesArrayListByKey1and2(ssn,((Integer)(flightId + 1)).toString())){
                            AirportSection store = flights.get(flightId).getDepartureAirport().getStoreByName(value[0]);
                            ticket.addDepartureVisitedStore(new VisitedStore(value[1], store));
                        }

                        flights.get(flightId).addTicket(ticket);
                    }
                } 
            }  
        }
        return flights;
    }

    private static Mymap<String[], String[]> loadVisitedStoresMap(){
        ArrayList<String[]> visitedStoresFile = loadStringsFile("CSVFiles//visitedStores.csv//");
        Mymap<String[], String[]> visitedStoresMap = new  Mymap<String[], String[]>();

        for(int i = 0; i < visitedStoresFile.size(); i++) {
            String id = visitedStoresFile.get(i)[0];
            String ssn = visitedStoresFile.get(i)[1];
            String flightId = visitedStoresFile.get(i)[2];
            String storeName = visitedStoresFile.get(i)[3];
            String entranceTime = visitedStoresFile.get(i)[4];

            String[] key = new String[]{ssn, flightId, id};
            String[] value = new String[]{storeName, entranceTime};
            visitedStoresMap.put(key, value);
        }
        return visitedStoresMap;
    }

    private static ArrayList<String[]> loadStringsFile(String fileName) {

        ArrayList<String[]> strings = new ArrayList<String[]>();
        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                strings.add(values);
            }
        } catch (Exception e) {
            System.out.println("\nLoad failed.\n");
            e.printStackTrace();
            System.exit(1);
        }
        return strings;
    }

    public static ArrayList<Person> getFlightCrew(String fileName){
        ArrayList<Person> flightCrew = new ArrayList<Person>();
        ArrayList<String[]> peopleFile = loadStringsFile(fileName);
        for(int i=0; i < peopleFile.size(); i++){
            String SSN = peopleFile.get(i)[0];
            String name = peopleFile.get(i)[2];
            String lastName = peopleFile.get(i)[3];
            String address = peopleFile.get(i)[4];
            String phone = peopleFile.get(i)[5];
            String type = peopleFile.get(i)[6];
            if(type.equals("FLIGHTCREW")){
                flightCrew.add(new Person(SSN, name, lastName, address, phone));
            }
        }
        return flightCrew;
    }

    public static ArrayList<Person> getPassengers(String fileName){
        ArrayList<Person> passengers = new ArrayList<Person>();
        ArrayList<String[]> peopleFile = loadStringsFile(fileName);
        for(int i=0; i < peopleFile.size(); i++){
            String SSN = peopleFile.get(i)[0];
            String name = peopleFile.get(i)[2];
            String lastName = peopleFile.get(i)[3];
            String address = peopleFile.get(i)[4];
            String phone = peopleFile.get(i)[5];
            String type = peopleFile.get(i)[6];
            if(type.equals("PASSENGER")){
                passengers.add(new Person(SSN, name, lastName, address, phone));
            }
        }
        return passengers;
    }

}
