import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AirportStuff extends Person {

    // Each column is about the time that employee start working every day of the week
    private Date[][] workHours = new Date[1][7];

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
     * @param workHours An array of employee's work hours
     */
    public AirportStuff(String ssn, String name, String lastName, String address, String phone, String[][] workHours) {
        super(ssn, name, lastName, address, phone);
        setWorkHours(workHours);
    }


    /**
     * Sets the work hours that employee starts working
     * @param workHours String 2D-array containing the work hours of employee
     */
    public void setWorkHours(String[][] workHours) {
        // handle ParseException if input has other form
        try {
            for (int i = 0; i < 7; i++) {
                // TODO convert String to Date (HH:mm)
                this.workHours[0][i ]= new SimpleDateFormat("HH:mm").parse(workHours[0][i]);
            }
        } catch(ParseException e) {
            System.out.println("You should type workHours in format 'HH:mm'");
        }
    }


    /**
     * Gets the work hours of the employee
     * @return Date[][] representing the work hours of employee
     */
    public Date[][] getWorkHours() {
        return this.workHours;
    }

}
