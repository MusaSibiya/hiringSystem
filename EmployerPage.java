import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;

public class EmployerPage extends Application {

	/**
	 * initialising lastBlockHash to 0 and use it as 
	 * my Genesis since we dont have getGenesis
	 * @see lastBlockHash 
	 */
    private String lastBlockHash = "0"; 
	private String employerAddress;
	private User user;
	private VBox vbox;
	private Label nameLabel;
	private Label emailLabel;
	private Label provinceLabel;
	private VBox jobPostingsContainer;
	List<Transaction<String>> transactionBuffer;
    private final int MAX_TRANSACTIONS_PER_BLOCK = 3; 

	public EmployerPage(String employeeAddress, User user) {
		this.employerAddress = employeeAddress;
		this.user = user;
	    this.transactionBuffer = new ArrayList<>();
		this.jobPostingsContainer = new VBox(); // 
	}
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Employer Page");
		
		
    	Label profileLabel = new Label("Profile");
    	Label jobTitleLabel = new Label("Job Title:");
    	Label jobSkillsLabel = new Label("Job Required Skills:");
        Label welcomeLabel = new Label("Welcome to Employer Page!");
         welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight:"
   		+" bold; -fx-text-fill: white;");
      
        StackPane root = new StackPane();
        root.getChildren().add(welcomeLabel); 

        TextField jobTitleField = new TextField();
        TextField jobSkillsField = new TextField();
        TextArea blockchainArea = new TextArea();
    	ImageView profileImage = new ImageView(new Image("/musa.jpg"));
        
    	Button postJobButton = new Button("Post Job");
    	Button editProfileButton = new Button("Edit Profile");
    	Button viewBlockchainButton = new Button("View Applicants");
    	Hyperlink profileLink = new Hyperlink("", new VBox(profileImage, profileLabel));
    	VBox profileInfoBox = new VBox();
        
    	jobTitleLabel.setStyle("-fx-text-fill: white;");
    	jobSkillsLabel.setStyle("-fx-text-fill: white;");
         profileInfoBox.setStyle("#19376D");
         profileLink.setStyle("-fx-text-fill: white;");
     	 profileLabel.setStyle("-fx-text-fill: white;");
         postJobButton.setStyle("-fx-background-color: #576CBC; -fx-text-fill: #A5D7E8;");
     	 viewBlockchainButton.setStyle("-fx-background-color: #576CBC;"+"-fx-text-fill: #A5D7E8;");
         editProfileButton.setStyle("-fx-background-color: #576CBC; "
         		                  + "-fx-text-fill: #A5D7E8;");
         
		jobTitleField.setPromptText("Type in available Jobs (example):  Title: Sotfware Engineer" );
		jobSkillsField.setPromptText("Type in available Jobs (example):  Title: Sotfware Engineer" );

		 jobPostingsContainer = new VBox();
	     jobPostingsContainer.setPrefSize(250, 100);
	     // Add job postings container to a scrollable pane
	   ScrollPane scrollPane = new ScrollPane(jobPostingsContainer);
	   EmployeePage employeePage = new EmployeePage(employerAddress, user);
	
	   scrollPane.setFitToWidth(true);
       scrollPane.setPrefHeight(100);
       blockchainArea.setPrefHeight(130);
	   profileImage.setFitWidth(100);
	   profileImage.setFitHeight(100);
	   ////////////////////////////////////////////  
		postJobButton.setOnAction(e -> {
			
		    String jobTitle = jobTitleField.getText();
			String jobSkills = jobSkillsField.getText(); 
		    createJobPosting(jobTitle, jobSkills);
		  //  if (blockchain!=null) {System.out.print(BlockchainHolder.blockchain);}
		});
        
        ////////////////////////////////////////////////
        editProfileButton.setOnAction(e -> showEditDialog());
      ////////////////////////////////////////////////
	//viewBlockchainButton.setOnAction(e -> {});
	///////////////////////////////////////////////
		profileLink.setOnAction(e -> {
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
					roleLabel, provinceLabel,editProfileButton);
	
		});
		HBox profileBox = new HBox(profileLink, profileInfoBox);
		profileBox.setAlignment(Pos.TOP_RIGHT);
		
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
		// Initialize the VBox
		vbox = new VBox(); // Initialize the VBox
		vbox.getChildren().add(switchToEmployeePageLink);
		HBox hSwitch = new HBox(switchToEmployeePageLink);
		     hSwitch.setAlignment(Pos.BOTTOM_RIGHT);
		     
		//Add all to the vbox     
		vbox = new VBox(welcomeLabel,profileBox,jobTitleLabel,jobTitleField,jobSkillsLabel, jobSkillsField,
				        postJobButton, scrollPane,viewBlockchainButton, 
				        blockchainArea,hSwitch);
		
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
	    root = new StackPane(vbox);
	    root.setBackground(new Background(new BackgroundFill(Color.web("#19376D"), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	

	///////////////////////////////////////////////
	//edit profile information

	/////////////////////////////////////////////
Blockchain<String> blockchain=BlockchainHolder.blockchain;

	public void createJobPosting(String jobTitle,String jobSkills) {
		 // Combine job title and skills into one job description string
	    String jobDescription = jobTitle + ", Skills Required: " + jobSkills;
	    // Create a transaction with the job description
	    Transaction<String> jobTransaction = new Transaction<>(employerAddress, "Job Board", jobDescription);
	    // Add the job transaction to the transaction buffer
	    transactionBuffer.add(jobTransaction);
	    if (transactionBuffer.size() >= MAX_TRANSACTIONS_PER_BLOCK) {
	        // Create a new list of transactions from the transaction buffer to add to the blockchain
	        List<Transaction<String>> transactionsToAdd = new ArrayList<>(transactionBuffer);
	        // Create a new block with the last block's hash and the transactions
	     
	        Block<String> block = new Block<>(lastBlockHash, transactionsToAdd);
	        // add the block to the blockchain
	        blockchain.addBlock(transactionsToAdd);
	        // Convert the block to a string and log it for simulation
	        String blockString = block.toString();
	        System.out.println(blockString);
	        // Update the last block's hash with the new block's hash
	        lastBlockHash = block.getHash();
	        // Clear the buffer for the next block
	        transactionBuffer.clear();
	    }

	    // Update the UI with the new job posting
	    updateJobPostingsUI(jobTransaction); 
	}

	//////////////////////////////////////////////
	public void updateJobPostingsUI(Transaction<String> jobTransaction) {
	    // Create a new TextArea with the job description from the transaction
	    TextArea jobPostingText = new TextArea(jobTransaction.getData());
	    jobPostingText.setEditable(false); // Make the text area read-only
	    jobPostingText.setWrapText(true); // Enable text wrapping
	    jobPostingText.setStyle("-fx-font-size: 14px; -fx-padding: 10;");

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
        // Create input fields for editing
        TextField nameField = new TextField(user.getName());
        TextField emailField = new TextField(user.getEmail());
       // TextField roleField = new TextField(user.getRole());
        TextField provinceField = new TextField(user.getProvince());
      ;

        // Add input fields to the dialog
        editDialog.getDialogPane().setContent(new VBox(
        		  new Label("Name:"), nameField,
	                new Label("Email:"), emailField,
	               // new Label("Role:"), roleField,
	                new Label("Province:"), provinceField
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
	
	///////////////////////////////////////////////////////////////////
	 
	public static void main(String[] args) {
		launch(args);
	}
}