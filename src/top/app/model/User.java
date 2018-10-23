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
	 * Override the toString method to format the User
	 * into a String.
	 * 
	 * @return the User as a string
	 */
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}
}
