package pkgfinal2.login;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgfinal2.MainScreen;


public class LoginWindow {
    private boolean success=false;
    
    
    public void display(){
        
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


        ChoiceBox cb = new ChoiceBox();
        cb.setItems(FXCollections.observableArrayList("Spanish","French","English"));

        //sets up the controls
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
        
        //functionality of the login button
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e->{
            try{
                //validates the login
                success = checkValidLogin(txtUsername.getText(), passPassword.getText());
                //if login is successful, do stuff
                // need to add the localization stuff for the alert box.
                // throws exception if the login fails.
                if(success){
                    MainScreen.setAuthUser(txtUsername.getText());
                    window.close();
                }
                else{
                    throw new LoginException();
                }
            }
            catch(LoginException h){
                Alert alt = new Alert(Alert.AlertType.ERROR);
                alt.setHeaderText("LOGIN FAILURE");
                alt.setContentText("Username and/or password are incorrect.\nPlease try again");
                alt.showAndWait();
                txtUsername.setText("");
                txtUsername.requestFocus();
                passPassword.setText("");                
            }            
        });
        layout.add(btnLogin, 1, 3);
        layout.add(cb,1,4);
        Scene scene = new Scene(layout,300,250);
        Platform.runLater(() ->{
            btnLogin.requestFocus();
        });
        window.setScene(scene);
        window.showAndWait();
    }
    
    public static boolean checkValidLogin(String username, String passwd){
        // access the database and verify username and password...
        if(username.equals("ecogle")){
                LogFile.write(username,LogEvents.LOGIN);
                return true;
            }
            else
            {
                LogFile.write(username,LogEvents.LOGINFAIL);
                return false;
            }            
    }
}
