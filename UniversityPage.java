import java.util.List;

import acsse.csc03a3.Blockchain;
import acsse.csc03a3.Transaction;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class UniversityPage extends StackPane {

    private Blockchain<HiringService> blockchain;
 
    private VBox profileInfoBox;
    private User user; // Assuming User is a class that holds user information

    public UniversityPage(Blockchain<HiringService> blockchain, User user) {
        this.blockchain = blockchain;
      
        this.user = user;
        this.profileInfoBox = new VBox();
    }
    

    public void start(Stage primaryStage) {
        // Profile Information
    	Label welcomeLabel = new Label("Welcome to Institution Page, Please Add Qualified Students");
    	 welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight:"	+ " bold; -fx-text-fill: white;");
        ImageView universityLogo = new ImageView(new Image("/uj.jpeg"));
        universityLogo.setFitWidth(200);
        universityLogo.setFitHeight(130);

        Label nameLabel = new Label("University: " + user.getName());
        nameLabel.setStyle("-fx-text-fill: white;");
        profileInfoBox.getChildren().addAll(universityLogo, nameLabel);
profileInfoBox.setAlignment(Pos.TOP_CENTER);
        // Qualifications Form
        Label studentIdLabel = new Label("Student ID:");
        studentIdLabel.setStyle("-fx-text-fill: white;");
        TextField studentIdField = new TextField();
        studentIdField.setPrefWidth(380);
        HBox studentIdBox = new HBox(82, studentIdLabel, studentIdField);
        studentIdBox.setAlignment(Pos.CENTER_LEFT);

        Label degreeLabel = new Label("Degree:");
        degreeLabel.setStyle("-fx-text-fill: white;");
        TextField degreeField = new TextField();
        degreeField.setPrefWidth(380);
        HBox degreeBox = new HBox(100, degreeLabel, degreeField);
        degreeBox.setAlignment(Pos.CENTER_LEFT);

        Label fieldOfStudyLabel = new Label("Institution:");
        fieldOfStudyLabel.setStyle("-fx-text-fill: white;");
        TextField institutionField = new TextField();
        institutionField.setPrefWidth(380);;
        HBox fieldOfStudyBox = new HBox(85, fieldOfStudyLabel, institutionField);
        fieldOfStudyBox.setAlignment(Pos.CENTER_LEFT);

        Label dateOfIssuanceLabel = new Label("Date of Issuance:");
        dateOfIssuanceLabel.setStyle("-fx-text-fill: white;");
        TextField dateOfIssuanceField = new TextField();
        dateOfIssuanceField.setPrefWidth(380);
        HBox dateOfIssuanceBox = new HBox(50, dateOfIssuanceLabel, dateOfIssuanceField);
        dateOfIssuanceBox.setAlignment(Pos.CENTER_LEFT);

        Button addQualificationButton = new Button("Add Qualification");
        addQualificationButton.setStyle("-fx-background-color: #576CBC; -fx-text-fill: #A5D7E8;");
        addQualificationButton.setOnAction(e -> {
            String studentId = studentIdField.getText();
            String qualificationName = degreeField.getText();
            String institution = institutionField.getText();
            String graduateYear = dateOfIssuanceField.getText();

            // Add the qualification to the blockchain
            addQualification(studentId, qualificationName, institution, graduateYear);

            // Optionally, display a success message or update UI as needed
            System.out.println("Qualification added to the blockchain!");
        });

     
        // Layout
        VBox formContainer = new VBox(10, studentIdBox, degreeBox, fieldOfStudyBox, dateOfIssuanceBox, addQualificationButton);
        formContainer.setAlignment(Pos.CENTER);
        formContainer.setPadding(new Insets(10));

        VBox mainContainer = new VBox(10,welcomeLabel, profileInfoBox, formContainer);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(10));
        mainContainer.setBackground(new Background(new BackgroundFill(Color.web("#19376D"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().add(mainContainer);

        Scene scene = new Scene(this, 600, 600);
        primaryStage.setTitle("Institution");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public void addQualification(String studentId, String qualificationName, String institution, String graduateYear) {
        Qualification qualification = new Qualification(studentId, qualificationName, institution, graduateYear);
        Transaction<HiringService> qualificationTransaction = new Transaction<>(user.getName(), "Qualification Board", qualification);
        blockchain.addBlock(List.of(qualificationTransaction));
        
    System.out.println(blockchain);
    }


    // Additional methods .
}
