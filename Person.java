import java.io.Serializable;

public class Person implements Serializable {

    private String SSN;
    private String name;
    private String lastName;
    private String address;
    private String phone;

    public Person(String SSN, String name, String lastName, String address, String phone) {
		this.SSN = SSN;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}


	public void setSSN(String SSN) {
		this.SSN = SSN;
    }


	public String getSSN() {
		return SSN;
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

	public String getFullName() {
		return getName() + " " + getLastName();
	}
}
