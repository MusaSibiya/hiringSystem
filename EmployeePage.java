
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;


public class EmployeePage extends Application {

	private String employeeAddress;
	private User user;
	private VBox vbox;
	private TextField skillsField; 
    private ScrollPane scrollPane;
	private TextFlow textFlow;
	 
	public EmployeePage(String employeeAddress, User user) {
		//this.blockchain = BlockchainHolder.blockchain;
		this.employeeAddress = employeeAddress;
		this.user = user;
	}
	TextArea jobApplicationField = new TextArea();
	EmployerPage employerPage = new EmployerPage(employeeAddress, user);

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Employee Page");
		
		Label jobA =  new Label("Write your Motivation Letter");
		Label profileLabel = new Label("Profile");
	    Label SkillsLabel= new Label("Enter your Skills");
	    Label welcomeLabel = new Label("Welcome to Employee Page!");
		Label guide = new Label("1. Enter your skills\n" +
		                        "2. Match your skills \n" +
		                        "3. Before applying make sure you include \n" +
		                        "the brief description/motivational letter");
		
		skillsField = new TextField();
		textFlow = new TextFlow();
		
		HBox guideBox = new HBox(guide);
		VBox profileInfoBox = new VBox();
	    StackPane root = new StackPane();
	    ImageView profileImage = new ImageView(new Image("/musa1.jpg"));
	    Hyperlink profileLink = new Hyperlink(" ", new VBox(profileImage, profileLabel));
	    Hyperlink switchToEmployerPageLink = new Hyperlink("Switch to Employer Page\n"+"for testing purposes");
	    HBox profileBox = new HBox(profileLink, profileInfoBox);
	    HBox profileContainer = new HBox(profileBox);
		HBox profileGuide = new HBox(guide,profileContainer);
		HBox.setHgrow(guideBox, Priority.ALWAYS);
		HBox.setHgrow(profileContainer, Priority.ALWAYS);
	    
	    
	    Button viewAvailableJobButton = new Button("Match Skills ");
	    Button editProfileButton = new Button("Edit Profile");
		

		jobA.setStyle("-fx-text-fill: white;");
	    SkillsLabel.setStyle("-fx-text-fill: white;");
		switchToEmployerPageLink.setStyle("-fx-text-fill: #A5D7E8;");
		viewAvailableJobButton.setStyle("-fx-background-color: #576CBC;"+ "-fx-text-fill: #A5D7E8;");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight:"	+ " bold; -fx-text-fill: white;");
        editProfileButton.setStyle("-fx-background-color: #576CBC; "+ "-fx-text-fill: #A5D7E8;");
    	//submitButton.setStyle("-fx-background-color: #576CBC; "+ "-fx-text-fill: #A5D7E8;");
        guide.setStyle("-fx-background-color: #d1e8ff; " + // Light blue background
	               "-fx-padding: 10px; " +
	               "-fx-font-weight: bold; " +
	               "-fx-border-color: #cccccc; " +
	               "-fx-border-width: 1px;"); 
        
        scrollPane = new ScrollPane(textFlow); //  read-only
    	skillsField.setPromptText(" e.g Java,Problem Solving,Dancer");
    	jobApplicationField.setPromptText("Enter your Skills :\r" +
			       "Enter years of experience :\r" +
			       "A brief statement about career goals:\r");
		
    
        root.getChildren().add(welcomeLabel); 
  
      
		profileImage.setFitWidth(100);
		profileImage.setFitHeight(100);
		profileBox.setAlignment(Pos.TOP_RIGHT);
	  	guideBox.setAlignment(Pos.TOP_LEFT);
		profileContainer.setAlignment(Pos.TOP_RIGHT);
		profileGuide.setAlignment(Pos.TOP_CENTER);
		profileGuide.setSpacing(10);
		jobApplicationField.setPrefHeight(75);
	    scrollPane.setFitToWidth(true);
	    scrollPane.setPrefHeight(90); 
	
        ////////////////////////////////////////////////////////
		viewAvailableJobButton.setOnAction(e -> displayMatchedJobs()
		);
		//////////////////////////////////////////////////////////////

		profileLabel.setStyle("-fx-text-fill: white;");
		
		///////////////////////////////////////////////////////////////
	    editProfileButton.setOnAction(e -> showEditDialog());
	        
