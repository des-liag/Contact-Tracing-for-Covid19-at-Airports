import java.io.Serializable;

public class Person implements Serializable {

    private String ssn;
    private String name;
    private String lastName;
    private String address;
	private String phone;
	// A variable that shows if the covid test of person is positive
	private boolean isPositive;
	// A variable that shows whether person is informed or not
    private boolean isInformed;


	/**
	 * Costructor of the class
	 * Creates a person with the specified ssn, name, last name, address and phone
	 * @param ssn The ssn of person
	 * 				It's unique for each person
	 * @param name The first name of person
	 * @param lastName The last name of person
	 * @param address The address of person
	 * @param phone The phone of person
	 */
    public Person(String ssn, String name, String lastName, String address, String phone) {
		this.ssn = ssn;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.isPositive = false;
		this.isInformed = false;
	}


	/**
	 * Sets the ssn of the person
	 * @param ssn String containing the ssn of the person
	 */
	public void setSSN(String ssn) {
		this.ssn = ssn;
    }


	/**
	 * Gets the ssn of the person
	 * @return String representing the ssn of the person
	 */
	public String getSSN() {
		return ssn;
	}


	/**
	 * Sets the first name of the person
	 * @param name String containing the first name of the person
	 */
	public void setName(String name) {
		this.name = name;
    }


	/**
	 * Gets the first name of the person
	 * @return String representing the first name of the person
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the last name of the person
	 * @param lastName String containing the last name of the person
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * Gets the last name of the person
	 * @return String representing the last name of the person
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Sets the address of the person
	 * @param address String containing the address of the person
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * Gets the address of the person
	 * @return String representing the address of the person
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * Sets the phone of the person
	 * @param phone String containing the phone of the person
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * Gets the phone of the person
	 * @return String representing the phone of the person
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * Sets the variable isPositive as true if covid test of this person is positive
	 * @param isPositive Boolean containing the information about covid test of this person
	 */
	public void setPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}


	/**
	 * Gets the variable isPositive informing about covid test of this person
	 * @return boolean representing the information about covid test of this person
	 */
	public boolean getPositive() {
		return isPositive;
	}


	/**
	 * Sets the variable isInformed as true if this person has been informed that he/she maybe has been
	 * in contact with a coronavirus case
	 * @param isInformed Boolean containing the information if this person has been informed
	 */
	public void setInformed(boolean isInformed) {
		this.isInformed = isInformed;
	}


	/**
	 * Gets the variable isInformed that declares if this person has been informed
	 * @return boolean depending on whether this person has been informed or not
	 */
	public boolean getInformed() {
		return isInformed;
	}


	/**
	 * Gets the full name of the person
	 * @return String representing the full name of the person
	 */
	public String getFullName() {
		return getName() + " " + getLastName();
	}
}
