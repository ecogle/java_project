package pkgfinal2.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import pkgfinal2.appointments.AppointmentController;


public class LoginWindow {
    private boolean success=false;
    private String location;
    private String language;
    private Locale locale;

    private Map<String,ZoneId> zoneIdMap;

    public void display(){
        //todo fix the login to set defaults for the location and language
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

        HBox hbxLang = new HBox();
        
        ChoiceBox cbLanguage = new ChoiceBox();
        ChoiceBox cbLocation = new ChoiceBox();
        
        Label lblLanguage = new Label("Language");
        lblLanguage.setFont(Font.font("Arial",10));
        Label lblLocation = new Label("Location");
        lblLocation.setFont(Font.font("Arial",10));
        hbxLang.getChildren().addAll(lblLanguage,cbLanguage,lblLocation,cbLocation);
        cbLanguage.setItems(FXCollections.observableArrayList("Spanish","French","English"));
        cbLanguage.setStyle("-fx-font:8pt \"san-serif\"");
        cbLanguage.getSelectionModel().select(2); //sets the default value


        
        cbLocation.setItems(FXCollections.observableArrayList("New York","Phoenix","London"));
        cbLocation.getSelectionModel().select(0);
        cbLocation.setStyle("-fx-font:8pt \"san-serif\"");
        cbLocation.setOnAction(event -> {
            Alert alt = new Alert(Alert.AlertType.INFORMATION);
            String s = cbLocation.getSelectionModel().getSelectedItem().toString();
            alt.setContentText(s + " " + getTimeZone(s));
            alt.showAndWait();
        });

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
        hbxLang.setSpacing(10);
        hbxLang.setPadding(new Insets(10,3,3,3));
        layout.add(hbxLang, 0, 4,2,1);
        //functionality of the login button
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e->{
            ResourceBundle rb = null;
            try{
                //validates the login
                success = checkValidLogin(txtUsername.getText(), passPassword.getText());
                language = (String) cbLanguage.getValue();
                location = (String) cbLocation.getValue();
                if(success){                    
                    MainScreen.setAuthUser(txtUsername.getText());                    
                    if(language != null){                         
                        rb = ResourceBundle.getBundle("login",getLanguage(language));
                        Alert alrt = new Alert(Alert.AlertType.INFORMATION,rb.getString("loginSuccess"));
                        alrt.showAndWait();
                    }
                    window.close();
                }
                else{                    
                    throw new LoginException();
                }
            }
            catch(LoginException h){
                rb = ResourceBundle.getBundle("login",getLanguage(language));
                Alert alrt = new Alert(Alert.AlertType.INFORMATION,rb.getString("loginFailed"));
                alrt.showAndWait();
                txtUsername.setText("");
                txtUsername.requestFocus();
                passPassword.setText("");                
            }            
        });
        
     
        layout.add(btnLogin, 1, 3);
        
        Scene scene = new Scene(layout,300,250);
        Platform.runLater(() ->{
            txtUsername.requestFocus();
        });
        window.setScene(scene);
        window.showAndWait();
    }

    // todo break out into a controller

    public static boolean checkValidLogin(String username, String passwd){
        String sql = "Select * from user where userName = '" + username.trim() + "' and password = '" + passwd.trim()+"';";        
        try(Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();){            
            ResultSet rs = stmnt.executeQuery(sql);
            if(rs.next()){
                LogFile.write(username,LogEvents.LOGIN);
                return true;
            }
        }
        catch(SQLException s){
            s.getMessage();
        }
        LogFile.write(username,LogEvents.LOGINFAIL);
        return false;
    }
    
    private static Locale getLanguage(String s){
        Locale loc=null;
        switch (s){
            case "Spanish":
                loc = new Locale("es","US");
                break;
            case "English":
                loc = new Locale("en","US");
                break;
            case "French":
                loc = new Locale("fr","FR");
                break;
        }
        return loc;
    }

    private static ZoneId getTimeZone(String location){
        ZoneId zoneId;
        switch (location){
            case "Phoenix":  zoneId = ZoneId.of("US/Mountain");
                break;
            case "New York": zoneId = ZoneId.of("US/Eastern");
                break;
            case "London": zoneId = ZoneId.of("GMT");
                break;
            default: zoneId=ZoneId.systemDefault();
                break;
        }
        return zoneId;
    }
    
    
}
