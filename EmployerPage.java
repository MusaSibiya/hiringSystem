import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc03a3.Block;
import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class EmployerPage extends StackPane {

	/**
	 * initialising lastBlockHash to 0 and use it as 
	 * my Genesis since we dont have getGenesis
	 * @see lastBlockHash 
	 */
    private String lastBlockHash = "0"; 
	private String employerID;
	private TextFlow textFlow;
	private VBox vbox;
	private VBox jobPostingsContainer;
	private   ListView<JobApplication> applicationsListView;
	List<Transaction<HiringService>> transactionBuffer;
    private final int MAX_TRANSACTIONS_PER_BLOCK = 3; 
User user = new User(employerID, employerID, employerID, employerID, employerID, employerID, null);
	public EmployerPage( User user) {
		this.employerID = user.getUsername();
		
		this.user = user;
	    this.transactionBuffer = new ArrayList<>();
		this.jobPostingsContainer = new VBox(); 
		this.applicationsListView= new ListView<>();
	}
	VBox profileInfoBox = new VBox();
	Button editProfileButton = new Button("Edit Profile");
	TextArea verified = new TextArea();
	 TextArea ViewApplicantsArea = new TextArea();
	 
	   TextField employeeIdField = new TextField();

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Employer Page");
		
		
    	Label profileLabel = new Label("Profile");
    	Label employeeLabel = new Label("Enter Employee ID to Verify their Qualification");
    	Label jobTitleLabel = new Label("Job Title:");
    	Label jobSkillsLabel = new Label("Job Required Skills:");
        Label welcomeLabel = new Label("Welcome to Employer Page!");
         welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight:"
   		+" bold; -fx-text-fill: white;");
      
        StackPane root = new StackPane();
        root.getChildren().add(welcomeLabel); 

        TextField jobTitleField = new TextField();
        TextField jobSkillsField = new TextField();
		textFlow = new TextFlow();
		
       // TextArea ViewApplicantsArea = new TextArea();
    	ImageView profileImage = new ImageView(new Image("/musa.jpg"));
        
    	Button postJobButton = new Button("Post Job");
    	
    	Button viewApplicantsButton = new Button("View Applicants");
    	
        
    	jobTitleLabel.setStyle("-fx-text-fill: white;");
    	jobSkillsLabel.setStyle("-fx-text-fill: white;");
         profileInfoBox.setStyle("#19376D");
        employeeLabel.setStyle("-fx-text-fill: white;");
     	 profileLabel.setStyle("-fx-text-fill: white;");
         postJobButton.setStyle("-fx-background-color: #576CBC; -fx-text-fill: #A5D7E8;");
     	 viewApplicantsButton.setStyle("-fx-background-color: #576CBC;"+"-fx-text-fill: #A5D7E8;");
         editProfileButton.setStyle("-fx-background-color: #576CBC; "
         		                  + "-fx-text-fill: #A5D7E8;");
         
		jobTitleField.setPromptText("Type in available Jobs (e.g): Sotfware Engineer" );
		jobSkillsField.setPromptText("Type in your Skill (e.g): Java " );

		 jobPostingsContainer = new VBox();
	     jobPostingsContainer.setPrefSize(250, 100);
	     // Add job postings container to a scrollable pane
	   ScrollPane scrollPane = new ScrollPane(jobPostingsContainer);
	   EmployeePage employeePage = new EmployeePage(user);
	
	   scrollPane.setFitToWidth(true);
       scrollPane.setPrefHeight(100);
       ViewApplicantsArea.setPrefHeight(130);
	   profileImage.setFitWidth(100);
	   profileImage.setFitHeight(100);
	   ////////////////////////////////////////////  
		postJobButton.setOnAction(e -> {
			
		    String jobTitle = jobTitleField.getText();
			String jobSkills = jobSkillsField.getText(); 
		    createJobPosting(jobTitle, jobSkills);
		 //   processRemainingTransactions();
		  //  if (blockchain!=null) {System.out.print(BlockchainHolder.blockchain);}
		});
        
        ////////////////////////////////////////////////
        editProfileButton.setOnAction(e -> showEditDialog());
      ////////////////////////////////////////////////
	viewApplicantsButton.setOnAction(e -> displayJobApplications());
	///////////////////////////////////////////////
		
			profileInfoBox.getChildren().clear();
			Label nameLabel = new Label("Name: " + user.getName());
			nameLabel.setStyle("-fx-text-fill: white;");
			Label usernameLabel = new Label("Username: " + user.getUsername());
			usernameLabel.setStyle("-fx-text-fill: white;");
			Label emailLabel = new Label("Email: " + user.getEmail());
			emailLabel.setStyle("-fx-text-fill: white;");
			Label roleLabel = new Label("Role: " + user.getRole());
			roleLabel.setStyle("-fx-text-fill: white;");
			Label provinceLabel = new Label("Province: " + user.getProvince());
			provinceLabel.setStyle("-fx-text-fill: white;");
			
			profileInfoBox.getChildren().addAll(nameLabel, emailLabel, 
					roleLabel, provinceLabel,editProfileButton);
	
		HBox profileBox = new HBox(profileImage, profileInfoBox);
		profileBox.setAlignment(Pos.TOP_RIGHT);
		profileBox.setSpacing(10);
