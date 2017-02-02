/*
 * Login interface for the program.
 * Functionality not completed.
 */
package pkgfinal2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ecogle
 */
public class JavaFinal extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GlobalDyn Login:");
        
        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.CENTER);
        myGrid.setHgap(15);
        myGrid.setVgap(15);
        myGrid.setPadding(new Insets(25));
        
        
        Scene myScene = new Scene(myGrid,300,275);
        
        Text sceneTitle = new Text("Welcome to GlobalDyn...");
        sceneTitle.setFont(Font.font("Tahoma",FontWeight.NORMAL, 15));
        myGrid.add(sceneTitle, 0,0,2,1);
        
        Label userName = new Label("Username: ");
        myGrid.add(userName, 0, 1);
        
        TextField userTextField = new TextField();
        myGrid.add(userTextField, 1, 1);
        
        Label pwd = new Label("Password: ");
        myGrid.add(pwd, 0, 2);
        
        PasswordField userPassword = new PasswordField();
        myGrid.add(userPassword, 1, 2);
        
        Button loginButton = new Button("Login");
        HBox hboxButton = new HBox(10);
        hboxButton.setAlignment(Pos.BOTTOM_RIGHT);
        hboxButton.getChildren().add(loginButton);
        myGrid.add(hboxButton, 1, 4);
        
        final Text actionTarget = new Text();
        myGrid.add(actionTarget, 1, 6);
        
        /**
         * This is the event listener for the login button
         * Add functionality to:
         * 
         *  1) Check for credentials
         *  2) Use properties file for localization and translation
         *      of the login success and error based on location
         *      [Phoenix, Arizona; New York, New York; London, England]
         *  3) Determine location based on time-zone information
         */
        loginButton.setOnAction((ActionEvent event) -> {
            System.out.println("Hello there my friend");
            actionTarget.setFill(Color.FIREBRICK);
            actionTarget.setText("Login button pressed");
        });
        
        
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
