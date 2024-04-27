
// Registration.java
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import java.util.List;




public class Registration extends Application {

	private FileHandler fileHandler = new FileHandler();
 

	public Registration() {

	}

	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(" Registration Page");
		
			
		

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));	
		grid.setVgap(15);

		
		 Label welcomeLabel = new Label("Create an Account");
	        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight:"
	        		+ " bold; -fx-text-fill: white;");
	        welcomeLabel.setPrefWidth(200);
	        grid.add(welcomeLabel, 0, 0);  
	        grid.setAlignment(Pos.BASELINE_CENTER);
	        
		Label nameLabel = new Label("Name:");
		nameLabel.setStyle("-fx-text-fill: white;");
		TextField nameField = new TextField();
		grid.add(nameLabel, 0, 2);
		grid.add(nameField, 1, 2);

		Label userLabel = new Label("Username:");
		userLabel.setStyle("-fx-text-fill: white;");
		TextField userField = new TextField();
		grid.add(userLabel, 0, 3);
		grid.add(userField, 1, 3);

		Label emailLabel = new Label("Email:");
		emailLabel.setStyle("-fx-text-fill: white;");
		TextField emailField = new TextField();
		emailField.setPromptText("Musa5@icloud.com");
		grid.add(emailLabel, 0, 4);
		grid.add(emailField, 1, 4);

		Label roleLabel = new Label("Role:");
		roleLabel.setStyle("-fx-text-fill: white;");
		ComboBox<String> roleCombo = new ComboBox<>();
		roleCombo.getItems().addAll("Employee", "Employer");
		grid.add(roleLabel, 0, 5);
		grid.add(roleCombo, 1, 5);

		Label provinceLabel = new Label("Province:");
		provinceLabel.setStyle("-fx-text-fill: white;");
		ComboBox<String> provinceCombo = new ComboBox<>();
		provinceCombo.getItems().addAll("Gauteng", "Western Cape",
				"KwaZulu-Natal", "Eastern Cape", "Free State",
				"Limpopo", "Mpumalanga", "Northern Cape", "North West");

		grid.add(provinceLabel, 0, 6);
		grid.add(provinceCombo, 1, 6);

		Label passLabel = new Label("Password:");
		passLabel.setStyle("-fx-text-fill: white;");
		PasswordField passField = new PasswordField();
		grid.add(passLabel, 0, 7);
		grid.add(passField, 1, 7);

		Label confirmPassLabel = new Label("Confirm Password:");
		confirmPassLabel.setStyle("-fx-text-fill: white;");
		PasswordField confirmPassField = new PasswordField();
		grid.add(confirmPassLabel, 0, 8);
		grid.add(confirmPassField, 1, 8);

		Circle passMatchIndicator = new Circle(10);
		passMatchIndicator.setFill(Color.RED);

		passField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals(confirmPassField.getText())) {
				passMatchIndicator.setFill(Color.GREEN);
			} else {
				passMatchIndicator.setFill(Color.RED);
			}
		});

		confirmPassField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.equals(passField.getText())) {
				passMatchIndicator.setFill(Color.GREEN);
			} else {
				passMatchIndicator.setFill(Color.RED);
			}
		});

		grid.add(passMatchIndicator, 2, 8);

		Button registerButton = new Button("Register");
		grid.add(registerButton, 1, 9);

		registerButton.setOnAction(e -> {
			try {
				String name = nameField.getText();
				String username = userField.getText();
				String email = emailField.getText();
				String role = roleCombo.getValue();
				String province = provinceCombo.getValue();
				String password = passField.getText();
				String confirmPassword = confirmPassField.getText();

				if (!password.equals(confirmPassword)) {
					// Show an error message that passwords do not match
					  Alert alert = new Alert(Alert.AlertType.ERROR);
			            alert.setTitle("Password Mismatch");
			            alert.setHeaderText(null);
			            alert.setContentText("The passwords do not match. Please try again.");
			            alert.showAndWait();
			            return;
				}
		        // Check for duplicate username or role
		        List<User> existingUsers = fileHandler.readUsers();
		        for (User existingUser : existingUsers) {
		            if (existingUser.getUsername().equals(username)) {
		                Alert alert = new Alert(Alert.AlertType.ERROR);
		                alert.setTitle("Duplicate Username");
		                alert.setHeaderText(null);
		                alert.setContentText("The username is already taken. Please choose a different one.");
		                alert.showAndWait();
		                return;
		            }
		            
		        }



				KeyPair keyPair = generateKeyPair();
				User user = new User(name, username, email, role, province, password, keyPair);
				fileHandler.writeUser(user); // Write the new user to the file
				System.out.println("User registered successfully!");
				new Login().start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				 Alert alert = new Alert(Alert.AlertType.ERROR);
			        alert.setTitle("Registration Error");
			        alert.setHeaderText(null);
			        alert.setContentText("An error occurred during registration. Please try again.");
			        alert.showAndWait();
				ex.printStackTrace();
			}
		});

		// Create a Hyperlink instance
		Hyperlink loginLink = new Hyperlink("Already registered? Click here to Login.");

		// Set the action to be performed when the hyperlink is clicked
		loginLink.setOnAction(e -> {
			try {
				new Login().start(new Stage());
				primaryStage.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});

		// Add the hyperlink layout
		grid.add(loginLink, 1, 10);
	   
	    grid.setBackground(new Background(new BackgroundFill(Color.web("#19376D"), CornerRadii.EMPTY, Insets.EMPTY)));

	
	
	
	
		Scene scene = new Scene(grid, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(2048);
		return keyGen.generateKeyPair();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
