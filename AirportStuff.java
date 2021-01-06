import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AirportStuff extends Person {

    private Date startTime;
    private Date endTime;

    public AirportStuff(String SSN, String name, String lastName, String address, String phone, String startTime, String endTime) {
        super(SSN, name, lastName, address, phone);
        setStartTime(startTime);
        setEndTime(endTime);
    }

    public void setStartTime(String startTime) {
        try {
            this.startTime = new SimpleDateFormat("HH:mm").parse(startTime);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type startTime in format 'HH:mm'");
        }
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setEndTime(String endTime) {
        try {
            this.endTime = new SimpleDateFormat("HH:mm").parse(endTime);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type endTime in format 'HH:mm'");
        }
    }

    public Date getEndTime() {
        return this.endTime;
    }

}
