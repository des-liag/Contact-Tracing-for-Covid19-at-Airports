import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ProgramData implements Serializable {

    private ArrayList<Airport> airports;
    private ArrayList<Person> flightCrew;
    private ArrayList<Person> pasengers;
    private ArrayList<Flight> flights;

    public ProgramData() {
        if (!this.load()) {
            initializeFromFile();
            saveData();
        }
    }

    public ProgramData(Boolean initializeFromFiles) {
        // this();
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

    public void initializeFromFile() {
        this.airports = FileManager.loadAirports("CSVFiles//airports.csv//");
        this.flightCrew = FileManager.getFlightCrew("CSVFiles//people.csv//");
        this.pasengers = FileManager.getPassengers("CSVFiles//people.csv//");
        this.flights = FileManager.loadFlights("CSVFiles//flights.csv//", airports, flightCrew, pasengers);
    }

    // When initilization is completed save to byte file
    public void saveData() {
        this.saveObject(this.airports, "airports");
        this.saveObject(this.flightCrew, "flightCrew");
        this.saveObject(this.pasengers, "passengers");
        this.saveObject(this.flights, "flights");
    }

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

    @SuppressWarnings ("unchecked")
    public boolean load(){
        this.airports = (ArrayList<Airport>) loadObject("airports");
        this.flightCrew = (ArrayList<Person>) loadObject("flightCrew");
        this.pasengers = (ArrayList<Person>) loadObject("passengers");
        this.flights = (ArrayList<Flight>) loadObject("flights");
        if(this.airports == null | this.flightCrew == null | this.pasengers == null | this.flights == null){
            return false;
        }
        return true;
    }

    private Object loadObject(String fileName){
        ObjectInputStream objectinputstream = null;
        Object tempList = null;
        try {
            FileInputStream streamIn = new FileInputStream(fileName + ".ser");
            objectinputstream = new ObjectInputStream(streamIn);
			tempList =  objectinputstream.readObject();
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

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public ArrayList<Person> getFlightCrew() {
        return flightCrew;
    }

    public ArrayList<Person> getPasengers() {
        return pasengers;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

}