	   ////////////////////////////////////////////////////////////////      
	    profileLink.setOnAction(e -> {
			// Clear old profile info
			profileInfoBox.getChildren().clear();
			Label nameLabel = new Label("Name: " + user.getName());
			nameLabel.setStyle("-fx-text-fill: white;");
			Label emailLabel = new Label("Email: " + user.getEmail());
			emailLabel.setStyle("-fx-text-fill: white;");
			Label roleLabel = new Label("Role: " + user.getRole());
			roleLabel.setStyle("-fx-text-fill: white;");
			Label provinceLabel = new Label("Province: " + user.getProvince());
			provinceLabel.setStyle("-fx-text-fill: white;");

			profileInfoBox.getChildren().addAll(nameLabel, emailLabel, 
					roleLabel, provinceLabel,
					editProfileButton);
	        });
             ////////////////////////////////////////////////////////
				switchToEmployerPageLink.setOnAction(e -> {
					
					try {
						employerPage.start(new Stage());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				});
            //////////////////////////////////////////////////////////
			
				vbox = new VBox();
				vbox.getChildren().add(switchToEmployerPageLink);
				   HBox hSwitch = new HBox(switchToEmployerPageLink);
				   hSwitch.setAlignment(Pos.BOTTOM_RIGHT);

	         	vbox = new VBox(welcomeLabel,profileGuide,SkillsLabel ,
				skillsField,viewAvailableJobButton,scrollPane,jobA,
				jobApplicationField,hSwitch);
		        vbox.setPadding(new Insets(10));
	         	vbox.setSpacing(8);
		
	     root = new StackPane(vbox);
	    root.setBackground(new Background(new BackgroundFill(Color.web("#19376D"), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		}
///////////////////////////////////////////////////////////////////////////
	
	 // Method to extract job postings from the blockchain string representation
		Blockchain<String> blockchain=BlockchainHolder.blockchain;
	//////////////////////////////////////////////////////////////////////
	public void applyForJob( String jobPosted ,String employeeProfile, String briefDescription ) {
		briefDescription = jobApplicationField.getText();
		 employeeProfile = "Name: " + user.getName() +
                  ", Email: " + user.getEmail() +
                  ", Province: " + user.getProvince();
	    // Create a transaction with the employee's application
		String jobApplication = employeeProfile + ", \nBrief Description: " + briefDescription;
	    Transaction<String> applicationTransaction = 
	    		new Transaction<>(employeeAddress,"Job Board", jobApplication);
	    List<Transaction<String>> transactions = new ArrayList<>();
	    transactions.add(applicationTransaction);
	    blockchain.addBlock(transactions);
	    // Prepare transaction details for confirmation
	    String transactionDetails = "Applied for: " + jobPosted + 
	    		"\n Application Details: "+jobApplication;
	  
		showConfirmationDialog(transactionDetails) ;
	      
	 System.out.println(blockchain);
	}
	
	/////////////////////////////////////////////////////////////////
	public List<String> extractJobPostings() {
	    List<String> jobPostings = new ArrayList<>();
	    String blockchainString = blockchain.toString(); // Get the string representation of the blockchain
	    Pattern pattern = Pattern.compile("Transaction\\{[^}]*data=([^,]*, Skills Required: [^,]*)");
	    Matcher matcher = pattern.matcher(blockchainString);

	    while (matcher.find()) {
	        String jobPosting = matcher.group(1).trim();
	        jobPostings.add(jobPosting);
	    }

	    return jobPostings;
	}

//////////////////////////////////////////////////////////////////
	public void displayMatchedJobs() {
	    // Clear previous postings if any
	    textFlow.getChildren().clear();

	    List<String> matchedJobPostings = matchJobPostingsWithSkills(skillsField.getText());

	    if (matchedJobPostings.isEmpty()) {
	        Text noMatchFoundText = new Text("No match found.\n");
	        noMatchFoundText.setFill(Color.RED); // Set the text color to red
	        noMatchFoundText.setStyle("-fx-font-weight: bold;"); // Make the text bold
	        textFlow.getChildren().add(noMatchFoundText);
	    } else {
	        for (String jobPosting : matchedJobPostings) {
	            Text matchFoundText = new Text("Match found: ");
	            matchFoundText.setFill(Color.GREEN); // Set the text color to green
	            matchFoundText.setStyle("-fx-font-weight: bold;"); // Make the text bold

	            Text jobPostingText = new Text(jobPosting + " ");
	            jobPostingText.setFill(Color.BLACK); // Set the job posting text color to black

	            Button applyButton = new Button("Apply");
	            applyButton.setOnAction(e -> {
	                // Get the brief description from the TextArea
	                String briefDescription = jobApplicationField.getText();
	                // Construct the employee profile string
	                String employeeProfile = "Name: " + user.getName() +
	                                         ", Email: " + user.getEmail() +
	                                         ", Province: " + user.getProvince();
	                // Call the applyForJob method with the job posting, profile, and brief description
	                applyForJob(jobPosting, employeeProfile,briefDescription);
	            });

	            // Create an HBox to hold the match found text, job posting text, and the apply button
	            HBox jobHBox = new HBox(matchFoundText, jobPostingText, applyButton);
	            jobHBox.setAlignment(Pos.CENTER_LEFT); // Align items to the left
	            jobHBox.setSpacing(5); // Set spacing between items

	            // Add the HBox to the textFlow and then add a new line
	            textFlow.getChildren().addAll(jobHBox, new Text("\n"));
	        }
	    }
	}
	////////////////////////////////////////////////////////////////
	public void showConfirmationDialog(String transactionDetails) {
		
		 TextArea textArea = new TextArea("Your application was successfully sent!!.\n\n" + transactionDetails);
		    textArea.setEditable(false);
		  textArea.setWrapText(true);
		    ScrollPane scrollPane = new ScrollPane(textArea);
		    scrollPane.setFitToWidth(true);
		    scrollPane.setPrefHeight(200); 
	    // Create a new alert dialog
		    
	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Application Confirmation");
	    alert.setHeaderText(null);
	    alert.getDialogPane().setContent(scrollPane);
	    alert.getDialogPane().lookup(".content").setStyle("-fx-background-color: #d1e8ff;"); // Light blue background
	    // Show the dialog and wait for it to be closed
	    alert.showAndWait();
	}

   /////////////////////////////////////////////////
	    public List<String> matchJobPostingsWithSkills(String employeeSkills) {
	        List<String> matchedJobPostings = new ArrayList<>();
	        List<String> jobPostings = extractJobPostings(); // Make sure this method is correctly implemented

	        // Split the employee's skills into a list
	        List<String> employeeSkillList = Arrays.asList(employeeSkills.split("\\s*,\\s*"));
	       // System.out.println("Employee Skills List: " + employeeSkillList);

	        for (String jobPosting : jobPostings) {
	            // Assume the job posting format is "Job Title, Skills Required: skill1, skill2, ..."
	            String[] parts = jobPosting.split(", Skills Required: ");
	            if (parts.length < 2) continue; // Skip if the format is not correct

	            List<String> requiredSkills = Arrays.asList(parts[1].split("\\s*,\\s*"));
	            System.out.println("Required Skills for " + parts[0] + ": " + requiredSkills);

	            // Check if the employee has all the required skills
	            if (employeeSkillList.containsAll(requiredSkills)) {
	                matchedJobPostings.add(jobPosting);
	                System.out.println("Match found: " + jobPosting);
	            }
	        }

	        System.out.println("Matched Job Postings: " + matchedJobPostings);
	        return matchedJobPostings;
	    }

////////////////////////////////////////////////////////////////
	    /**
	     * TO DO Save and change actual user info on userss dat
	     */
		private void showEditDialog() {
	        Dialog<User> editDialog = new Dialog<>();
	        editDialog.setTitle("Edit Profile");
	        editDialog.setHeaderText("Update your profile information");
	     DialogPane dialogPane = editDialog.getDialogPane();
	     dialogPane.setStyle("-fx-background-color:#19376D");
	        // Create input fields for editing
	        TextField nameField = new TextField(user.getName());
	        TextField emailField = new TextField(user.getEmail());
	       // TextField roleField = new TextField(user.getRole());
	        TextField provinceField = new TextField(user.getProvince());
	        Label nameLabel = new Label("Name:");
	        nameLabel.setStyle("-fx-text-fill: white;"); // Sets the text color to white

	        Label emailLabel = new Label("Email:");
	        emailLabel.setStyle("-fx-text-fill: white;");

Label provinceLabel = new Label("Province:");
provinceLabel.setStyle("-fx-text-fill: white;");

	        // Add input fields to the dialog
	        editDialog.getDialogPane().setContent(new VBox(
	                nameLabel, nameField,
	              emailLabel, emailField,
	               // new Label("Role:"), roleField,
	              provinceLabel, provinceField
	        ));

	        // Set dialog buttons
	        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
	        editDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

	        // Handle button actions
	        editDialog.setResultConverter(dialogButton -> {
	            if (dialogButton == saveButtonType) {
	                user.setName(nameField.getText());
	                user.setEmail(emailField.getText());
	                //user.setRole(roleField.getText());
	                user.setProvince(provinceField.getText());

	                // Update corresponding labels in your UI
	                nameLabel.setText("Name: " + user.getName());
	                emailLabel.setText("Email: " + user.getEmail());
	               // roleLabel.setText("Role: " + user.getRole());
	                provinceLabel.setText("Province: " + user.getProvince());
	            }
	            return null;
	        });

	        editDialog.showAndWait();
	    }
//////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		launch(args);
		
	}
}