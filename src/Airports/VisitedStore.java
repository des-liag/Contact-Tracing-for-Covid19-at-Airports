package Airports;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VisitedStore implements Serializable {

    private LocalDateTime entranceDateTime;
    private AirportSection store;


    /**
     * Constructor of the class
     * Creates a visited store with the specified entrance date and time and the object store
     * @param entranceDateTime The date and the time when passenger visited the store
     * @param store An object AirportSection representing the store that is visited
     * @see AirportSection
     */
    public VisitedStore(String entranceDateTime, AirportSection store) {
        setEntranceDateTime(entranceDateTime);
        this.store = store;
    }


    /**
     * Sets the date and the time when passenger visited the store
     * @param entranceDateTime String containing the date and the time when passenger visited the store
     */
    public void setEntranceDateTime(String entranceDateTime) {
        this.entranceDateTime = LocalDateTime.parse(entranceDateTime);
    }


    /**
     * Gets the date and the time when passenger visited the store
     * @return LocalDateTime representing the date and the time when passenger visited the store
     */
    public LocalDateTime getEntranceDateTime() {
        return this.entranceDateTime;
    }


    /**
     * Sets the store that passenger visited
     * @param store Object AirportSection containing the store that passenger visited
     * @see AirportSection
     */
    public void setStore(AirportSection store) {
        this.store = store;
    } 


    /**
     * Gets the store that passenger visited
     * @return AirportSection representing the store that passenger visited
     */
    public AirportSection getStore() {
        return this.store;
    }
}
