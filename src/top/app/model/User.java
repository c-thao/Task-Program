package top.app.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a User.
 * 
 * @author chou
 *
 */
public class User {
	private final StringProperty firstName;
	private final StringProperty lastName;
	
	/**
	 * Default Constructor.
	 */
	public User () {
		this(null,null);
	}
	
	/**
	 * Constructor with initial values.
	 * 
	 * @param firstName a string to initialize firstName
	 * @param lastName  a string to initialize lastName
	 */
	public User(String firstName, String lastName) {
		System.out.println("Creating a new instance of a user: " + firstName.toString() + " " + lastName.toString());
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
	}
	
	/**
	 * Returns the firstName as a String.
	 * 
	 * @return the firstName as a string
	 */
	public String getFirstName() {
		return firstName.get();
	}
	
	/**
	 * Returns the firstName.
	 * 
	 * @return the firstName
	 */
	public StringProperty firstNameProperty() {
		return firstName;
	}
	
	/**
	 * Returns the lastName as a String.
	 * 
	 * @return the lastName as a string
	 */
	public String getLastName() {
		return lastName.get();
	}
	
	/**
	 * Returns the lastName.
	 * 
	 * @return the lastName
	 */
	public StringProperty lastNameProperty() {
		return lastName;
	}
	
	
	/**
	 * Overrides the toString method to format the User
	 * into a String.
	 * 
	 * @return the User as a string
	 */
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
	
	/**
	 * Overrides the equal method to check if an Object
	 * o is equal to the user.
	 * 
	 * @param o the Object to check if the user is equal
	 *          to
	 */
	@Override
	public boolean equals(Object o) {
		// check if same object
		if (o == this) return true;
		
		// check if o is a User object
		if (!(o instanceof User)) return false;
		
		// typecast o to a User object and check if
		// it's equal to the user
		User object = (User) o;
		if (object.firstNameProperty().equals(firstName) &&
				object.lastNameProperty().equals(lastName)) {
			return true;
		}
		return false;
	}
}
