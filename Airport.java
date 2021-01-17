import java.time.LocalDateTime;

public class Airport {
    public Person getWorkingEmployee(LocalDateTime dateTime){
        for(AirportStuff employee : this.getCheckInPlace().getSectionStuff()){
            if(employee.isWorking(dateTime)){
                return employee;
            }
        }
        return null;
    }

    public ArrayList<AirportStuff> getEmployeesByGate(String gateName){
        for(AirportSection gate : this.getGates()){
            if(gate.getSectionName().equals(gateName)){
                return gate.getSectionStuff();
            }
        }
        return new ArrayList<AirportStuff>();
    }

}