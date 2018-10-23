package top.app.view;

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
import javafx.scene.control.TextField;
import top.app.MainApp;
import top.app.model.Task;
import top.app.model.TaskItem;
import top.app.model.User;

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
	private TextField descriptionField;
	
	private ObservableList<User> users = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox<User> assignUserBox;
	@FXML
	private ComboBox<User> unassignUserBox;
	
	@FXML
	private DatePicker selectEndDate;
	
	private MainApp mainApp;
	
	/**
	 * Default Constructor.
	 */
	public ApplicationLayoutController() {}
	
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
			nameLabel.setText(taskItem.getTaskName());
			creatorLabel.setText(taskItem.getCreatorName());
			userLabel.setText(taskItem.getAssignedUsersName());
			
			// TODO: fix LocalDate to be converted into a string
			//startDateLabel.setText(taskItem.getStartDate());
			//endDateLabel.setText(taskItem.getEndDate());
			
			descriptionField.setText(taskItem.getTask().getDescription());
		} else {
			System.out.println("Clearing application details.");
			nameLabel.setText("N/A");
			creatorLabel.setText("undef");
			userLabel.setText("undef");
			
			// TODO: fix LocalDate to be converted into a string
			//startDateLabel.setText(taskItem.getStartDate());
			//endDateLabel.setText(taskItem.getEndDate());
			
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
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Error");
	        alert.setHeaderText("Unable to process delete.");
	        alert.setContentText("No task were selected. Unable to process delete.");
	        alert.showAndWait();
		}
	}
	
	/**
	 * This function handles the creation of a TaskItem.
	 */
	public void handleNew() {
		System.out.println("Creating a new task.");
	}
	
}
