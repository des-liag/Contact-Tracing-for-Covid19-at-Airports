package Airports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * FileManager class manages the CSV files in order to initialize data
 * Its methods are called only the first time that program runs
 * Every other time, data have been saved to binary files
 */
public class FileManager {

    /**
     * Read and write data of file airports.csv
     * @param fileName String containing the name of the csv file
     * @return ArrayList<Airport> representing all the objects Airport with necessary information about them
     */
    public static ArrayList<Airport> loadAirports(String fileName) {

        ArrayList<Airport> airports = new ArrayList<Airport>();
        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            //read csv file line by line
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                String icao = values[0];
                String name = values[1];
                //create new Airport and get it into the ArrayList 
                airports.add(new Airport(icao, name));
            }
        } catch (Exception e) {
            System.out.println("\nLoad failed.\n");
            System.exit(1);
        }

        //A map in which put all the gates of the file
        Mymap<String[], AirportSection> gatesMap = new  Mymap<String[], AirportSection>();
        //A map in which put all the stores of the file
        Mymap<String[], AirportSection> storesMap = new  Mymap<String[], AirportSection>();
        //A map in which put all the checkIn places of the file
        Mymap<String[], AirportSection> checkInPlacesMap = new  Mymap<String[], AirportSection>();
        //Read and write data of file sections.csv
        ArrayList<String[]> sectionsFile = loadStringsFile("CSVFiles//sections.csv//");


        for(int  i= 0; i < sectionsFile.size(); i++){
            String sectionId = sectionsFile.get(i)[0];
            String iciao = sectionsFile.get(i)[1];
            String typeOfSection = sectionsFile.get(i)[2];
            String name = sectionsFile.get(i)[3];
            
            //the key of each map contists of id and icao because this combination makes exery section unique
            String[] key = new String[]{sectionId, iciao};
            if(typeOfSection.equals("GATE")){
                //is created new AirportSection gate
                gatesMap.put(key, new AirportSection(name));
            } else if(typeOfSection.equals("STORE")) {
                //is created new AirportSection store
                storesMap.put(key, new AirportSection(name));
            } else {
                //is created new AirportSection check-In place
                checkInPlacesMap.put(key, new AirportSection(name));
            }
        }

        //Read and write data of file workHours.csv
        ArrayList<String[]> workHoursFile = loadStringsFile("CSVFiles//workHours.csv//");
        //A map in which put all workk-hour programs from file
        HashMap<Integer, HashMap<DayOfWeek, LocalTime[]>> workHoursMap = new HashMap<Integer, HashMap<DayOfWeek, LocalTime[]>>();

        //each line of file represents one work-hour program that has unique id
        for (int j = 0; j < workHoursFile.size(); j++) {
            int id = Integer.parseInt(workHoursFile.get(j)[0]);
            //how many hours work every day
            int hours = Integer.parseInt(workHoursFile.get(j)[8]);

            //as key is the id and as values a nested HashMap with key the day of the week and values an array with 2 elements: the start and end time of work 
            workHoursMap.put(id,new HashMap<DayOfWeek, LocalTime[]>());

            for (int index = 1; index <= 7; index++) {
                DayOfWeek day = DayOfWeek.of(index);
                String startTime =  workHoursFile.get(j)[index];
                //if null means that this day employee doesn't work
                if(!startTime.equals("NULL")){
                    LocalTime startTimeDate = LocalTime.parse(startTime);
                    LocalTime endTimeDate = LocalTime.parse(startTime).plusHours(hours);
                    workHoursMap.get(id).put(day, new LocalTime[]{startTimeDate, endTimeDate});
                }
            }
        }

        //Read and write data of file people.csv
        //This file have 3 types of people: AirportStuff, FlightCrew and Passenger
        ArrayList<String[]> peopleFile = loadStringsFile("CSVFiles//people.csv//");

        for (int i=0; i < peopleFile.size(); i++) {
            String ssn = peopleFile.get(i)[0];
            String name = peopleFile.get(i)[2];
            String lastName = peopleFile.get(i)[3];
            String address = peopleFile.get(i)[4];
            String phone = peopleFile.get(i)[5];
            String type = peopleFile.get(i)[6];
            //the id of work-hour program in order to match it
            String workHoursString = peopleFile.get(i)[7];
            String sectionIdString = peopleFile.get(i)[8];

            if(type.equals("AIRPORTSTUFF")) {
                int workHoursId = Integer.parseInt(workHoursString);
                //is created new AirportStuff
                AirportStuff sectionEmployee  = new AirportStuff(ssn, name, lastName, address, phone);
                HashMap<DayOfWeek, LocalTime[]> workHoursOfEmployeeMap =  workHoursMap.get(workHoursId);
                //is created the work-hour program of employee
                for(DayOfWeek day : workHoursOfEmployeeMap.keySet()){
                    sectionEmployee.addWorkHours(day, workHoursOfEmployeeMap.get(day)[0], workHoursOfEmployeeMap.get(day)[1]);
                }

                if(gatesMap.containsKey1(sectionIdString)){
                    //add gate stuff to a gate
                    gatesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                } else if(storesMap.containsKey1(sectionIdString)) {
                    //add store stuff to a store
                    storesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                } else {
                    //add check-In stuff to a check-In place
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

    /**
     * Read and write data of file flights.csv
     * @param fileName String containing the name of the csv file
     * @param airportsFromFile ArrayList<Airport> that has been created previously from files
     * @param flightCrewFromFile  ArrayList<Person> that has been created previously from files
     * @param passengersFromFile ArrayList<Person> that has been created previously from files
     * @return ArrayList<Flight> representing all the objects Flight with necessary information about them
     */
    public static ArrayList<Flight> loadFlights(
        String fileName, ArrayList<Airport> airportsFromFile, ArrayList<Person> flightCrewFromFile, ArrayList<Person> passengersFromFile ) {

        ArrayList<Flight> flights = new ArrayList<Flight>();
        ArrayList<Airport> airports = airportsFromFile;
        ArrayList<Person> flightCrew = flightCrewFromFile;
        ArrayList<Person> passengers = passengersFromFile;

        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            //read csv file line by line
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
                //create new Flight and get it into the ArrayList 
                flights.add(new Flight(depAirport, destAirport, depDateString, destDateString));
            }
        } catch (Exception e) {
            System.out.println("\nLoad failed.\n");
            System.exit(1);
        }

        //Read and write data of file flightsData.csv
        //this file contains information about tickets of every flight and of every passenger
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

            //the gates of departure's airport of the flight
            ArrayList <AirportSection> thisDepAirportGates = flights.get(flightId).getDepartureAirport().getGates();
            //the gates of destination's airport of the flight
            ArrayList <AirportSection> thisDestAirportGates = flights.get(flightId).getDestinationAirport().getGates();


            if(personType.equals("FLIGHTCREW")){
                for (int j = 0; j < flightCrew.size(); j++) {
                    if (flightCrew.get(j).getSSN().equals(ssn)){
                        //import flightCrew to flights
                        flights.get(flightId).addFlightCrew(flightCrew.get(j));
                    }
                }
            } else if (personType.equals("PASSENGER")) {
                for (int j = 0; j < passengers.size(); j++) {
                    if (passengers.get(j).getSSN().equals(ssn)) {
                        for(int k = 0; k < thisDepAirportGates.size(); k++) {
                            if (thisDepAirportGates.get(k).getSectionName().equals(depGateName)) {
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

                        //is created a new ticket
                        Ticket ticket = new Ticket(passengers.get(j), checkInTime, ifLuggage, depGate, destGate);
                        //a map with visited stores
                        Mymap<String[], String[]> visitedStoresMap = loadVisitedStoresMap();

                        for(String[] value : visitedStoresMap.getValuesArrayListByKey1and2(ssn,((Integer)(flightId + 1)).toString())){
                            AirportSection store = flights.get(flightId).getDepartureAirport().getStoreByName(value[0]);
                            //create new VisitedStore and add them to ticket
                            ticket.addDepartureVisitedStore(new VisitedStore(value[1], store));
                        }
                        //add tickets to flights
                        flights.get(flightId).addTicket(ticket);
                    }
                } 
            }  
        }
        return flights;
    }

    /**
     * Create a map that contains the visited stores of the csv file
     * @return the map
     */
    private static Mymap<String[], String[]> loadVisitedStoresMap(){
        //Read and write data of file people.csv
        ArrayList<String[]> visitedStoresFile = loadStringsFile("CSVFiles//visitedStores.csv//");
        //map that has as key the combination of passenger's ssn, flight's id and file's id in an array, that makes it unique,
        //and as value an array of store's name and time that passenger got into the store
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

    /**
     * Read and write CSV files
     * @param fileName String contatining the file's name
     * @return ArrayList<String[]> containing all lines of the file
     */
    private static ArrayList<String[]> loadStringsFile(String fileName) {

        ArrayList<String[]> strings = new ArrayList<String[]>();
        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String line;
            //read line by line
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

    /**
     * Creates the arraylist with all flightCrew
     * @param fileName String contatining the file's name
     * @return ArrayList<Person> with all flightCrew
     */
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
                //create new person and add it to the arraylist
                flightCrew.add(new Person(SSN, name, lastName, address, phone));
            }
        }
        return flightCrew;
    }

    /**
     * Creates the arraylist with all passengers
     * @param fileName String contatining the file's name
     * @return ArrayList<Person> with all passengers
     */
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
                //create new person and add it to the arraylist
                passengers.add(new Person(SSN, name, lastName, address, phone));
            }
        }
        return passengers;
    }

}
