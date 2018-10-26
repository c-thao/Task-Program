package top.app.view;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;
import top.app.MainApp;
import top.app.model.Task;
import top.app.model.TaskItem;
import top.app.model.User;
import top.app.util.AlertUtil;
import top.app.util.DateUtil;

/**
 * This is a controller for the application layout.
 * 
 * @author chou
 *
 */
public class ApplicationLayoutController {
	@FXML
	private TableView<TaskItem> taskItemTable;
	@FXML
	private TableColumn<Task, String> taskNameColumn;
	
	@FXML
	private Tab taskOverviewTab;
	@FXML
	private Tab taskManagementTab;
	
	@FXML
	private Label creatorLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;
	@FXML
	private Label userLabel;
	
	@FXML
	private TextArea descriptionField;
	
	private ObservableList<User> users = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<User> assignUserBox;// = new ComboBox();
	@FXML
	private ComboBox<User> unassignUserBox;// = new ComboBox();
	
	@FXML
	private DatePicker selectEndDate;
	
	private MainApp mainApp;
	
	/**
	 * Default Constructor.
	 */
	public ApplicationLayoutController() {
		assignUserBox = new ComboBox();
		unassignUserBox = new ComboBox();
		
		// initializes the DatePicker element
		selectEndDate = new DatePicker();
		
		// initializes the converter for the DatePicker element
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			@Override
			public String toString(LocalDate date) {
				return DateUtil.formatDate(date);
			}
			
			@Override
			public LocalDate fromString(String date) {
				return DateUtil.parse(date);
			}
			
			
		};
		selectEndDate.setConverter(converter);
		selectEndDate.setPromptText("dd-MMMM-yyyy");
		
		
	}
	
	/**
	 * Initializes the application layout.
	 */
	@FXML
	private void initialize() {
		System.out.println("Initializing the application layout's data...");
		// initializes the task table columns
		taskNameColumn.setCellValueFactory(
				cellData -> {
					Task t = cellData.getValue();
					return t.NameProperty();
				});
		
		// clear the contents of the application
		showApplicationDetails(null);
		
		// setup a listener for the table to dynamically update
		// the labels
		taskItemTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showApplicationDetails(newValue));
	}
	
	/**
	 * Sets the mainApp initialize taskItemTable with data from
	 * the argument mainApp.
	 * 
	 * @param mainApp a MainApp to assign to the mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// Add observable list of taskItem to the table data
		System.out.println("Retrieving data from main application to the application layout.");
		taskItemTable.setItems(mainApp.getTaskItemData());
		
		// get the observable list of user
		System.out.println("Retrieving data from main application to the application layout.");
		users = mainApp.getUsers();
	}
	
	/**
	 * Displays the application details setting the text of the 
	 * label and description fields. If the taskItem is null,
	 * sets the label and description fields with N/A and undef.
	 * 
	 * @param taskItem a TaskItem to extract field text data
	 */
	public void showApplicationDetails(TaskItem taskItem) {
		if (taskItem != null) {
			System.out.println("Displaying application details for " + taskItem.getTaskName() + ".");
			
			// updates creator, name, and user labels
			System.out.println("Updating creator, task name, and assigned users labels.");
			creatorLabel.setText(taskItem.getCreatorName());
			nameLabel.setText(taskItem.getTaskName());
			userLabel.setText(taskItem.getAssignedUsersName());
			
			// converts the dates into a string and updates the date
			// label
			System.out.println("Updating start and end date labels.");
			if (taskItem.getStartDate() != null) {
				String startDateString = DateUtil.formatDate(taskItem.getStartDate());
				startDateLabel.setText(startDateString);
			} else {
				startDateLabel.setText("N/A");
			}
			if (taskItem.getEndDate() != null) {
				String endDateString = DateUtil.formatDate(taskItem.getEndDate());
				endDateLabel.setText(endDateString);
			} else {
				endDateLabel.setText("UNDEF");
			}
			
			// updates description field
			System.out.println("Updating the task description field.");
			descriptionField.setText(taskItem.getTask().getDescription());
			
			// updates the combobox with list of users
			System.out.println("Updating list inside the comboboxes.");
			listAssignableUsers(taskItem);
			listUnassignableUsers(taskItem);
		} else {
			// default state with no taskitem fields
			System.out.println("Clearing application details.");
			nameLabel.setText("N/A");
			creatorLabel.setText("UNDEF");
			userLabel.setText("N/A");
			startDateLabel.setText("N/A");
			endDateLabel.setText("UNDEF");
			descriptionField.setText("N/A");
		}
	}
	
	/**
	 * This function handles the deletion of a TaskItem.
	 */
	public void handleDelete() {
		int selectedIndex = taskItemTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			System.out.println("Task "
					+ taskItemTable.getItems().get(selectedIndex).getTaskName()
					+ " has been deleted.");
			taskItemTable.getItems().remove(selectedIndex);
		} else {
			System.out.println("No task were selected to be deleted.");
	        AlertUtil.alertUser("ERROR",
	        		"Unable to process delete.", 
	        		"No task were selected. Unable to process delete.");
		}
	}
	
	/**
	 * This function handles the creation of a TaskItem.
	 */
	public void handleNew() {
		System.out.println("Creating a new task.");
		TaskItem taskItem = new TaskItem();
		mainApp.displayTaskItemDialog(taskItem);
		mainApp.getTaskItemData().add(taskItem);
	}
	
	/**
	 * This function handles the cancel button.
	 */
	public void handleCancel() {
		System.out.println("Cancelling task management transaction.");
		
	}
	
	/**
	 * This function handles the apply button.
	 */
	public void handleApply() {
		// get currently selected task item
		TaskItem taskItem = taskItemTable.getSelectionModel().getSelectedItem();
		System.out.println("Applying task management transaction.");
		
		// check if a user has been chosen to be assigned to the task item
		// and add them to the task item
		if (assignUserBox.getSelectionModel().getSelectedItem() != null) {
			User assignedUser = assignUserBox.getSelectionModel().getSelectedItem();
			System.out.println("Assigned user " + assignedUser.toString() + " to the current task.");
			taskItem.assignANewUser(assignedUser);
		}
		
		// check if a user has been chosen to be unassign from the task item
		// and remove them from the task item
		if (unassignUserBox.getSelectionModel().getSelectedItem() != null) {
			User unassignUser = unassignUserBox.getSelectionModel().getSelectedItem();
			System.out.println("Unassigned user " + unassignUser.toString() + " from the current task.");
			taskItem.unassignACurrentUser(unassignUser);
		}
		
		// checks to see if the end date is available
		if (selectEndDate.getValue() != null) { 
			if (DateUtil.compare(selectEndDate.getValue(),taskItem.getStartDate()) > -1) {
				LocalDate endDate = selectEndDate.getValue();
				System.out.println("End date of task set to " + DateUtil.formatDate(endDate));
				taskItem.setEndDate(endDate);
			} else {
	        AlertUtil.alertUser("ERROR",
	        		"Incorrect date selected.", 
	        		"Choose only a date from today and onwards for the end date.");
			}
		}
		
		// update the labels and fields of the application layout
		showApplicationDetails(taskItem);
	}
	
	/**
	 * This function handles the list of users available in
	 * the combo box assignUserBox.
	 */
	public void listAssignableUsers(TaskItem taskItem) {
		System.out.println("Gathering list of users avaiable to assign to the task.");
		// clear out any content currently inside of the combobox
		if (assignUserBox.getItems().size() > 0) {
			assignUserBox.getItems().removeAll(assignUserBox.getItems());
		}
		
		// get all available users that are currently not assigned to the task
		ObservableList<User> assignableUsers = FXCollections.observableArrayList();
		ArrayList<User> assignedUsers = taskItem.getAssignedUsers();
		for (int i = 0; i < users.size(); i++) {
			if (!(assignedUsers.contains(users.get(i)))) {
				if (!(users.get(i).equals(taskItem.getCreator()))) {
					assignableUsers.add(users.get(i));
				}
			}
		}
		
		assignUserBox.setItems(assignableUsers);
	}
	
	/**
	 * This function handles unassigning a user from the list
	 * of assignedUsers in the TaskItem taskItem.
	 */
	public void listUnassignableUsers(TaskItem taskItem) {
		System.out.println("Gathering list of users avaiable to unassign from the task.");
		// clear out any content currently inside of the combobox
		if (unassignUserBox.getItems().size() > 0) {
			unassignUserBox.getItems().removeAll(unassignUserBox.getItems());
		}
		
		// get all the users assigned to the task
		ObservableList<User> unassignableUsers = FXCollections.observableArrayList();
		ArrayList<User> assignedUsers = taskItem.getAssignedUsers();
		for (int i = 0; i < assignedUsers.size(); i++) {
			unassignableUsers.add(assignedUsers.get(i));
		}
		
		unassignUserBox.setItems(unassignableUsers);
	}
}
