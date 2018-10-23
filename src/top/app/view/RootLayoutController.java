package top.app.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import top.app.MainApp;

/**
 * This is the controller for the root layout.
 * 
 * @author chou
 *
 */
public class RootLayoutController {
	private MainApp mainApp;
	
	/**
	 * Sets the mainApp to the argument mainApp.
	 * 
	 * @param mainApp a MainApp to assign to the mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	/**
	 * Creates a New application file.
	 */
	@FXML
	private void handleNew() {
		// TODO: implement a blank application file
		System.out.println("Creating a new application file.");
	}
	
	/**
	 * Opens an file dialog for a user to select an
	 * application file to open.
	 */
	@FXML
	private void handleOpen() {
		// TODO: create a file dialog and load the application
		// file selected by an user
		System.out.println("Opening an application file.");
	}
	
	/**
	 * Saves an application file if the current application
	 * file opened exists, else opens a file dialog to prompt
	 * for saving an new application file.
	 */
	@FXML
	private void handleSave() {
		// TODO: save the application file and only prompt
		// a file dialog if the file was not previously saved/loaded
		System.out.println("Saving the application file.");
	}
	
	/**
	 * Opens a file dialog to prompt for saving an application
	 * file.
	 */
	@FXML
	private void handleSaveAs() {
		// TODO: open a file dialog to let a user chose where to
		// save the application file
		System.out.println("Saving file as...");
	}
	
	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		// TODO: close any files if any are open
		System.out.println("Closing the application.");
		System.exit(0);
	}
	
    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
		System.out.println("Accessing application information...");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Task Application");
        alert.setHeaderText("About");
        alert.setContentText("Author: Chou Thao\n"
        		+ "Email: chou.thao.1993@gmail.com\n"
        		+ "Website: https://github.com/c-thao");
        alert.showAndWait();
    }
	
	
}
