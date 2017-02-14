package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pkgfinal2.MainScreen;

import java.util.Optional;

/**
 * Only edits the Customer name
 */
public class EditCustomerName {

    public void display(){
        Stage window = new Stage();

        // Gridpane for the main layout
        GridPane layout = new GridPane();
        layout.setHgap(5);
        layout.setVgap(8);
        layout.setPadding(new Insets(10));

        // Labels and TextFields
        Label lblExistingName = new Label("Current Name");
        Label lblModifiedName = new Label("New Name");
        TextField txtExistingName = new TextField();
        txtExistingName.setDisable(true);
        txtExistingName.setText(MainScreen.getSelectedCustomer().getCustomerName());
        txtExistingName.setStyle("-fx-background-color: lightgray");
        TextField txtModifiedName = new TextField();
        txtModifiedName.setPromptText("Modified Name");

        //Buttons
        Button btnConfirm = new Button("Engage");
        Button btnCancel = new Button("Belay");

        // HBox to hold the buttons
        HBox hboxButtons = new HBox();
        hboxButtons.setSpacing(8);
        hboxButtons.getChildren().addAll(btnCancel,btnConfirm);

        // add controls to the GridPane
        layout.add(lblExistingName,0,0);
        layout.add(txtExistingName,1,0);
        layout.add(lblModifiedName,0,1);
        layout.add(txtModifiedName,1,1);
        layout.add(hboxButtons,1,3);

        //**********************************************
        //*               EVENT HANDLERS              **
        //**********************************************

        btnConfirm.setOnAction(event -> {
            // CONFIRMS the modification of the name
            //todo add some text to the alert box
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
             Optional<ButtonType> flag= confirm.showAndWait();
             if (flag.get()==ButtonType.OK){
                 // uses a static method of the CustomerController to update the customer
                 CustomerController.updateCustomerName(MainScreen.getSelectedCustomer().getCustomerId(),txtModifiedName.getText());
                 MainScreen.getSelectedCustomer().setCustomerName(txtModifiedName.getText()); // changes the name of the selected customer to the new name
                 window.close();
             }
        });

        // sets the scene
        window.setTitle("Edit customer name...");
        Scene scene = new Scene(layout,275,150);
        window.setScene(scene);
        window.showAndWait();
    }
}