////////////////////////////////////////////////
		// Create the hyperlink
		Hyperlink switchToEmployeePageLink = new Hyperlink("Switch to Employee Page\n"+
		"for testing purposes");
		switchToEmployeePageLink.setStyle( "-fx-text-fill: #A5D7E8;");
		switchToEmployeePageLink.setOnAction(e -> {
		
			try {
				employeePage.start(new Stage());} 
			catch (Exception ex) {
				ex.printStackTrace();}
		});
		
//////////////////////////////////////////////
	
		     
		        employeeIdField.setPromptText("e.g. 220016513");
		        Button verifyEmployeeQualification = new Button("Verify Qualification");
 verified = new TextArea();
verified.setDisable(false);
//verified.setPromptText(false);
		        verifyEmployeeQualification.setOnAction(e -> {
		            
		            
		        	verifyQualification();
		        });

		        ScrollPane scrollPan = new ScrollPane(textFlow);
		        scrollPan.setFitToWidth(true);
			    scrollPan.setPrefHeight(90); 
		//Add all to the vbox     
		vbox = new VBox(welcomeLabel,profileBox,jobTitleLabel,jobTitleField,jobSkillsLabel, jobSkillsField,
				        postJobButton, scrollPane,viewApplicantsButton, 
				        scrollPan,employeeLabel,employeeIdField,
				        verifyEmployeeQualification,verified);
		   vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
	    root = new StackPane(vbox);
	    root.setBackground(new Background(new BackgroundFill(Color.web("#19376D"), CornerRadii.EMPTY, Insets.EMPTY)));
		ScrollPane pane = new ScrollPane(root);
		pane.setFitToWidth(true);
	    Scene scene = new Scene(pane, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

////////////////////////////////////
	   // Method to verify qualifications in the blockchain
	public void verifyQualification() {
	    String enteredEmployeeId = employeeIdField.getText().trim();

	    // Clear the previous result
	    verified.setText("");

	    // Parse the blockchain's toString() output
	    String blockchainString = blockchain.toString();

	    // Define the regular expression pattern to extract the qualification data
	    Pattern pattern =  Pattern.compile("data=StudentID:([^,]+), Degree: ([^,]+), Institution: ([^,]+), Year: ([^,]+)");
	    Matcher matcher = pattern.matcher(blockchainString);

	    // Initialize a flag to check if a match is found
	    boolean isMatchFound = false;

	    // Iterate over all matches
	    while (matcher.find()) {
	        // Extract the qualification details
	        String studentId = matcher.group(1);
	        String qualificationName = matcher.group(2);
	        String institution = matcher.group(3);
	        String year = matcher.group(4);

	        // Check if the extracted student ID matches the entered employee ID
	        if (studentId.equals(enteredEmployeeId)) {
	            // Display the qualification details in the "verified" textarea
	            verified.appendText("Employee with ID: " + studentId + "\n");
	            verified.appendText("Has a Qualification in: " + qualificationName + "\n");
	            verified.appendText("From: " + institution + " Institution "+ "\n");
	            verified.appendText("Year: " + year + "\n\n");
	            isMatchFound = true;
	        }
	    }

	    // If no match is found, display a message
	    if (!isMatchFound) {
	        verified.setText("No qualification found for the entered employee ID.");
	    }
	}

	



///////////////////////////////////////////////
	
	    public List<String> extractJobApplications() {
	        List<String> jobApplications = new ArrayList<>();
	        String blockchainString = blockchain.toString();
	        Pattern pattern = Pattern.compile("Transaction\\{[^}]*data=JobApplication\\{([^}]*)\\}");
	        Matcher matcher = pattern.matcher(blockchainString);

	        while (matcher.find()) {
	            String applicationData = matcher.group(1).trim();
	            
	       
	            jobApplications.add(applicationData);
	        }

	        return jobApplications;
	    }

	 
	    public void displayJobApplications() {
	        // Clear previous content
	    	textFlow.getChildren().clear();

	        // Extract job applications
	        List<String> jobApplications = extractJobApplications();

	        for (String application : jobApplications) {
	            // Create a Text node for the employee profile
	            Text profileText = new Text(application + "\n");
	           

	          
	            // Add the Text nodes to the TextFlow
	            textFlow.getChildren().addAll(profileText);
	        }
	    }


	
	
	///////////////////////////////////////////////
	/**public boolean verifyEmployeeQualification(String studentId) {
	    String blockchainData = blockchain.toString();
	    Pattern blockPattern = Pattern.compile("\\[(.*?)\\]");
	    Matcher blockMatcher = blockPattern.matcher(blockchainData);

	    while (blockMatcher.find()) {
	        String transactionsData = blockMatcher.group(1); // Extract transactions data
	        List<Transaction<HiringService>> transactions = parseTransactionsFromData(transactionsData);

	        for (Transaction<HiringService> transaction : transactions) {
	            // Check if the transaction is not null before accessing its data
	            if (transaction != null) {
	                HiringService service = transaction.getData();
	                if (service instanceof Qualification) {
	                    Qualification qualification = (Qualification) service;
	                    if (qualification.getStudentIdentifier().equals(studentId)) {
	                        // Found a qualification for the student
	                        System.out.println("Qualification details: " + qualification);
	                        return true;
	                    }
	                }
	            }
	        }
	    }
	    // No qualification found for the student
	    System.out.println("Student with ID " + studentId + " does not exist.");
	    return false;
	}


    private List<Transaction<HiringService>> parseTransactionsFromData(String transactionsData) {
        List<Transaction<HiringService>> transactions = new ArrayList<>();
        // Assuming each transaction is separated by a comma and space
        String[] transactionStrings = transactionsData.split(",\\s*");
        for (String transactionString : transactionStrings) {
            Transaction<HiringService> transaction = parseTransactionFromString(transactionString);
            transactions.add(transaction);
        }
        return transactions;
    }

    private Transaction<HiringService> parseTransactionFromString(String transactionString) {
        // Assuming the transaction string format is "Transaction{sender='...', receiver='...', data=...}"
        Pattern transactionPattern = Pattern.compile("Transaction\\{sender='(.*?)', receiver='(.*?)', data=(.*?)\\}");
        Matcher transactionMatcher = transactionPattern.matcher(transactionString);
        if (transactionMatcher.find()) {
            String sender = transactionMatcher.group(1);
            String receiver = transactionMatcher.group(2);
            String dataString = transactionMatcher.group(3);

            // Check if the dataString is not null or empty
            if (dataString != null && !dataString.isEmpty()) {
                // Parse the dataString to create a Qualification object
                Qualification qualification = parseQualificationFromString(dataString);
                // Ensure qualification is not null before creating the Transaction
                if (qualification != null) {
                    return new Transaction<>(sender, receiver, qualification);
                }
            }
        }
        return null; // If the transaction string does not match the pattern or data is null
    }

    private Qualification parseQualificationFromString(String dataString) {
        // Assuming the dataString format is "Qualification{studentIdentifier='...', qualificationName='...', institution='...', graduateYear='...'}"
        Pattern qualificationPattern = Pattern.compile("Qualification\\{studentIdentifier='(.*?)', qualificationName='(.*?)', institution='(.*?)', graduateYear='(.*?)'\\}");
        Matcher qualificationMatcher = qualificationPattern.matcher(dataString);
        if (qualificationMatcher.find()) {
            String studentIdentifier = qualificationMatcher.group(1);
            String qualificationName = qualificationMatcher.group(2);
            String institution = qualificationMatcher.group(3);
            String graduateYear = qualificationMatcher.group(4);

            return new Qualification(studentIdentifier, qualificationName, institution, graduateYear);
        }
        return null; // If the qualification string does not match the pattern
    }

*/
	/////////////////////////////////////////////
	Blockchain<HiringService> blockchain=BlockchainHolder.blockchain;
	 public void createJobPosting(String jobTitle, String jobSkills) {
	        // Create a new Job object
	        Job job = new Job(jobTitle, jobSkills);

	        // Create a transaction with the job description
	        Transaction<HiringService> jobTransaction = new Transaction<>(employerID, "Job Board", job);

	        // Add the job transaction to the transaction buffer
	        transactionBuffer.add(jobTransaction);

	        if (transactionBuffer.size() >= MAX_TRANSACTIONS_PER_BLOCK) {
	            // Create a new list of transactions from the transaction buffer to add to the blockchain
	            List<Transaction<HiringService>> transactionsToAdd = new ArrayList<>(transactionBuffer);

	            // Create a new block with the transactions
	           Block<HiringService> block = new Block<>(lastBlockHash, transactionsToAdd);
	            
	            // Add the block to the blockchain
	            blockchain.addBlock(transactionsToAdd);


	            // Update the last block's hash with the new block's hash
	            lastBlockHash = block.getHash();

	            // Clear the buffer for the next block
	            transactionBuffer.clear();
	        }

	        // Update the UI with the new job posting
	        updateJobPostingsUI(jobTransaction); 
	    }
///////////////////////////////////////////
/**	public void processRemainingTransactions() {
	    if (!transactionBuffer.isEmpty()) {
	        // Create a new block with the remaining transactions
	        Block<String> block = new Block<>(lastBlockHash, new ArrayList<>(transactionBuffer));
	        // Add the block to the blockchain
	        blockchain.addBlock(transactionBuffer);
	        // Update the last block's hash with the new block's hash
	        lastBlockHash = block.getHash();
	        // Clear the buffer for the next block
	        transactionBuffer.clear();
	    }
	}*/

	//////////////////////////////////////////////
	public void updateJobPostingsUI(Transaction<HiringService> jobTransaction) {
	    // Create a new TextArea with the job description from the transaction
	    TextArea jobPostingText = new TextArea(jobTransaction.getData().toString());
	    jobPostingText.setEditable(false); // Make the text area read-only
	    jobPostingText.setWrapText(true); // Enable text wrapping
	    jobPostingText.setStyle("-fx-font-size: 13px; -fx-padding: 10;");

	    // Add the TextArea to the jobPostingsContainer
	    jobPostingsContainer.getChildren().add(jobPostingText);
	}
	//////////////////////////////////////////////////////////////////////
	private void showEditDialog() {
	    Dialog<User> editDialog = new Dialog<>();
	    editDialog.setTitle("Edit Profile");
	    editDialog.setHeaderText("Update your profile information");

	    DialogPane dialogPane = editDialog.getDialogPane();
	    dialogPane.setStyle("-fx-background-color:#19376D");

	    // Initialize labels
	    Label nameLabel = new Label("Name: " );
	    Label usernameLabel = new Label("Username:");
	    Label emailLabel = new Label("Email: " );
	    Label provinceLabel = new Label("Province: " );
	 
        nameLabel.setStyle("-fx-text-fill: white;"); // Sets the text color to white
        usernameLabel.setStyle("-fx-text-fill: white;");
  
        emailLabel.setStyle("-fx-text-fill: white;");

       provinceLabel.setStyle("-fx-text-fill: white;");
	    // Create input fields for editing
	    TextField nameField = new TextField(user.getName());
	    TextField usernameField = new TextField(user.getUsername());
	    TextField emailField = new TextField(user.getEmail());
	    TextField provinceField = new TextField(user.getProvince());

	    // Add input fields and labels to the dialog
	    VBox content = new VBox(10); // 10 is the spacing between elements
	    content.getChildren().addAll(
	        nameLabel, nameField,
	        usernameLabel,usernameField,
	        emailLabel, emailField,
	        provinceLabel, provinceField
	    );
	    editDialog.getDialogPane().setContent(content);

	    // Set dialog buttons
	    ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
	    editDialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

	    // Handle button actions
	    editDialog.setResultConverter(dialogButton -> {
	        if (dialogButton == saveButtonType) {
	            user.setName(nameField.getText());
	            user.setUsername(usernameField.getText());
	            user.setEmail(emailField.getText());
	            user.setProvince(provinceField.getText());

	            // Update corresponding labels with the new values
	            nameLabel.setText("Name: " + user.getName());
	            usernameLabel.setText("Username: " + user.getUsername());
	            emailLabel.setText("Email: " + user.getEmail());
	            provinceLabel.setText("Province: " + user.getProvince());
	            
	            // Refresh the profileInfoBox to show updated labels
	            profileInfoBox.getChildren().setAll(nameLabel,usernameLabel, emailLabel, provinceLabel, editProfileButton);
	        }
	        return null;
	    });

	    editDialog.showAndWait();
	}


	///////////////////////////////////////////////////////////////////
	 
	
}