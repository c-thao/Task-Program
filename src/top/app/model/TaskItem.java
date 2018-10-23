package top.app.model;

import java.util.ArrayList;

/**
 * Model class for a TaskItem.
 * 
 * @author chou
 *
 */
public class TaskItem extends Task{
	//private final Task task;
	private final User creator;
	private ArrayList<User> assignedUsers = new ArrayList<User>();
	
	/**
	 * Default constructor.
	 */
	public TaskItem() {
		this(null, null);
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
	 * Returns the creator.
	 * 
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
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
		for (int i = 0; i<assignedUsers.size(); i++) {
			userList += assignedUsers.get(i).toString();
			if (i != assignedUsers.size() - 1) {
				userList += ", ";
			}
		}
		return userList;
	}
}
