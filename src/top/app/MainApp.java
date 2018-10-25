package top.app;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import top.app.model.Task;
import top.app.model.TaskItem;
import top.app.model.User;
import top.app.view.ApplicationLayoutController;
import top.app.view.RootLayoutController;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ObservableList<TaskItem> taskItemData =  FXCollections.observableArrayList();
	private ObservableList<User> users = FXCollections.observableArrayList();
	
	private User currentUser;
	
	/**
	 * Constructor with sample data for the ObservableList
	 * taskListData.
	 */
	public MainApp() {
		// generate sample users
		users.add(new User("Chou", "Thao"));
		users.add(new User("James", "Kan"));
		users.add(new User("Bob", "Dole"));
		users.add(new User("John", "Bread"));
		users.add(new User("Ben", "Hack"));
		users.add(new User("Barak", "Cheese"));
		
		
		// generate sample data to begin
		System.out.println("Generating a list of tasks...");
		taskItemData.add(new TaskItem(
				new Task("Repair Computer", "Fix a professor's computer."),
				users.get(0)));
		taskItemData.add(new TaskItem(
				new Task("Backup Hard Disk", "Backup a client's hard disk."),
				users.get(1)));
		taskItemData.add(new TaskItem(
				new Task("Sanitize Hard Disk",
						"Backup a client's hard disk if possible and sanitize "
						+ "the harddisk according to DOD guidelines."),
				users.get(2)));
		
		// assign users to a task
		taskItemData.get(0).assignANewUser(users.get(3));
		taskItemData.get(0).assignANewUser(users.get(4));
		taskItemData.get(1).assignANewUser(users.get(5));
		taskItemData.get(2).assignANewUser(users.get(1));
		
		// set current user
		currentUser = users.get(5);
	}
	
	/**
	 * Overrides the start method of the Application class
	 * and sets the primaryStage to argument primaryStage.
	 * Initializes the rootLayout of the primary stage from
	 * function call initRootLayout() and displayApplicationLayout().
	 * 
	 * @param primaryStage the Stage to assign to the primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		// initalize the stage
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Task Manager");
		
		// initalize the root layout
		System.out.println("Setting up the root layout...");
		initRootLayout();
		System.out.println("Root layout setup complete.");
		
		// initalize the application layout
		displayApplicationLayout();
		
		// enable primaryStage to display/show
		System.out.println("Displaying the root layout on the Primary stage now.");
		primaryStage.show();
		
	}
	
	/**
	 * The main function of the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// get today's date and format it then display it as a string
		LocalDate date = LocalDate.now();
		DateTimeFormatter dateFormatter = 
				DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String today = dateFormatter.format(date);
		System.out.println("Today's date is " + today);
		
		System.out.println("Initializing the application...");
		launch(args);
	}
	
	/**
	 * Loads into a scene from a fxml file the root layout of
	 * the application and display it on the primary stage.
	 */
	public void initRootLayout() {
		try {
			// load a fxml file layout and 
			// load the layout into rootLayout
			System.out.println("Loading the root layout from a fxml file...");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.
					getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			System.out.println("Root layout loaded.");
			
			// load the layout into a scene and then
			// set the scene into primaryStage
			System.out.println("Setting up the primary stage...");
			System.out.println("Generating new scene from root layout...");
			Scene root = new Scene(rootLayout);
			System.out.println("Finshed generating new scene.");
			primaryStage.setScene(root);
			System.out.println("Added scene to the primary stage.");
			System.out.println("Finished setting up the Primary stage.");
			
			// give the controller access to the main app
			System.out.println("Giving root controller access to the main app...");
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads into a scene from a fxml file the application layout
	 * and display it on the primary stage.
	 */
	public void displayApplicationLayout() {
		try {
			// load a fxml file layout and 
			// load the layout into rootLayout
			System.out.println("Loading the application layout from a fxml file...");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.
					getResource("view/ApplicationLayout.fxml"));
			AnchorPane applicationLayout = (AnchorPane) loader.load();
			System.out.println("Application layout loaded.");
			
			// load into the root layout the application layout
			rootLayout.setCenter(applicationLayout);
			
			// give the controller access to the main app
			System.out.println("Giving application controller access to the main app...");
			ApplicationLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the ObservableList taskItemData.
	 * 
	 * @return the ObservableList taskItemData
	 */
	public ObservableList<TaskItem> getTaskItemData() {
		return taskItemData;
	}
	
	/**
	 * Returns the ObservableList users.
	 * 
	 * @return the ObservableList users
	 */
	public ObservableList<User> getUsers() {
		return users;
	}
}
