import java.security.KeyPair;
import java.security.KeyPairGenerator;


import acsse.csc03a3.Blockchain;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	


    public static void main(String[] args) {
        launch(args); 
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new Registration().start(primaryStage); // This will display the registration form
        // Generate a key pair for testing
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Create a new user
      
		User user = new User("testName", "testUser", "testEmail", "testRole", "testProvince", "testPassword", keyPair);
        Blockchain<String> blockchain=BlockchainHolder.blockchain;
 

        // Register a stake for the user
        String userAddress = user.getKeyPair().getPublic().toString();
      blockchain.registerStake(userAddress, 1000); // Register a stake of 1000 for the user

System.out.println(blockchain);

       
    }
    

   
    }
    

