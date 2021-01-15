import java.io.Serializable;
import java.time.LocalDateTime;


public class VisitedStore implements Serializable {

    private LocalDateTime entranceDateTime;
    private AirportSection store;

    public VisitedStore(String entranceDateTime, AirportSection store) {
        setEntranceDateTime(entranceDateTime);
        this.store = store;
    }


    public void setEntranceDateTime(String entranceDateTime) {
        this.entranceDateTime = LocalDateTime.parse(entranceDateTime);
    }

    public LocalDateTime getEntranceDateTime() {
        return this.entranceDateTime;
    }


    public AirportSection getStore() {
        return this.store;
    }


    public void setStore(AirportSection store) {
        this.store = store;
    } 
}
