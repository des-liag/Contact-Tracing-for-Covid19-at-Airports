public class Person {

    private String ssn;
    private String name;
    private String lastName;
    private String address;
    private String phone;
    private boolean isPositive;
    private boolean isInformed;

    public Person(String ssn, String name, String lastName, String address, String phone) {
		this.ssn = ssn;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

    public void setSSN(String ssn) {
		this.ssn = ssn;
    }

	public String getSSN() {
		return ssn;
	}

    public void setName(String name) {
		this.name = name;
    }

	public String getName() {
		return name;
	}

    public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

    public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPositive(boolean isPositive) {
		this.isPositive = isPositive;
	}

	public boolean getPositive() {
		return isPositive;
	}

	public void setInformed(boolean isInformed) {
		this.isInformed = isInformed;
	}

	public boolean getInformed() {
		return isInformed;
	}

    public String getFullName() {
		return getName() + " " + getLastName();
	}
}
