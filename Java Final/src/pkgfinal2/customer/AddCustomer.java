package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.audit.Audit;
import static pkgfinal2.customer.AddCustomerControl.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Customer form for adding information.
 *
 *  search for existing country first, if exists, return the id, if not, add it, then return the id.
 *  do the same for the city and address
 */
public class AddCustomer extends Audit implements Displayable {
    private static int countryId;
    private Map<String,String> map = new HashMap<>();
    @Override
    public void display(){

        Stage window = new Stage();
        window.setTitle("Add Customer");
        window.setOnCloseRequest(event -> {
           // event.consume();
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

        TextField txtCustomerName = new TextField();
        txtCustomerName.setPromptText("Customer name");

        TextField txtAddress1 = new TextField();
        txtAddress1.setPromptText("Address");

        TextField txtAddress2 = new TextField();
        txtAddress2.setPromptText("Address2");

        TextField txtCity = new TextField();
        txtCity.setPromptText("City");

        TextField txtZip = new TextField();
        txtZip.setPromptText("Zip");

        TextField txtCountry = new TextField();
        txtCountry.setPromptText("Country");
        
        TextField txtPhoneNumber= new TextField();
        txtPhoneNumber.setPromptText("Phone number");

        CheckBox chkActive = new CheckBox("Active");
        chkActive.setSelected(false);
        
        Button btnReset = new Button("Reset");
        Button btnAdd = new Button("Add");

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
        
        Menu mnuEdit = new Menu("Edit");
        Menu mnuView = new Menu("View");

        mnuFile.getItems().addAll(fileNew,fileSave,fileSaveAs,fileExit);
        custMenu.getMenus().addAll(mnuFile,mnuEdit,mnuView);
        HBox hbxMenu = new HBox();
        hbxMenu.getChildren().add(custMenu);
        layout.setTop(hbxMenu);

        GridPane gpControls = new GridPane();
        gpControls.setPadding(new Insets(10));
        gpControls.setHgap(15);
        gpControls.setVgap(8);
        gpControls.add(lblCustomerName,0,0);
        gpControls.add(txtCustomerName,1,0);
        gpControls.add(lblAddress1, 0, 1);
        gpControls.add(txtAddress1, 1, 1);
        gpControls.add(lblAddress2,0,2);
        gpControls.add(txtAddress2,1,2);
        gpControls.add(lblCity, 0, 3);
        gpControls.add(txtCity, 1, 3);
        gpControls.add(lblCountry, 0, 4);
        gpControls.add(txtCountry, 1, 4);
        gpControls.add(lblPhoneNumber, 0, 5);
        gpControls.add(txtPhoneNumber, 1, 5);
        gpControls.add(lblZipCode, 0, 6);
        gpControls.add(txtZip, 1, 6);
        gpControls.add(lblActive, 0, 7);
        gpControls.add(chkActive, 1, 7);
        
        
        GridPane btnGridPane = new GridPane();
        btnGridPane.add(btnReset, 0, 0);
        btnGridPane.add(btnAdd, 1, 0);
        btnGridPane.setHgap(8);
        
        gpControls.add(btnGridPane, 1, 9);

        btnAdd.setOnAction(event -> {
            String k = "hi";
            try {
                //**************************************************
                if(checkForEmpty(txtAddress1.getText())){
                    txtAddress1.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(textContainsNumbers(txtAddress1.getText())){
                    txtAddress1.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }
                else{
                    map.put("address",txtAddress1.getText());
                }
                //**************************************************
                if(checkForEmpty(txtAddress2.getText())){
                    txtAddress2.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(textContainsNumbers(txtAddress2.getText())){
                    txtAddress2.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }else{
                    map.put("address2",txtAddress2.getText());
                }
                //**************************************************
                if(checkForEmpty(txtPhoneNumber.getText())){
                    txtPhoneNumber.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(numbersContainText(txtPhoneNumber.getText())){
                    txtPhoneNumber.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }else{
                    map.put("phone",txtPhoneNumber.getText());
                }

                //**************************************************
                if(checkForEmpty(txtZip.getText())){
                    txtZip.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(numbersContainText(txtZip.getText())){
                    txtZip.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }else{
                    map.put("postalCode",txtZip.getText());
                }

                //**************************************************
                if(checkForEmpty(txtCustomerName.getText())){
                    txtCustomerName.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(textContainsNumbers(txtCustomerName.getText())){
                    txtCustomerName.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }else{
                    map.put("customerName",txtCustomerName.getText());
                }

                //**************************************************
                if(checkForEmpty(txtCity.getText())){
                    txtCity.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(textContainsNumbers(txtCity.getText())){
                    txtCity.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }else{
                    map.put("city",txtCity.getText());
                }

                //**************************************************
                if(checkForEmpty(txtCountry.getText())){
                    txtCountry.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputEmptyException();
                }else if(textContainsNumbers(txtCountry.getText())){
                    txtCountry.setStyle("-fx-backgound-color: lightred; -fx-color:yellow;");
                    throw new InputTypeException();
                }else{
                    map.put("country",txtCountry.getText());
                }

                map.put("active",((Boolean)chkActive.isSelected()).toString());
    
                AddCustomerControl.countryWork(txtCountry.getText());
                AddCustomerControl.cityWork(txtCity.getText());
                AddCustomerControl.addressWork(map);
                AddCustomerControl.customerWork(map);
                window.close();           
                
            } catch (InputEmptyException | InputTypeException e) {
                System.out.println(e.getMessage());
            }


        });       
        
        layout.setCenter(gpControls);

        Scene scene = new Scene(layout,450,400);

        window.setScene(scene);
        window.showAndWait();

    }


}
