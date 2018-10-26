package top.app.model;

import java.util.ArrayList;

/**
 * Model class for a TaskItem.
 * 
 * @author chou
 *
 */
public class TaskItem extends Task{
	private User creator;
	private ArrayList<User> assignedUsers;
	
	/**
	 * Default constructor.
	 */
	public TaskItem() {
		creator = new User();
		assignedUsers = new ArrayList<User>();
	};
	
	/**
	 * Constructor with initial data for the task and
	 * the creator.
	 * 
	 * @param task    a task to initialize the class
	 * @param creator a user to initialize the creator
	 */
	public TaskItem(Task task, User creator) {
		super(task.getName(), task.getDescription());
		this.creator = creator;
		System.out.print(creator.toString() + " has created a new task ");
		System.out.print(task.getName() + "\n");
		assignedUsers = new ArrayList<User>();
	}
	
	/**
	 * Constructor with initial data for the task, the
	 * creator, and the user.
	 * 
	 * @param task    a task to initialize the class
	 * @param creator a user to initialize the creator
	 * @param user a user to initialize the user
	 */
	public TaskItem(Task task, User creator, User user) {
		super(task.getName(), task.getDescription());
		this.creator = creator;
		System.out.print(creator.toString() + " has created a new task ");
		System.out.print(task.getName() + " and assigned user ");
		assignedUsers = new ArrayList<User>();
		assignedUsers.add(user);
		System.out.print(user.toString());
		System.out.print("\n");
	}
	
	/**
	 * Returns the name of the Task as a String.
	 * 
	 * @return the name of Task as a string
	 */
	public String getTaskName() {
		return getName();
	}
	
	/**
	 * Returns the TaskItem as a Task.
	 * 
	 * @return the TaskItem as a Task
	 */
	public Task getTask() {
		return (Task)this;
	}
	
	/**
	 * Sets the name and description.
	 * 
	 * @param task the Task to assign the name and description.
	 */
	public void setTask(Task task) {
		this.setName(task.getName());
		this.setDescription(task.getDescription());
	}
	
	/**
	 * Sets the name and description.
	 * 
	 * @param name        the String to assign to the name
	 * @param description the String to assign to the description
	 */
	public void setTask(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}
	
	/**
	 * Returns the creator.
	 * 
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}
	
	/**
	 * Sets the creator to the argument creator.
	 * 
	 * @param creator the User to assign to the creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	/**
	 * Returns the creator as a String.
	 * 
	 * @return the creator as a string
	 */
	public String getCreatorName() {
		return creator.toString();
	}

	/**
	 * Returns the ArrayList assignedUsers.
	 * 
	 * @return the ArrayList assignedUsers
	 */
	public ArrayList<User> getAssignedUsers() {
		return assignedUsers;
	}
	
	/**
	 * Adds a user to the ArrayList assignedUsers.
	 * 
	 * @param user a user to add to the ArrayList assignedUsers
	 */
	public void assignANewUser(User user) {
		System.out.println("Assigning user, " + user.toString() + ", to the current task " + this.getName());
		assignedUsers.add(user);
	}
	
	/**
	 * Removes a user from the ArrayList assignedUsers
	 * that matches the argument user.
	 * 
	 * @param user a user to remove from the ArrayList assignedUsers
	 */
	public void unassignACurrentUser(User user) {
		if(assignedUsers.contains(user)) {
			assignedUsers.remove(user);
		}
	}
	
	/**
	 * Returns the ArrayList assignedUsers as a string.
	 * 
	 * @return the ArrayList assignedUsers as a string
	 */
	public String getAssignedUsersName() {
		String userList = "";
		// construct a string of all assigned users
		// name
		for (int i = 0; i<assignedUsers.size(); i++) {
			userList += assignedUsers.get(i).toString();
			if (i != assignedUsers.size() - 1) {
				userList += ", ";
			}
		}
		
		// check to see if userList has been constructed with
		// names, else specify none assigned
		if (userList == null || userList.length() == 0) {
			userList = "None Assigned";
		}
		
		return userList;
	}
}
