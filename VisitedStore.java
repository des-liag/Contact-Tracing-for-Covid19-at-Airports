import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VisitedStore {

    private Date entranceTime;
    private AirportSection store;

    public VisitedStore(String entranceTime, AirportSection store) {
        setEntranceTime(entranceTime);
        this.store = store;
    }

    public void setEntranceTime(String entranceTime) {
        try {
            this.entranceTime = new SimpleDateFormat("HH:mm").parse(entranceTime);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("You should type entranceTime in format 'HH:mm'");
        }
    }
    public Date getEntranceTime() {
        return this.entranceTime;
    }

    public AirportSection getStore() {
        return this.store;
    }

    public void setStore(AirportSection store) {
        this.store = store;
    } 
}
