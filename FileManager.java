import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<Airport> loadAirports(String fileName) {

        ArrayList<Airport> airports = new ArrayList<Airport>();
        String FilePath = new File(fileName).getAbsolutePath();

        try (BufferedReader br = new BufferedReader(
                new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                String icao = values[0];
                String name = values[1];
                airports.add(new Airport(icao, name));
                // records.add(line);
            }
        } catch (Exception ex) {
            System.out.println("\nLoad failed.\n");
            // ex.printStackTrace();
            System.exit(1);
        }
        // System.out.println("\nLoaded successfully.\n");



        Mymap<String[], AirportSection> gatesMap = new  Mymap<String[], AirportSection>();
        Mymap<String[], AirportSection> storesMap = new  Mymap<String[], AirportSection>();
        Mymap<String[], AirportSection> checkInPlacesMap = new  Mymap<String[], AirportSection>();
        ArrayList<String[]> sectionsFile = loadStringsFile("CSVFiles//sections.csv//");
        // for(int i=0; i< sectionsFile.size(); i++){
        //     System.out.println("-----------------------");

        //     for(int k=0; k< sectionsFile.get(i).length; k++){
        //         System.out.println(sectionsFile.get(i)[k]);
        //     }
        //     System.out.println("-----------------------");
        // }
        for(int i=0; i < sectionsFile.size(); i++){
            // int SectionId = Integer.parseInt(sectionsFile.get(i)[0]);
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
        
        ArrayList<Person> flightCrew = new ArrayList<Person>();
        ArrayList<Person> passengers = new ArrayList<Person>();
        ArrayList<String[]> peopleFile = loadStringsFile("CSVFiles//people.csv//");
     
        for(int i=0; i < peopleFile.size(); i++){
            String SSN = peopleFile.get(i)[0];
            String name = peopleFile.get(i)[2];
            String lastName = peopleFile.get(i)[3];
            String address = peopleFile.get(i)[4];
            String phone = peopleFile.get(i)[5];
            String type = peopleFile.get(i)[6];
            String startTimeString = peopleFile.get(i)[7];
            String endTimeString = peopleFile.get(i)[8];
            String sectionIdString = peopleFile.get(i)[9];
            
            if(type.equals("PASSENGER")){
                passengers.add(new Person(SSN, name, lastName, address, phone));
            } else if(type.equals("FLIGHTCREW")) {
                flightCrew.add(new Person(SSN, name, lastName, address, phone));
            } else {
                AirportStuff sectionEmployee  = new AirportStuff(SSN, name, lastName, address, phone, startTimeString, endTimeString);

                if(gatesMap.containsKey1(sectionIdString)){
                    gatesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                } else if(storesMap.containsKey1(sectionIdString)){
                    storesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                } else {
                    checkInPlacesMap.getByKey1(sectionIdString).addSectionStuff(sectionEmployee);
                }
            }
        }

        //Import gates to airports
        for(String[] key : gatesMap.getKeysArrayList()){
            for(int k=0;k < airports.size();k++){
                if(airports.get(k).getAirportICAO().equals(key[1])){
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

        for(int i = 0; i < airports.size(); i++) {
            // System.out.println(airports.get(i).getAirportICAO());
            // System.out.println(airports.get(i).getAirportName());
            // System.out.println(airports.get(i).stores);
            // System.out.println(airports.get(i).getGates());
            // System.out.println(airports.get(i).getGates().size());
            for(int j = 0; j < airports.get(i).getGates().size(); j++){
                // System.out.println("Inn");
                // System.out.println(airports.get(i).getGates().size());
            }
        }
        return airports;        
    }


    // public ArrayList<Flight> loadFlights(String fileName) {

    //     // ArrayList<Airport> flights = new ArrayList<Flight>();
    //     // String FilePath = new File(fileName).getAbsolutePath();
    //     // System.out.print("Loading file: "+FilePath+": ");

    //     // // ArrayList<String> records = new ArrayList<String>();
    //     // try (BufferedReader br = new BufferedReader(
    //     //         new FileReader(FilePath))) {
    //     //     String line;
    //     //     while ((line = br.readLine()) != null) {
    //     //         String values[] = line.split(";");
    //     //         String depICAO = values[1];
    //     //         String destICAO = values[2];
    //     //         airports.add(new Airport(icao, name));
    //     //         // records.add(line);
    //     //         System.out.println(values[0]);
    //     //     }
    //     // } catch (Exception ex) {
    //     //     System.out.println("\nLoad failed.\n");
    //     //     ex.printStackTrace();
    //     //     System.exit(1);
    //     // }
    //     // System.out.println("\nLoaded successfully.\n");
    // }


    private static ArrayList<String[]> loadStringsFile(String fileName) {

        ArrayList<String[]> strings = new ArrayList<String[]>();
        String FilePath = new File(fileName).getAbsolutePath();
        // System.out.print("Loading file: "+FilePath+": ");

        // ArrayList<String> records = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(
                new FileReader(FilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String values[] = line.split(";");
                strings.add(values);
             
            }
        } catch (Exception ex) {
            System.out.println("\nLoad failed.\n");
            ex.printStackTrace();
            System.exit(1);
        }
        // System.out.println("\nLoaded successfully.\n");
        // for(int i=0; i< strings.size(); i++){
        //     // System.out.println(strings.get(i));
        //     for(int k=0; k< strings.get(i).length; k++){
        //         System.out.println(strings.get(i)[k]);
        //     }
        // }
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



