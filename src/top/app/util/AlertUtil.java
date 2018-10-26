package top.app.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Helper functions to handle alert pop-ups.
 * 
 * @author chou
 *
 */
public class AlertUtil {
	/** The alert object. */
	private static Alert alert = new Alert(AlertType.NONE);
	
	/**
	 * Creates a alert pop up windows with custom head and content text.
	 * 
	 * @param type    a String specifying the type of alert
	 * @param header  a String to display in the header of the alert
	 * @param message a String to display in the content of the aler
	 */
	public static void alertUser(String type, String header, String message) {
		// determine the type of alert depending on
		// the string input type and update the title
		// accordingly
		if (type.equals("INFO")) {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
		}
		else if (type.equals("WARN")) {
			alert.setAlertType(AlertType.WARNING);
			alert.setTitle("WARNING");
		}
		else if (type.equals("ERROR")) {
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("ERROR");
		}
		
		// update header and content text
		alert.setHeaderText(header);
		alert.setContentText(message);
		
		// display and wait for user to close
		alert.showAndWait();
		}
}
