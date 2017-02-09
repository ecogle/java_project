/*
 * Login interface for the program.
 * Functionality not completed.
 */
package pkgfinal2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pkgfinal2.customer.*;
import pkgfinal2.login.LoginWindow;
import pkgfinal2.user.UserList;
import sun.util.resources.cldr.om.CurrencyNames_om;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Button btnLogin = new Button("Show login");



    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("TESTING MAIN PAGE");
        //window.setMaximized(true);
        Label lblAuthUserLabel = new Label(MainScreen.getAuthUser());

        // todo Need to create a new class for properties of the ENTIRE customer in order to display it in the list.


        BorderPane layout = new BorderPane();
        layout.setPrefSize(700,600);
        TableView tvCustomer = new TableView();
        TableColumn colCustName = new TableColumn("Name");
        TableColumn colAddress = new TableColumn("Address");
        TableColumn colCity = new TableColumn("City");
        TableColumn colCountry = new TableColumn("Country");
        TableColumn colActive = new TableColumn("Active");
        tvCustomer.getColumns().addAll(colCustName,colAddress,colCity,colCountry,colActive);

        colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        ObservableList<Customer> custList = FXCollections.observableArrayList();

        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = stmnt.executeQuery("Select * from customer");
            while(rs.next()){
                Customer c = new Customer();
                c.setCustomerName(rs.getString("customerName"));
                custList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        VBox vbxLeft = new VBox();
        vbxLeft.getChildren().addAll(btnLogin);
        layout.setLeft(vbxLeft);
        layout.setCenter(tvCustomer);

        tvCustomer.setItems(custList);

        window.setOnShown(event -> {

        });
        //add Customer
        Button btnAddCustomer = new Button("Add Customer");
        btnAddCustomer.setVisible(false);
        btnAddCustomer.setOnAction(event -> {
            new AddCustomer().display();
        });

        btnLogin.setOnAction(e-> {
            LoginWindow liwin = new LoginWindow(); //instantiates the login window
            liwin.display(); //displays the login window
            if(MainScreen.getAuthUser()!= null){
                //displays the username if authenticated properly
                lblAuthUserLabel.setText(MainScreen.getAuthUser());
                btnAddCustomer.setVisible(true);
            }

        });




        Scene scene = new Scene(layout,700,600);
        
        
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
