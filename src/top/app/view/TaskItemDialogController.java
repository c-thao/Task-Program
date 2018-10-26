package top.app.view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import top.app.model.Task;
import top.app.model.TaskItem;
import top.app.model.User;
import top.app.util.AlertUtil;
import top.app.util.DateUtil;

/**
 * This is a controller for the task list dialog layout.
 * 
 * @author chou
 *
 */
public class TaskItemDialogController {
	@FXML
	private Label creatorLabel;
	@FXML
	private TextField taskNameField;
	@FXML
	private TextArea descriptionField;
	
	@FXML
	private DatePicker selectEndDate;
	
	private Stage dialogStage;
	private TaskItem taskItem;
	private boolean clickedOK;
	private User creator;
	
	/**
	 * Default Constructor.
	 */
	public TaskItemDialogController() {};
	
	/**
	 * Constructor to initialize the creator and the
	 * creatorLabel with the User argument creator.
	 * 
	 * @param creator User to assign to the creator
	 * 
	 */
	public TaskItemDialogController(User creator) {
		System.out.println("Initializing taskitem dialog...");
		this.creator = creator;
		if (creator != null) {
			creatorLabel.setText(creator.toString());
		}

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
	 * Initializes the task list dialog layout.
	 */
	@FXML
	private void initialize() {};
	
	/**
	 * Sets the stage of this dialog.
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**
	 * Sets the creator of the taskItem.
	 */
	public void setCreator(User creator) {
		this.creator = creator;
		if (creator != null) {
			creatorLabel.setText(creator.toString());
		}
	}
	
	/**
	 * Sets the taskItem to the argument taskItem.
	 * 
	 * @param taskItem TaskItem to assign to the taskItem
	 */
	public void setTaskItem(TaskItem taskItem) {
		this.taskItem = taskItem;
	}
	
	/**
	 * Handles the cancel button.
	 */
	public void handleCancel() {
		System.out.println("Closing taskitem dialog.");
		dialogStage.close();
	}
	
	/**
	 * Handles the ok button.
	 */
	public void handleOK() {
		if (checkValidInput()) {
			// setup the creator
			taskItem.setCreator(creator);
			
			System.out.println("Current taskitem state date: " + 
					DateUtil.formatDate(taskItem.getStartDate()));
			
			// setup the task name and description
			taskItem.setTask(
					taskNameField.getText(),
					descriptionField.getText());
			
			// setup the end date for the task
			if (selectEndDate.getValue() != null) {
				taskItem.setEndDate(selectEndDate.getValue());
			}
			
			System.out.println("Data has been processed.");
			System.out.println("Closing taskitem dialog.");
			dialogStage.close();
		}
	}
	
	/**
	 * Checks if the label and fields have valid input to
	 * setup a TaskItem.
	 */
	public boolean checkValidInput() {
		System.out.println("Checking input before processing data.");
		String errorMsg = "";
		if (creator == null) {
			errorMsg = "No creator exist.";
		}
		else if (taskNameField.getText() == null || taskNameField.getText().length() < 3) {
			errorMsg = "Task does not have a proper name.\n"
					+ "Task name must be a minimum of 3 characters.";
		}
		else if (descriptionField.getText() == null || descriptionField.getText().length() < 10 ) {
			errorMsg = "Description is missing or too short.\n"
					+ "Please make sure to write a proper description, 10 characters minimum.";
		} else if (selectEndDate.getValue() != null && 
				DateUtil.compare(selectEndDate.getValue(), LocalDate.now()) < 0) {
			errorMsg = "Error selecting an end date.\n"
					+ "Please select a date from today's date onwards only.";
		}
		
		if (errorMsg != null && !errorMsg.isEmpty()) {
			System.out.println("Error when processing data.");
			AlertUtil.alertUser("ERROR", "Unable to process information.", errorMsg);
			return false;
		}
		return true;
	}
}
