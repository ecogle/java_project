/*
 * Login interface for the program.
 * Functionality not completed.
 */
package pkgfinal2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pkgfinal2.customer.*;
import pkgfinal2.login.LoginWindow;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.layout.HBox;


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
        int intButtonWidth = 120;
        window = primaryStage;
        window.setTitle("TESTING MAIN PAGE");
        //window.setMaximized(true);
        Label lblAuthUserLabel = new Label(MainScreen.getAuthUser());
        Button btnAddCustomer = new Button("Add Customer");
        Button btnEditCustomer = new Button("Edit Customer");
        Button btnDeleteCustomer = new Button("Delete Customer");
        Button btnRefreshList = new Button("Refresh list");
        btnLogin.setPrefWidth(intButtonWidth);
        btnAddCustomer.setPrefWidth(intButtonWidth);
        btnDeleteCustomer.setPrefWidth(intButtonWidth);
        btnEditCustomer.setPrefWidth(intButtonWidth);



        BorderPane layout = new BorderPane();
        layout.setPrefSize(700,600);
        TableView tvCustomer = new TableView();
        TableColumn colCustName = new TableColumn("Name");
        TableColumn colAddress = new TableColumn("Address");
        TableColumn colAddress2 = new TableColumn("Address2");
        TableColumn colCity = new TableColumn("City");
        TableColumn colCountry = new TableColumn("Country");
        TableColumn colActive = new TableColumn("Active");
        tvCustomer.getColumns().addAll(colCustName,colAddress,colAddress2,colCity,colCountry,colActive);

        colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress2.setCellValueFactory(new PropertyValueFactory<>("address2"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        VBox vbxLeft = new VBox();
        vbxLeft.setPadding(new Insets(30,10,10,15));
        vbxLeft.setSpacing(10);
        vbxLeft.getChildren().addAll(btnLogin,btnAddCustomer,btnEditCustomer,btnDeleteCustomer);
        layout.setLeft(vbxLeft);
        layout.setCenter(tvCustomer);
        
        MenuBar mnuMenuBar = new MenuBar();
        mnuMenuBar.prefWidthProperty().bind(window.widthProperty());
        Menu fileMenu = new Menu("File");
        MenuItem mnuFile = new MenuItem("Open record");
        Menu editMenu = new Menu("Edit");
        Menu logMenu = new Menu("Users");
        
        // add functionality to disable login logoff based on login status
        // try to add functionality to display the username in the menu area.
        MenuItem mnuLogin = new MenuItem("Login");
        MenuItem mnuLogoff = new MenuItem("Logoff");
        
        MenuItem mnuEditCity = new MenuItem("Edit City...");
        MenuItem mnuEditCounty = new MenuItem("Edit Country...");
        HBox mnuHbox = new HBox();
        
        editMenu.getItems().addAll(mnuEditCity,mnuEditCounty);
        fileMenu.getItems().add(mnuFile);
        logMenu.getItems().addAll(mnuLogin,mnuLogoff);
        mnuMenuBar.getMenus().addAll(fileMenu,editMenu,logMenu);
        mnuHbox.getChildren().add(mnuMenuBar);
        
        layout.setTop(mnuHbox);

        tvCustomer.setItems(buildCustList());



        //add Customer

        btnAddCustomer.setVisible(false);
        btnEditCustomer.setVisible(false);
        btnDeleteCustomer.setVisible(false);

        btnAddCustomer.setOnAction(event -> {
            AddCustomer a = new AddCustomer();
            a.display();

            tvCustomer.setItems(buildCustList());
        });

        btnLogin.setOnAction(e-> {
            LoginWindow liwin = new LoginWindow(); //instantiates the login window
            liwin.display(); //displays the login window
            if(MainScreen.getAuthUser()!= null){
                //displays the username if authenticated properly
                lblAuthUserLabel.setText(MainScreen.getAuthUser());
                btnAddCustomer.setVisible(true);
                btnEditCustomer.setVisible(true);
                btnDeleteCustomer.setVisible(true);
            }

        });

        btnEditCustomer.setOnAction(event -> {
            //gets the selected object for editing
            CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
        });

        btnDeleteCustomer.setOnAction(event -> {
            CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
            try{
                String sql = "delete from customer where customerId = ?";
                PreparedStatement pstmnt = MySQLDatabase.getMySQLConnection().prepareStatement(sql);
                pstmnt.setInt(1,compCust.getCustomerId());
                pstmnt.execute();
            }
            catch (SQLException e){
                e.getMessage();
            }
            tvCustomer.setItems(buildCustList());
        });

        btnRefreshList.setOnAction(event -> {
            tvCustomer.setItems(buildCustList());
            tvCustomer.refresh();
        });
        Scene scene = new Scene(layout,700,600);
        
        
        window.setScene(scene);
        window.show();
        
    }

    private static ObservableList<CompleteCustomer> buildCustList(){
        ObservableList<CompleteCustomer> custList = FXCollections.observableArrayList();
        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            String sql = "select customerId,customerName,active,addr.addressId,address,address2,postalCode,phone,ci.cityId,city,co.countryId,country from customer cou inner join\n" +
                    "address addr on cou.addressId = addr.addressId inner join city ci on addr.cityId = ci.cityId inner join\n" +
                    "country co on ci.countryId = co.countryId";
            ResultSet rs = stmnt.executeQuery(sql);
            while(rs.next()){
                CompleteCustomer c = new CompleteCustomer();
                c.setCustomerId(rs.getInt("customerId"));
                c.setCustomerName(rs.getString("customerName"));
                c.setActive(rs.getBoolean("active"));
                c.setFkAddressId(rs.getInt("addressId"));
                c.setAddress(rs.getString("address"));
                c.setAddress2(rs.getString("address2"));
                c.setPostalCode(rs.getString("postalCode"));
                c.setPhone(rs.getString("phone"));
                c.setFkCityId(rs.getInt("cityId"));
                c.setCity(rs.getString("city"));
                c.setFkCountryId(rs.getInt("countryId"));
                c.setCountry(rs.getString("country"));

                custList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }

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
