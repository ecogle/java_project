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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pkgfinal2.customer.*;
import pkgfinal2.login.LoginWindow;
import pkgfinal2.user.UserList;

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
        Button btnLogin = new Button("Show login");
        btnLogin.setOnAction(e-> {
            new LoginWindow().display();
        });

        //user list
        Button btnUserList = new Button("User List");
        btnUserList.setOnAction(event -> {
            new UserList().display();
        });

        //Add Customer
        Button btnAddCountry = new Button("Add country");
        btnAddCountry.setOnAction(e->{
            new AddCountry().display();
        });

        //Add Customer
        Button btnAddCity = new Button("Add city");
        btnAddCity.setOnAction(e->{
            new AddCity().display();
        });

        //add address
        Button btnAddAddress = new Button("Add address");
        btnAddAddress.setOnAction(e->{
            new AddAddress().display();
        });

        VBox vbox1 = new VBox();
        vbox1.setSpacing(8);
        VBox vbox2 = new VBox();
        vbox2.setSpacing(8);
        vbox1.getChildren().add(btnConfirm);
        vbox1.getChildren().add(btnLogin);
        vbox1.getChildren().add(btnAddCity);
        vbox2.getChildren().add(btnUserList);
        vbox2.getChildren().add(btnAddCountry);
        vbox2.getChildren().addAll(btnAddAddress);

        layout.add(vbox1,0,0);
        layout.add(vbox2,1,0);
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
