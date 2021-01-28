package Airports;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * AirportSection class represents the sections of the airport. These can be:
 * one checkIn place where passengers do the ckeck-in, stores that passengers can visit and gates where flights depart
 * Each section has its employees that work only in this section
 */
public class AirportSection implements Serializable {

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
     * @return boolean according to succesfully or failed addition
     */
    public boolean addSectionStuff(AirportStuff sectionStuff) {
        for(AirportStuff stuff : this.getSectionStuff()) {
            if(stuff.getSSN().equals(sectionStuff.getSSN())) {
                return false;
            }
        }
        this.sectionStuff.add(sectionStuff);
        return true;
    }

    /**
     * Gets the ArrayList sectionStuff with all section's employees
     * @return ArrayList<AirportStuff> containing objects AirportStuff representing the employees of this section
     * @see AirportStuff
     */
    public ArrayList<AirportStuff> getSectionStuff() {
        return this.sectionStuff;
    }

    /**
     * Gets an AirportStuff employee using the ssn
     * @param ssn unique code of every Object People
     * @return AirportStuff if this ssn belongs to an employee that is Object AirportStuff
     */
    public AirportStuff getAirportStuffBySSN(String ssn) {
        for (AirportStuff airportStuff : this.getSectionStuff()) {
            if (airportStuff.getSSN().equals(ssn)) {
                return airportStuff;
            }
        }
        return null;
    }

    /**
     * Check if an Object AirportStuff exists
     * @param ssn unique code of every Oblect People
     * @return boolean depending on if this Oblect People belongs to AirportStuff
     */
    public boolean ifExistsAirportStuff(String ssn) {
        for (AirportStuff airportStuff : this.sectionStuff) {
            if (airportStuff.getSSN().equals(ssn)) {
                return true;
            }
        }
        return false;
    }
}
