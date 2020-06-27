package sprint05.PracticeTaskExceptions;

//	Create class Person with private fields firstName,  lastName, idCode. Create two classes inherited from RuntimeException: NameException and CodeException. 
//	NameException will be thrown if the first name or last name is invalid (don't start from uppercase or contains not only letters and symbols "-" and " "). 
//			CodeException will be thrown if idCode is invalid (valid idCode contains exactly 10 digits). 
//			In class Person create setters methods that throw NameException or CodeException if appropriate arguments are not valid. 
//			Create static method buildPerson(String firstName, String lastName, String idCode) that returns Person if all arguments are valid otherwise it thrown IllegalArgumentException with message about all invalid arguments.  
//			Override method toString() for Person class that returns representation of Person in form: firstName lastName: idCode. Override equals and hashCode methods for Person class.
//	
//	For example
//	
//	Person p = new Person(); 
//	p.setFirstName("joe") throw NameException with message “Incorrect value joe for firstName (should start from upper case and contains only alphabetic characters and -, space symbol;)” 
//	p.setIdCode("2") throw CodeException with message "Incorrect value 2 for code (should contains exactly 10 digits)"
//	Person.buildPerson("Joe", "KlarK", "AS-2") throw IllegalArgumentException with message "Incorrect value KlarK for lastName (should start from upper case and contains only alphabetic characters and -, _;); Incorrect value AS-2 for code (should contains exactly 10 digits)"

class NameException extends RuntimeException {
	public NameException(String name) {
		super(name);
	}
}

class CodeException extends RuntimeException {
	public CodeException(String name) {
		super(name);
	}
}

class Person {
	private String firstName;
	private String lastName;
	private String idCode;
	
	public void setFirstName(String firstName) throws NameException {
		if (firstName == null || firstName.startsWith(".*[a-z].*") ||!firstName.matches("^[A-Z]+([a-z]*(-)?[A-Za-z]+)?"))
			throw new NameException("Incorrect value " + firstName + " for firstName (should start from upper case and contains only alphabetic characters and -, space symbol;)");
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) throws NameException {
		if (lastName == null || lastName.startsWith(".*[a-z].*")  || !lastName.matches("^[A-Z]+([a-z]*(-)?[A-Za-z]+)?"))
			throw new NameException("Incorrect value " + lastName + " for firstName (should start from upper case and contains only alphabetic characters and -, space symbol;)");
		this.lastName = lastName;
	}
	
	public void setIdCode(String idCode) {
		if (idCode.length() != 10 || !idCode.matches("[0-9]+"))
			throw new CodeException("Incorrect value " + idCode + " for code (should contains exactly 10 digits)");
		this.idCode = idCode;
	}
	
	public static Person buildPerson(String firstName, String lastName, String idCode) throws CodeException, NameException {
		Person person = new Person();
		try {
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setIdCode(idCode);
		} catch (RuntimeException ex) {
			throw new IllegalArgumentException("Incorrect value " + firstName + " for firstName (should start from upper case and contains only alphabetic characters and symbols -, _); Incorrect value " + idCode + " for code (should contains exactly 10 digits)");
		}
		return person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((idCode == null) ? 0 : idCode.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (idCode == null) {
			if (other.idCode != null)
				return false;
		} else if (!idCode.equals(other.idCode))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + ": " + idCode;
	}
}