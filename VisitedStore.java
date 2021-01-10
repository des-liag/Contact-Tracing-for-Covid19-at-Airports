import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitedStore {

    private Date entranceTime;
    private AirportSection store;


    /**
     * Constructor of the class
     * Creates a visited store with the specified entrance time and the object store
     * @param entranceTime The time when passenger visited the store
     * @param store An object AirportSection representing the store that is visited
     * @see AirportSection
     */
    public VisitedStore(String entranceTime, AirportSection store) {
        setEntranceTime(entranceTime);
        this.store = store;
    }


    /**
     * Sets the time when passenger visited the store
     * @param entranceTime String containing the time when passenger visited the store
     */
    public void setEntranceTime(String entranceTime) {
        // handle ParseException if input has other form
        try {
            // convert String to Date (HH:mm)
            this.entranceTime = new SimpleDateFormat("HH:mm").parse(entranceTime);
        } catch (ParseException e) {
            System.out.println("You should type entranceTime in format 'HH:mm'");
        }
    }


    /**
     * Gets the time when passenger visited the store
     * @return Date representing the time when passenger visited the store
     */
    public Date getEntranceTime() {
        return this.entranceTime;
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
