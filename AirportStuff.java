public class AirportStuff {
    public boolean isWorking(LocalDateTime dateTime){
        DayOfWeek day = dateTime.getDayOfWeek();
        LocalTime time = dateTime.toLocalTime();
        for(LocalTime[] localtimes : this.workHoursMap.get(day)){
            if(time.isAfter(localtimes[0]) && time.isBefore(localtimes[1])){
                return true;
            }
        }
        return false;
    }
}
