package Airports;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.*;

public class AirportStuff extends Person {

    // A map that has as key one of the days of the week, and as value an arraylist which has inside an array with 2 rows
    // The first row is about the time when employee starts working this day and the second is about the time of the end of working
    private HashMap<DayOfWeek, ArrayList<LocalTime[]>> workHoursMap;

    /**
     * Constructor of the class
     * Creates an employee (storeStuff, gateStuff or checkInStuff) with the specified elements which are inherited by class Person
     * @see Person
     * @param ssn The SSN of the employee
     * @param name The first name of the employee
     * @param lastName The last name of the employee
     * @param address The address of the employee
     * @param phone The phone of the employee
     */
    public AirportStuff(String SSN, String name, String lastName, String address, String phone) {
        super(SSN, name, lastName, address, phone);
        this.workHoursMap = new  HashMap<DayOfWeek,ArrayList<LocalTime[]>>();
        setWorkHoursMap();
    }

    /**
     * Sets the work hours of the employee for everyday
     */
    public void setWorkHoursMap() {
        for(DayOfWeek day : DayOfWeek.values()){
            workHoursMap.put(day, new ArrayList<LocalTime[]>());
        }
    }

    /**
     * Gets the work hours of the employee for every day
     * @return HashMap representing the hours that each employee works
     */
    public HashMap<DayOfWeek, ArrayList<LocalTime[]>> getWorkHoursMap() {
        return workHoursMap;
    }

    /**
     * Adds the work hour of one day of the week for an employee
     * @param day DayofWeek containing a day of the week
     * @param startTime LocalTime containing the time when employee starts working
     * @param endTime LocalTime containing the time when employee ends working
     * @return HashMap representing the weekly work hours of an employee
     */
    public boolean addWorkHours(DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        return this.workHoursMap.get(day).add(new LocalTime[]{startTime, endTime});
    }

    /**
     * Adds the work hour of one day of the week for an employee
     * @param day DayofWeek containing a day of the week
     * @param startTime String containing the time when employee starts working
     * @param endTime String containing the time when employee ends working
     * @return HashMap representing the weekly work hours of an employee
     */
    public boolean addWorkHours(DayOfWeek day, String startTime, String endTime) {
        // Convert String startTime to LocalTime
        LocalTime startTimeDate = LocalTime.parse(startTime);
        // Convert String endTime to LocalTime
        LocalTime endTimeDate = LocalTime.parse(endTime);
        return this.workHoursMap.get(day).add(new LocalTime[]{startTimeDate, endTimeDate});
    }

    /**
     * Gets for a date and time we search for if a employee works at that time
     * Finds the day of the dateTime and the time of the dateTime
     * @param dateTime
     * @return boolean depending if there is someone working the day and time we look for at the workHoursMap
     */
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
