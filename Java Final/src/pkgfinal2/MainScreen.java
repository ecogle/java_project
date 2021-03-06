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
import pkgfinal2.appointments.ShowByMonth;
import pkgfinal2.appointments.reminder.PollingForReminders;
import pkgfinal2.customer.*;
import pkgfinal2.login.LoginWindow;
import javafx.scene.layout.HBox;
import java.time.ZoneId;
import java.util.Optional;
import pkgfinal2.appointments.ShowByWeek;

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
    private static ZoneId zoneId = ZoneId.systemDefault(); // gets the default timezone

    //sets the selected customer in the TableView
    private static CompleteCustomer selectedCustomer;

    private String auththenticatedUser;

    private void setAuththenticatedUser(String str){
        this.auththenticatedUser = str;
    }
    public String getAuththenticatedUser(){
        return this.auththenticatedUser;
    }

    @Override
    public void start(Stage primaryStage) {
        new PollingForReminders().startMe();
        int intButtonWidth = 110; //width of the buttons
        window = primaryStage; //primary stage
        window.setTitle("Customer Main");
        window.setOnCloseRequest(event -> {
            event.consume();
            System.exit(0);
        });
        
        // Labels and Buttons
        Label lblAuthUserLabel = new Label(MainScreen.getAuthUser());
        Button btnAddCustomer = new Button("Add Customer");
        Button btnEditCustomer = new Button("Edit Customer");
        Button btnDeleteCustomer = new Button("Delete Customer");
        Button btnRefreshList = new Button("Refresh list");
        Button btnAddAppointment = new Button("Schedule Appt.");
        Button btnShowByWeek = new Button("Weekly Appts");
        Button btnShowByMonth = new Button("Monthly Appts");
        

        //set width of the buttons to be the same
        btnLogin.setPrefWidth(intButtonWidth);
        btnAddCustomer.setPrefWidth(intButtonWidth);
        btnDeleteCustomer.setPrefWidth(intButtonWidth);
        btnEditCustomer.setPrefWidth(intButtonWidth);
        btnLogoff.setPrefWidth(intButtonWidth);
        btnAddAppointment.setPrefWidth(intButtonWidth);
        btnShowByMonth.setPrefWidth(intButtonWidth);
        btnShowByWeek.setPrefWidth(intButtonWidth);

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
        gpButtons.add(btnShowByWeek, 0, 5);
        gpButtons.add(btnShowByMonth, 0, 6);

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
        MenuItem mnuExit = new MenuItem("Exit");
        Menu editMenu = new Menu("Edit");
        Menu logMenu = new Menu("Users");
        MenuItem mnuLogin = new MenuItem("Login");
        MenuItem mnuLogoff = new MenuItem("Logoff");
        MenuItem mnuEditCustomer = new MenuItem("Edit Customer...");
        HBox mnuHbox = new HBox();
        editMenu.getItems().addAll(mnuEditCustomer);
        fileMenu.getItems().addAll(mnuFile,mnuExit);
        logMenu.getItems().addAll(mnuLogin,mnuLogoff);
        mnuMenuBar.getMenus().addAll(fileMenu,editMenu,logMenu);
        mnuHbox.getChildren().add(mnuMenuBar);
        layout.setTop(mnuHbox); // ADDS THE HBOX TO THE BORDERPANE TOP

        //**************************************************
        //                  EVENT HANDLERS                 *
        //**************************************************

        EventHandler editMe = e -> {
            if(tvCustomer.getSelectionModel().isEmpty() == false){
                CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
                setSelectedCustomer(compCust);
                new ShowCustomer(compCust).display();
                tvCustomer.setItems(MainClassController.buildCustList());
                tvCustomer.refresh();
            }
        };

        mnuExit.setOnAction(event -> {
            System.exit(0);
        });
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
                this.setAuththenticatedUser(liwin.getAuththenticatedUser());
                lblAuthUserLabel.setText(MainScreen.getAuthUser());
                btnAddCustomer.setVisible(true);
                btnEditCustomer.setVisible(true);
                btnDeleteCustomer.setVisible(true);
                tvCustomer.setVisible(true);
                btnLogin.setVisible(false);
                btnLogoff.setVisible(true);
                btnAddAppointment.setVisible(true);
                btnShowByMonth.setVisible(true);
                btnShowByWeek.setVisible(true);
                // Calls the static method "buildCustList" to set the items of the list
                tvCustomer.setItems(MainClassController.buildCustList());
                //new PollingForReminders().startMe();
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

        btnShowByWeek.setOnAction(event -> {
            ShowByWeek sbw = new ShowByWeek();
            sbw.display();
        });

        btnShowByMonth.setOnAction(event -> {
            ShowByMonth sbm = new ShowByMonth();
            sbm.display();
        });
        //double-click event
        tvCustomer.setOnMouseClicked(event -> {
            if(event.getClickCount() ==2){
                CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
                setSelectedCustomer(compCust);
                new ShowCustomer(compCust).display();
                tvCustomer.setItems(MainClassController.buildCustList());
                tvCustomer.refresh();
            }
        });

        btnEditCustomer.setOnAction(editMe);

        btnDeleteCustomer.setOnAction(event -> {
            if(tvCustomer.getSelectionModel().isEmpty() == false){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Are you sure?");
                alert.setHeaderText("Deleting Customer...");
                Optional<ButtonType> result = alert.showAndWait();

                if(result.get()==ButtonType.OK){
                    CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
                    MainClassController.deleteCustomer(compCust);
                    tvCustomer.setItems(MainClassController.buildCustList());
                    tvCustomer.refresh();
                }
            }
        });

        btnRefreshList.setOnAction(event -> {
            tvCustomer.setItems(MainClassController.buildCustList());
            tvCustomer.refresh();
        });

        btnAddAppointment.setOnAction(event -> {
            if(tvCustomer.getSelectionModel().isEmpty()==false){
                CompleteCustomer compCust = (CompleteCustomer) tvCustomer.getSelectionModel().getSelectedItem();
                setSelectedCustomer(compCust);
                new AddAppointment().display();
            }
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
        btnShowByMonth.setVisible(false);
        btnShowByWeek.setVisible(false);
        

        //change for actual program
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
        return selectedCustomer;    }

    public static void setZoneId(ZoneId zone){
        zoneId = zone;
    }

    public static ZoneId getZoneId(){
        ZoneId d = zoneId;
        return d;
    }





}
