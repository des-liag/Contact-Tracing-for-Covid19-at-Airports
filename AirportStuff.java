import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AirportStuff extends Person {

    private Date startTime;
    private Date endTime;


    /**
     * Constructor of the class
     * Creates an employee (storeStuff, gateStuff or checkInStuff) with the specified elements which are inherited by class Person
     * {@see Person} and its work hours
     * Work hours of each employee are stable
     * @param ssn The SSN of the employee
     * @param name The first name of the employee
     * @param lastName The last name of the employee
     * @param address The address of the employee
     * @param phone The phone of the employee
     * @param startTime The time employee starts working
     * @param endTime The time employee ends working
     */
    public AirportStuff(String ssn, String name, String lastName, String address, String phone, String startTime, String endTime) {
        super(ssn, name, lastName, address, phone);
        setStartTime(startTime);
        setEndTime(endTime);
    }


    /**
     * Sets the time that employee starts working
     * @param startTime String containing the time employee starts working
     */
    public void setStartTime(String startTime) {
        // handle ParseException if input has other form
        try {
            // convert String to Date (HH:mm)
            this.startTime = new SimpleDateFormat("HH:mm").parse(startTime);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type startTime in format 'HH:mm'");
        }
    }


    /**
     * Gets the time that employee starts working
     * @return Date representing the time employee starts working
     */
    public Date getStartTime() {
        return this.startTime;
    }


    /**
     * Sets the time that employee ends working
     * @param endTime String containing the time employee ends working
     */
    public void setEndTime(String endTime) {
        // handle ParseException if input has other form
        try {
            // convert String to Date (HH:mm)
            this.endTime = new SimpleDateFormat("HH:mm").parse(endTime);
        } catch(ParseException e) {
            e.printStackTrace();
            System.out.println("You should type endTime in format 'HH:mm'");
        }
    }


    /**
     * Gets the time that employee ends working
     * @return Date representing the time employee ends working
     */
    public Date getEndTime() {
        return this.endTime;
    }


    /**
     * Gets the work hours of employee
     * @return String the work hours of employee
     */
    public String getWorkHours() {
        return getStartTime().toString() + "-" + getEndTime().toString();
    }

}
