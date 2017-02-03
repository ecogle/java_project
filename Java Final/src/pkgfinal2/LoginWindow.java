package pkgfinal2;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Locale;

public class LoginWindow {
    
    public void show(){
        Stage window = new Stage();
        window.setTitle("Login...");
        window.initModality(Modality.APPLICATION_MODAL);
        
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(8);
        layout.setHgap(10);
        Label message = new Label("Please login... ");
        Font fMessage = new Font("Verdana", 13);
        message.setPadding(new Insets(0,0,30,0));
        message.setFont(fMessage);
        layout.add(message, 0, 0,2,1);
        
        
        
        Label lblUsername = new Label("Username: ");
        layout.add(lblUsername, 0, 1);
        
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("username");
        layout.add(txtUsername, 1, 1);
        
        Label lblPassword = new Label("Password:");
        layout.add(lblPassword, 0, 2);
        
        PasswordField passPassword = new PasswordField();
        passPassword.setPromptText("password");
        layout.add(passPassword, 1, 2);
        
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e->{
            // verify username and password and login to the screen
            // if login is successful, log  timestamp to file.
            if(txtUsername.getText().equals("ecogle")){
                LogFile.write(txtUsername.getText(),LogEvents.LOGIN);
            }
            else
            {
                LogFile.write(txtUsername.getText(),LogEvents.LOGINFAIL);
            }
            
            // use localization here
            
            
            // track timestamps for user log-in data in a .txt file
            // each record should be appeneded to the log file.
            window.close();
            
        });
        
        
        layout.add(btnLogin, 1, 3);
        
        Scene scene = new Scene(layout,300,250);
        Platform.runLater(() ->{
            btnLogin.requestFocus();
        });
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
}
