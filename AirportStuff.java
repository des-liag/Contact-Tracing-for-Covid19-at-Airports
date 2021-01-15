import java.util.ArrayList;
import java.util.HashMap;
import java.time.*; 

public class AirportStuff extends Person {

    private HashMap<DayOfWeek, ArrayList<LocalTime[]>> workHoursMap; 

    public AirportStuff(String SSN, String name, String lastName, String address, String phone) {
        super(SSN, name, lastName, address, phone);
        this.workHoursMap = new  HashMap<DayOfWeek,ArrayList<LocalTime[]>>();
        setWorkHoursMap();
    }

    public void setWorkHoursMap() {
        for(DayOfWeek day : DayOfWeek.values()){
            workHoursMap.put(day, new ArrayList<LocalTime[]>());
        }
    }

    public boolean addWorkHours(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        return this.workHoursMap.get(day).add(new LocalTime[]{startTime, endTime});
    }

    public boolean addWorkHours(DayOfWeek day, String startTime, String endTime) {
        LocalTime startTimeDate = LocalTime.parse(startTime);
        LocalTime endTimeDate = LocalTime.parse(endTime);
        return this.workHoursMap.get(day).add(new LocalTime[]{startTimeDate, endTimeDate});
    }


}
