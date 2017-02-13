package pkgfinal2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pkgfinal2.appointments.AddAppointment;
import pkgfinal2.customer.*;
import pkgfinal2.login.LoginWindow;
import javafx.scene.layout.HBox;
import sun.applet.Main;

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
    Button btnLogin = new Button("Login");
    Button btnLogoff = new Button("Logoff");

    //sets the selected customer in the TableView
    private static CompleteCustomer selectedCustomer;

    @Override
    public void start(Stage primaryStage) {
        int intButtonWidth = 110; //width of the buttons
        window = primaryStage; //primary stage
        window.setTitle("Customer Main");
        
        // Labels and Buttons
        Label lblAuthUserLabel = new Label(MainScreen.getAuthUser());
        Button btnAddCustomer = new Button("Add Customer");
        Button btnEditCustomer = new Button("Edit Customer");
        Button btnDeleteCustomer = new Button("Delete Customer");
        Button btnRefreshList = new Button("Refresh list");
        Button btnAddAppointment = new Button("Schedule Appt.");

        //set width of the buttons to be the same
        btnLogin.setPrefWidth(intButtonWidth);
        btnAddCustomer.setPrefWidth(intButtonWidth);
        btnDeleteCustomer.setPrefWidth(intButtonWidth);
        btnEditCustomer.setPrefWidth(intButtonWidth);
        btnLogoff.setPrefWidth(intButtonWidth);
        btnAddAppointment.setPrefWidth(intButtonWidth);

        // using BorderPane for the generalized layout
        BorderPane layout = new BorderPane();
        layout.setPrefSize(700,600);

        // TableView to list the customer information
        TableView tvCustomer = new TableView();
        TableColumn colCustName = new TableColumn("Name");
        TableColumn colAddress = new TableColumn("Address");
        TableColumn colAddress2 = new TableColumn("Address2");
        TableColumn colCity = new TableColumn("City");
        TableColumn colCountry = new TableColumn("Country");
        TableColumn colActive = new TableColumn("Active");
        tvCustomer.getColumns().addAll(colCustName,colAddress,colAddress2,colCity,colCountry,colActive);

        // Links the columns to the properties of the object
        colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAddress2.setCellValueFactory(new PropertyValueFactory<>("address2"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));

        // nested GridPane to keep the buttons organized
        GridPane gpButtons = new GridPane();
        gpButtons.setVgap(5);
        gpButtons.add(btnLogin,0,0); // stacked the Login/Logoff button and toggleing the Visible property
        gpButtons.add(btnLogoff,0,0);
        gpButtons.add(btnAddCustomer,0,1);
        gpButtons.add(btnEditCustomer,0,2);
        gpButtons.add(btnDeleteCustomer,0,3);
        gpButtons.add(btnAddAppointment,0,4);

        // VBox nested inside the GridPane for vertical layout... probably not necessary
        VBox vbxLeft = new VBox();
        vbxLeft.setPadding(new Insets(30,10,10,15));
        vbxLeft.setSpacing(10);
        vbxLeft.getChildren().addAll(gpButtons);
        layout.setLeft(vbxLeft);
        layout.setCenter(tvCustomer);

        // Menu bar across the top of the window
        MenuBar mnuMenuBar = new MenuBar();
        mnuMenuBar.prefWidthProperty().bind(window.widthProperty()); // binds the menu width to the window width

        // Creates the menu items
        Menu fileMenu = new Menu("File");
        MenuItem mnuFile = new MenuItem("Open record");
        Menu editMenu = new Menu("Edit");
        Menu logMenu = new Menu("Users");
        MenuItem mnuLogin = new MenuItem("Login");
        MenuItem mnuLogoff = new MenuItem("Logoff");
        MenuItem mnuEditCity = new MenuItem("Edit City...");
        MenuItem mnuEditCounty = new MenuItem("Edit Country...");
        MenuItem mnuEditCustomer = new MenuItem("Edit Customer...");
        HBox mnuHbox = new HBox();
        editMenu.getItems().addAll(mnuEditCity,mnuEditCounty,mnuEditCustomer);
        fileMenu.getItems().add(mnuFile);
        logMenu.getItems().addAll(mnuLogin,mnuLogoff);
        mnuMenuBar.getMenus().addAll(fileMenu,editMenu,logMenu);
        mnuHbox.getChildren().add(mnuMenuBar);
        layout.setTop(mnuHbox); // ADDS THE HBOX TO THE BORDERPANE TOP

        //**************************************************
        //                  EVENT HANDLERS                 *
        //**************************************************

        EventHandler editMe = e -> {
            CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
            new EditCustomer(compCust).display();
            tvCustomer.setItems(MainClassController.buildCustList());
            tvCustomer.refresh();
        };

        mnuEditCustomer.setOnAction(editMe);

        btnAddCustomer.setOnAction(event -> {
            AddCustomer a = new AddCustomer();
            a.display();
            tvCustomer.setItems(MainClassController.buildCustList());
            tvCustomer.refresh();
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
                tvCustomer.setVisible(true);
                btnLogin.setVisible(false);
                btnLogoff.setVisible(true);
                btnAddAppointment.setVisible(true);
                // Calls the static method "buildCustList" to set the items of the list
                tvCustomer.setItems(MainClassController.buildCustList());
            }
        });

        btnLogoff.setOnAction(event -> {
            MainScreen.setAuthUser(null);
            btnAddCustomer.setVisible(false);
            btnEditCustomer.setVisible(false);
            btnDeleteCustomer.setVisible(false);
            tvCustomer.setVisible(false);
            btnLogin.setVisible(true);
            btnLogoff.setVisible(false);
            btnAddAppointment.setVisible(false);
        });

        //double-click event
        tvCustomer.setOnMouseClicked(event -> {
            if(event.getClickCount() ==2){
                CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
                setSelectedCustomer(compCust);
                new ShowCustomer().display();
                tvCustomer.setItems(MainClassController.buildCustList());
                tvCustomer.refresh();
            }
        });

        btnEditCustomer.setOnAction(editMe);

        btnDeleteCustomer.setOnAction(event -> {
            CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
            MainClassController.deleteCustomer(compCust);
            tvCustomer.setItems(MainClassController.buildCustList());
            tvCustomer.refresh();
        });

        btnRefreshList.setOnAction(event -> {
            tvCustomer.setItems(MainClassController.buildCustList());
            tvCustomer.refresh();
        });

        btnAddAppointment.setOnAction(event -> {
            new AddAppointment().display();
        });
        //**************************************************
        //              END EVENT HANDLERS                 *
        //**************************************************


        // Calls the static method "buildCustList" to set the items of the list
        tvCustomer.setItems(MainClassController.buildCustList());

        // hides all of the buttons until successful login
        btnAddCustomer.setVisible(false);
        btnEditCustomer.setVisible(false);
        btnDeleteCustomer.setVisible(false);
        btnLogoff.setVisible(false);
        tvCustomer.setVisible(false);
        btnAddAppointment.setVisible(false);


        Scene scene = new Scene(layout,700,600);        
        
        window.setScene(scene);
        window.show();
        
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

    private static void setSelectedCustomer(CompleteCustomer c){
        selectedCustomer = c;
    }

    public static CompleteCustomer getSelectedCustomer(){

        return selectedCustomer;
    }
    
}
