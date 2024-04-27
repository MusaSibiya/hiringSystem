
// Login.java
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;


public class Login extends Application {

	private FileHandler fileHandler = new FileHandler();
	

	public Login() {

	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle(" Login page");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setHgap(10);
		grid.setVgap(20);
		
		 Label welcomeLabel = new Label("Sign Up");
		    welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight:"
	        		+ " bold; -fx-text-fill: white;");
		    welcomeLabel.setPrefWidth(150);
	        grid.add(welcomeLabel, 0, 0);  
	        grid.setAlignment(Pos.BASELINE_CENTER);
		

		Label userLabel = new Label("Username:");
		userLabel.setStyle("-fx-text-fill: white;");
		TextField userField = new TextField();
		userField.setPrefWidth(200);
		grid.add(userLabel, 0, 2);
		grid.add(userField, 1, 2);

		Label passLabel = new Label("Password:");
		passLabel.setStyle("-fx-text-fill: white;");
		PasswordField passField = new PasswordField();
		passField.setPrefWidth(200);
		grid.add(passLabel, 0, 3);
		grid.add(passField, 1, 3);

		Button loginButton = new Button("Login");

		loginButton.setOnAction(e -> {
		    try {
		        List<User> users = fileHandler.readUsers();
		        String username = userField.getText();
		        String password = passField.getText();

		        // Find the user in the list with the matching username
		        User user = users.stream()
		                         .filter(u -> u.getUsername().equals(username))
		                         .findFirst()
		                         .orElse(null);

		        if (user != null && user.getPassword().equals(password)) {
		            System.out.println("Login successful!");
		            // Proceed with opening the user's role-specific page
		         // Check user's role and open the corresponding page
					if ("Employee".equals(user.getRole())) {
						new EmployeePage( username, user).start(new Stage());
					} else if ("Employer".equals(user.getRole())) {
						new EmployerPage(username, user).start(new Stage());
					}
		        } else {
		            System.out.println("Invalid credentials!");
		
					
					primaryStage.close();
				} 
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		grid.add(loginButton, 1, 4);

		Hyperlink regLink = new Hyperlink("Do not have an account? Click here to register.");
		regLink.setOnAction(e -> {
			try {
				new Registration().start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		grid.add(regLink, 1, 5);
	   
	    grid.setBackground(new Background(new BackgroundFill(Color.web("#19376D"), CornerRadii.EMPTY, Insets.EMPTY)));

		Scene scene = new Scene(grid, 450, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
