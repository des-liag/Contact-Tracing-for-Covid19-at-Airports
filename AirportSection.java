import java.util.ArrayList;

public class AirportSection {

    private String sectionName;
    // An ArrayList that contains objects AirportStuff which are the employees of this section
    // Each section of the airport has its own ArrayList with employees
    private ArrayList<AirportStuff> sectionStuff;


    /**
     * Constructor of the class
     * Creates an airport's section (store, gate or checkIn place) with the specified name
     * @param sectionName The name of the section
     */
    public AirportSection(String sectionName) {
        this.sectionName = sectionName;
        this.sectionStuff = new ArrayList<AirportStuff>();
    }


    /**
     * Sets the section's name
     * @param sectionName String containing the section's name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    /**
     * Gets the section's name
     * @return String representing the section's name
     */
    public String getSectionName() {
        return this.sectionName;
    }


    /**
     * Adds an object AirportStuff to the ArrayList sectionStuff
     * Check if this object already exists into the ArrayList sectionStuff
     * @param sectionStuff Object AirportStuff containing an employee of this section
     * @see AirportStuff
     */
    public void addSectionStuff(AirportStuff sectionStuff) {
        if(!this.sectionStuff.contains(sectionStuff)) {
            this.sectionStuff.add(sectionStuff);
        } else {
            System.out.println("This employee already works in this airport");
        }
    }


    /**
     * Gets the ArrayList sectionStuff with all section's employees
     * @return ArrayList<AirportStuff> containing objects AirportStuff representing the employees of this section
     * @see AirportStuff
     */
    public ArrayList<AirportStuff> getSectionStuff() {
        return this.sectionStuff;
    }
}
