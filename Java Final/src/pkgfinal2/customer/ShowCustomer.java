package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class ShowCustomer {
    private static int countryId;
    private Map<String,String> map = new HashMap<>();
    private CompleteCustomer origin;
    private CompleteCustomer modified;
    CheckBox chkActive;

    private Map<String,TextField> txtControls = new HashMap<>();

    public ShowCustomer(CompleteCustomer c){
        origin = new CompleteCustomer();
        origin = c;
        this.initTextFields();
    }
    public void display() {

        Stage window = new Stage();
        window.setTitle("Customer View");
        window.setOnCloseRequest(event -> {
            //event.consume();
        });

        window.initModality(Modality.APPLICATION_MODAL);
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


        // borderpane for the overall layout of the screen
        BorderPane layout = new BorderPane();
        MenuBar custMenu = new MenuBar();

        //binds the width of the menubar to the width of the scene
        custMenu.prefWidthProperty().bind(window.widthProperty());

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
        Menu mnuEdit = new Menu("Edit");
        MenuItem editCustomer = new MenuItem("Edit Customer Name");
        MenuItem editAddress = new MenuItem("Edit Customer Address (change address)");
        SeparatorMenuItem s = new SeparatorMenuItem();
        s.setStyle("-fx-border-color:red");
        Menu editAddressForAll =new Menu("Modify for ALL customers");
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
        GridPane btnGridPane = new GridPane();
        //btnGridPane.add(btnReset, 0, 0);
        //btnGridPane.add(btnEdit, 1, 0);
        //btnGridPane.setHgap(8);
        populateExistingFields();

        //using a stream to touch all of the textfield controls
        this.txtControls.forEach((e,f) ->{
            f.setEditable(false);
            f.setStyle("-fx-background-color: #dedfe0");
        });
        chkActive.setDisable(true);

        gpControls.add(btnGridPane, 1, 9);

        layout.setCenter(gpControls);

        Scene scene = new Scene(layout, 450, 400);

        window.setScene(scene);
        window.showAndWait();
    }

    private void populateExistingFields(){
        this.txtControls.get("txtCustomer").setText(origin.getCustomerName());
        this.txtControls.get("txtAddress").setText(origin.getAddress());
        this.txtControls.get("txtAddress2").setText(origin.getAddress2());
        this.txtControls.get("txtCity").setText(origin.getCity());
        this.txtControls.get("txtZip").setText(origin.getPostalCode());
        this.txtControls.get("txtPhoneNumber").setText(origin.getPhone());
        this.txtControls.get("txtCountry").setText(origin.getCountry());
        chkActive.setSelected(origin.getActive());
    }

    private void initTextFields(){
        this.txtControls.put("txtCustomer",new TextField());
        this.txtControls.put("txtAddress",new TextField());
        this.txtControls.put("txtAddress2",new TextField());
        this.txtControls.put("txtCity",new TextField());
        this.txtControls.put("txtZip",new TextField());
        this.txtControls.put("txtPhoneNumber",new TextField());
        this.txtControls.put("txtCountry",new TextField());
    }

    private void assignNewFields(){
        modified.setCustomerId(origin.getCustomerId());
    }
}
