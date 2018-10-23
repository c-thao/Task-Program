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
	
	private final StringProperty name;
	private final ObjectProperty<LocalDate> startDate;
	
	private StringProperty description;
	private ObjectProperty<LocalDate> endDate;
	
	/**
	 * Default Constructor.
	 */
	public Task() {
		this(null, null);
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
	
	public void setDescription(String description) {
		this.description.set(description);
	}
	
	/**
	 * Returns the startDate as a string.
	 * 
	 * @return the startData as a string
	 */
	// TODO: need to workout for localdate to work correctly
	public LocalDate getStartDate() {
		return startDate.get();
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
	 * Returns the endDate as a string.
	 * 
	 * @return endData as a string
	 */
	// TODO: need to workout for localdate to work correctly
	public LocalDate getEndDate() {
		return endDate.get();
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
	 * Sets the endDate.
	 * 
	 * @param endDate a LocalDate to assign to the endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate.set(endDate);
	}
}
