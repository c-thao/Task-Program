package top.app.model;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a task.
 * 
 * @author chou
 *
 */
public class Task {
	
	private final ObjectProperty<LocalDate> startDate;
	private StringProperty name;
	private StringProperty description;
	private ObjectProperty<LocalDate> endDate;
	
	/**
	 * Default Constructor.
	 */
	public Task() {
		this.name = new SimpleStringProperty();
		this.description = new SimpleStringProperty();
		startDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		endDate = new SimpleObjectProperty<LocalDate>();
	}
	
	/**
	 * Constructor with initial value for the name, the
	 * description, and the current date for the startDate.
	 * 
	 * @param name        a string to initialize the name
	 * @param description a string to initialize the description
	 */
	public Task(String name, String description) {
		System.out.println("Creating a new instance of a task: " + name.toString());
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		startDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		endDate = new SimpleObjectProperty<LocalDate>();
	}
	
	/**
	 * Returns the name as a String.
	 * 
	 * @return the name as a string
	 */
	public String getName() {
		return name.get();
	}
	
	/**
	 * Sets the name to the argument name.
	 * 
	 * @param name the String to assign to the name
	 */
	public void setName(String name) {
		this.name.set(name);
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public StringProperty NameProperty() {
		return name;
	}
	
	/**
	 * Returns the description as a string.
	 * 
	 * @return the description as a string
	 */
	public String getDescription() {
		return description.get();
	}
	
	/**
	 * Returns the description.
	 * 
	 * @return the description
	 */
	public StringProperty DescriptionProperty() {
		return description;
	}
	
	/**
	 * Sets the description.
	 * 
	 * @param description string to assign to the description
	 */
	public void setDescription(String description) {
		this.description.set(description);
	}
	
	/**
	 * Returns the startDate.
	 * 
	 * @return the startDate
	 */
	public ObjectProperty<LocalDate> StartDateProperty() {
		return startDate;
	}
	
	/**
	 * Returns the startDate as a LocalDate.
	 * 
	 * @return the startDate as a LocalDate
	 */
	public LocalDate getStartDate() {
		return startDate.get();
	}
	
	/**
	 * Returns the endDate.
	 * 
	 * @return the endDate
	 */
	public ObjectProperty<LocalDate> EndDateProperty() {
		return endDate;
	}
	
	/**
	 * Returns the endDate as a LocalDate.
	 * 
	 * @return the endDate as a LocalDate
	 */
	public LocalDate getEndDate() {
		return endDate.get();
	}
	
	/**
	 * Sets the endDate.
	 * 
	 * @param endDate a LocalDate to assign to the endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate.set(endDate);
	}
}
