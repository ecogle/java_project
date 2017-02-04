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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ecogle
 */
public class MainScreen extends Application {
    Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("TESTING MAIN PAGE");
        //window.setMaximized(true);
        GridPane layout = new GridPane();
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
        Button btnLogin = new Button("Show login");
        btnLogin.setOnAction(e-> {
            new LoginWindow().show();
        });

        //user list
        Button btnUserList = new Button("User List");
        btnUserList.setOnAction(event -> {
            new UserList().display();
        });
        
        layout.add(btnConfirm,0,0);
        layout.add(btnLogin,1,0);
        layout.add(btnUserList,2,0);
        
        Scene scene = new Scene(layout,300,250);
        
        
        window.setScene(scene);
        window.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
