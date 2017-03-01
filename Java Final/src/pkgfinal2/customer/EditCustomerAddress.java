package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by ecogle on 2/15/2017.
 */
public class EditCustomerAddress {

    private CompleteCustomer completeCustomer;

    public EditCustomerAddress(CompleteCustomer c){
        this.completeCustomer = c;
    }

    public void display(){
        Stage window = new Stage();
        window.setTitle("Edit Address");

        //controls
        Label lblAddress= new Label("Address");
        TextField txtAddress= new TextField();
        Label lblAddress2= new Label("Address2");
        TextField txtAddress2= new TextField();
        Label lblPhone= new Label("Phone:");
        TextField txtPhone= new TextField();
        Label lblPostalCode= new Label("Postal Code");
        TextField txtPostalCode= new TextField();
        Button btnClear = new Button("Clear");
        Button btnSubmit = new Button("Submit");

        //hbox container for buttons
        HBox hbButtons = new HBox();
        hbButtons.getChildren().addAll(btnClear,btnSubmit);
        hbButtons.setSpacing(5);
        hbButtons.setPadding(new Insets(4));

        //gridpane
        GridPane gp = new GridPane();
        gp.setVgap(8);
        gp.setHgap(5);
        gp.setPadding(new Insets(5));
        gp.add(lblAddress,0,0); gp.add(txtAddress,1,0);
        gp.add(lblAddress2,0,1); gp.add(txtAddress2,1,1);
        gp.add(lblPhone,0,2); gp.add(txtPhone,1,2);
        gp.add(lblPostalCode,0,3); gp.add(txtPostalCode,1,3);
        gp.add(hbButtons,1,5);

        // initial values
        txtAddress.setText(this.completeCustomer.getAddress());
        txtAddress2.setText(this.completeCustomer.getAddress2());
        txtPhone.setText(this.completeCustomer.getPhone());
        txtPostalCode.setText(this.completeCustomer.getPostalCode());

        //*********************************************
        //*             EVENT HANDLER                **
        //*********************************************
        btnSubmit.setOnAction(event -> {
            this.completeCustomer.setAddress(txtAddress.getText());
            this.completeCustomer.setAddress2(txtAddress2.getText());
            this.completeCustomer.setPhone(txtPhone.getText());
            this.completeCustomer.setPostalCode(txtPostalCode.getText());

            new AddressController(this.completeCustomer).updateCustomerAddress();
            window.close();
        });

        Scene scene = new Scene(gp,300,250);
        window.setScene(scene);
        window.showAndWait();
    }


}
