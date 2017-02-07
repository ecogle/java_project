/*
 * Login interface for the program.
 * Functionality not completed.
 */
package pkgfinal2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pkgfinal2.customer.*;
import pkgfinal2.login.LoginWindow;
import pkgfinal2.user.UserList;

/**
 * Main entry-point for the application. Login screen will pop-up as soon as main
 * screen loads. Nothing will be visible until the user authenticates. Once the
 * user authenticates, the authenticated username will be displayed discretely 
 * on the screen somewhere and then the main screen will be populated with the 
 * controls.
 * 
 * 
 * @author ecogle
 */
public class MainScreen extends Application {
    Stage window;
    private static String authUserString=null;
    boolean loginSuccedded = false;
    
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("TESTING MAIN PAGE");
        //window.setMaximized(true);
        Label lblAuthUserLabel = new Label(MainScreen.getAuthUser());
        GridPane layout = new GridPane();
        //layout.setGridLinesVisible(true);
        layout.setHgap(8);
        layout.setVgap(10);
        layout.setAlignment(Pos.CENTER);
        Button btnConfirm = new Button("Confirm please...");
//        btnConfirm.setOnAction( e ->{
//            ConfirmWindow.display("Are you sure? ");
//        });
        btnConfirm.setOnAction( e ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("This is a pre-packaged Information alert");
            alert.setHeaderText("INFORMATIONAL DIALOG");
            alert.setContentText("This is the information to deliver to the client");
            alert.showAndWait();
        });
        
        //Login screen
        /*
        
        */
        Button btnLogin = new Button("Show login");
        btnLogin.setOnAction(e-> {
            LoginWindow liwin = new LoginWindow(); //instantiates the login window
            liwin.display(); //displays the login window
            if(MainScreen.getAuthUser()!= null){
                //displays the username if authenticated properly
                lblAuthUserLabel.setText(MainScreen.getAuthUser());
            }
            
        });

        //user list
        Button btnUserList = new Button("User List");
        btnUserList.setOnAction(event -> {
            new UserList().display();
        });

        //Add Country
        Button btnAddCountry = new Button("Add country");
        btnAddCountry.setOnAction(e->{
            new AddCountry().display();
        });

        //Add City
        Button btnAddCity = new Button("Add city");
        btnAddCity.setOnAction(e->{
            new AddCity().display();
        });

        //add address
        Button btnAddAddress = new Button("Add address");
        btnAddAddress.setOnAction(e->{
            new AddAddress().display();
        });

        //add Customer
        Button btnAddCustomer = new Button("Add Customer");
        btnAddCustomer.setOnAction(event -> {
            new AddCustomer().display();
        });
        
        

        VBox vbox1 = new VBox();
        vbox1.setSpacing(8);
        VBox vbox2 = new VBox();
        vbox2.setSpacing(8);
        vbox1.getChildren().add(btnConfirm);
        vbox1.getChildren().add(btnLogin);
        vbox1.getChildren().add(btnAddCity);
        vbox1.getChildren().add(btnAddCustomer);
        vbox2.getChildren().add(btnUserList);
        vbox2.getChildren().add(btnAddCountry);
        vbox2.getChildren().add(btnAddAddress);

        layout.add(vbox1,0,1);
        layout.add(vbox2,1,1);
        layout.add(lblAuthUserLabel, 4, 0);
        Scene scene = new Scene(layout,300,250);
        
        
        window.setScene(scene);
        window.show();
        
    }
    
    //making it immutable
//    public static Map<String,String> getCredentials(){
//        Map<String,String> temp = new TreeMap<>();
//        temp = credentials;
//        return temp;
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public static void setAuthUser(String user){
        authUserString = user;
    }
    
    public static String getAuthUser(){
        return authUserString;
    }
    
}
