package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgfinal2.MainScreen;
import pkgfinal2.appointments.Appointment;
import pkgfinal2.appointments.AppointmentController;
import pkgfinal2.appointments.ShowAppointment;
import sun.applet.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ShowCustomer {
    //todo can probably delete this
    //private static int countryId; // can probably delete this.
    private Map<String,String> map = new HashMap<>();  // map that contains all of the TextFields on the page
    private static CompleteCustomer completeCustomer;  // the selected customer from the MainScreen
    private CompleteCustomer modified;
    CheckBox chkActive;

    private Map<String,TextField> txtControls = new HashMap<>();

    //todo See if you can pass the variables around as arguments rather than having static variables
    // constuctor that assigns the STATIC selected customer from mainscreen to the local
    // static variable
    public ShowCustomer(CompleteCustomer c){
        completeCustomer = c;
        this.initTextFields();  // initializes the text fields
    }

    // called when the window is displayed
    public void display() {

        Stage window = new Stage();
        window.setTitle("Customer View");
        window.initModality(Modality.APPLICATION_MODAL);  // sets the window to MODAL

        //event when the window close "red X" is clicked
        window.setOnCloseRequest(event -> {
            //event.consume();
        });

        // BorderPane layout as the main layout
        BorderPane layout = new BorderPane();

        //*********************************************
        //*        LABELS AND BUTTONS                **
        //*********************************************
        Label lblCustomerName = new Label("Customer Name: ");
        Label lblAddress1 = new Label("Address: ");
        Label lblAddress2 = new Label("Address2: ");
        Label lblCity = new Label("City: ");
        Label lblZipCode = new Label("Zip: ");
        Label lblPhoneNumber = new Label("Phone #: ");
        Label lblCountry = new Label("Country: ");
        Label lblActive = new Label("Active?");

        this.txtControls.get("txtCustomer").setPromptText("Customer name");
        this.txtControls.get("txtAddress").setPromptText("Address");
        this.txtControls.get("txtAddress2").setPromptText("Address2");
        this.txtControls.get("txtCity").setPromptText("City");
        this.txtControls.get("txtZip").setPromptText("Zip");
        this.txtControls.get("txtPhoneNumber").setPromptText("Phone #");
        this.txtControls.get("txtCountry").setPromptText("Country");

        chkActive = new CheckBox("Active");
        chkActive.setSelected(false);

        Button btnReset = new Button("Reset");
        Button btnEdit = new Button("Edit");

        //*********************************************
        //*        END LABELS AND BUTTONS            **
        //*********************************************

        // Menu bar across the top
        MenuBar custMenu = new MenuBar();
        custMenu.prefWidthProperty().bind(window.widthProperty()); //binds the width of the menubar to the width of the scene

        // File menu
        Menu mnuFile = new Menu("File");
        MenuItem fileNew = new MenuItem("New");
        MenuItem fileSave = new MenuItem("Save");
        MenuItem fileSaveAs = new MenuItem("Save As...");
        MenuItem fileExit = new MenuItem("Exit");


        fileExit.setOnAction(e->{
            Alert obj = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = obj.showAndWait();
            if(result.get() == ButtonType.OK){
                window.close();
            }
        });

        // Edit menu
        Menu mnuEdit = new Menu("Edit");
        MenuItem editCustomer = new MenuItem("Edit Customer Name");
        MenuItem editAddress = new MenuItem("Edit Customer Address");
        SeparatorMenuItem s = new SeparatorMenuItem(); // line separator
        s.setStyle("-fx-border-color:red");
        Menu editAddressForAll =new Menu("Modify for ALL customers");

        //sub menu
        MenuItem subMnuAddress = new MenuItem("Modify Address");
        MenuItem subMnuCountry = new MenuItem("Modify Country");
        MenuItem subMnuCity = new MenuItem("Modify City");
        Menu mnuView = new Menu("View");
        editAddressForAll.getItems().addAll(subMnuAddress,subMnuCity,subMnuCountry);
        mnuFile.getItems().addAll(fileNew, fileSave, fileSaveAs, fileExit);
        mnuEdit.getItems().addAll(editCustomer,editAddress,s,editAddressForAll);
        custMenu.getMenus().addAll(mnuFile, mnuEdit, mnuView);
        HBox hbxMenu = new HBox();
        hbxMenu.getChildren().add(custMenu);
        layout.setTop(hbxMenu);

        // Add the Labels and TextFields to a GridPane
        GridPane gpControls = new GridPane();
        gpControls.setPadding(new Insets(10));
        gpControls.setHgap(15);
        gpControls.setVgap(8);
        gpControls.add(lblCustomerName, 0, 0);
        gpControls.add(txtControls.get("txtCustomer"), 1, 0);
        gpControls.add(lblAddress1, 0, 1);
        gpControls.add(txtControls.get("txtAddress"), 1, 1);
        gpControls.add(lblAddress2, 0, 2);
        gpControls.add(txtControls.get("txtAddress2"), 1, 2);
        gpControls.add(lblCity, 0, 3);
        gpControls.add(txtControls.get("txtCity"), 1, 3);
        gpControls.add(lblCountry, 0, 4);
        gpControls.add(txtControls.get("txtCountry"), 1, 4);
        gpControls.add(lblPhoneNumber, 0, 5);
        gpControls.add(txtControls.get("txtPhoneNumber"), 1, 5);
        gpControls.add(lblZipCode, 0, 6);
        gpControls.add(txtControls.get("txtZip"), 1, 6);
        gpControls.add(lblActive, 0, 7);
        gpControls.add(chkActive, 1, 7);

        //* Try to integreate the STREAMS into this somehow. There has
        //* to be a way to use collect to write the values for the txtControls
        //* to a new ArrayList
        btnEdit.setOnAction(event -> {
            map.put("txtAddress",txtControls.get("txtAddress").getText());
            map.put("txtAddress2",txtControls.get("txtAddress2").getText());
            map.put("txtZip",txtControls.get("txtZip").getText());

        });

        // nested GridPane for the buttons
        GridPane btnGridPane = new GridPane();

        //Table for appointments
        TableView tvAppointments = new TableView();
        TableColumn colApptStartDate = new TableColumn("Start Date");
        TableColumn colApptEndDate = new TableColumn("End Date");
        TableColumn colApptStartTime = new TableColumn("Start Time");
        TableColumn colApptEndTime = new TableColumn("End Time");
        tvAppointments.getColumns().addAll(colApptStartDate,colApptStartTime,colApptEndDate,colApptEndTime);
        tvAppointments.setMinWidth(200);
        tvAppointments.setItems(new AppointmentController().getAppointmentList());

        colApptEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colApptEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colApptStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colApptStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));

        // populate the TextFields with the selected customer information
        populateExistingFields();

        //using a stream to disable all of the TextField controls
        this.txtControls.forEach((e,f) ->{
            f.setEditable(false);
            f.setStyle("-fx-background-color: #dedfe0");
        });
        chkActive.setDisable(true); // disable the Active button

        //*********************************************
        //*             EVENT HANDLER                **
        //*********************************************

        editAddress.setOnAction(event ->{
            new EditCustomerAddress(MainScreen.getSelectedCustomer()).display();
            populateExistingFields();
        });

        editCustomer.setOnAction(event -> {
            new EditCustomerName().display();
            txtControls.get("txtCustomer").setText(MainScreen.getSelectedCustomer().getCustomerName());
        });

        tvAppointments.setOnMouseClicked(event -> {
            if(event.getClickCount()==2){
                Appointment apt = (Appointment) tvAppointments.getSelectionModel().getSelectedItem();
                new ShowAppointment(apt).display();
            }
        });

        // sets up the scene
        gpControls.add(btnGridPane, 1, 9);
        layout.setCenter(gpControls);
        layout.setRight(tvAppointments);
        Scene scene = new Scene(layout, 700, 400);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     *  Populates the TextField with the selected customer data
     */
    private void populateExistingFields(){
        this.txtControls.get("txtCustomer").setText(completeCustomer.getCustomerName());
        this.txtControls.get("txtAddress").setText(completeCustomer.getAddress());
        this.txtControls.get("txtAddress2").setText(completeCustomer.getAddress2());
        this.txtControls.get("txtCity").setText(completeCustomer.getCity());
        this.txtControls.get("txtZip").setText(completeCustomer.getPostalCode());
        this.txtControls.get("txtPhoneNumber").setText(completeCustomer.getPhone());
        this.txtControls.get("txtCountry").setText(completeCustomer.getCountry());
        chkActive.setSelected(completeCustomer.getActive());
    }

    /**
     *  puts the TextFields in a Map<String,TextField> HashMap
     *  this is so Streams can be used to effect all of the TextField controls
     *  more efficiently
     */
    private void initTextFields(){
        this.txtControls.put("txtCustomer",new TextField());
        this.txtControls.put("txtAddress",new TextField());
        this.txtControls.put("txtAddress2",new TextField());
        this.txtControls.put("txtCity",new TextField());
        this.txtControls.put("txtZip",new TextField());
        this.txtControls.put("txtPhoneNumber",new TextField());
        this.txtControls.put("txtCountry",new TextField());
    }

    //todo can probably delete this
//    private void assignNewFields(){
//        modified.setCustomerId(completeCustomer.getCustomerId());
//    }

    /**
     * Returns the selected customer from the MainScreen
     * @return
     */
    public static CompleteCustomer getSelectedCustomer(){
        CompleteCustomer c = new CompleteCustomer();
        c = completeCustomer;
        return c;
    }
}
