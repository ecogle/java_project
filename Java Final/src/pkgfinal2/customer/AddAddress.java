package pkgfinal2.customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.audit.Audit;

/**
 * Created by ecogle on 2/5/2017.
 */
public class AddAddress extends Audit implements Displayable {
    Stage window;
    @Override
    public void display(){
        window = new Stage();
        window.setTitle("Add address");

        Label lblAddress1 = new Label("Address: ");
        Label lblAddress2 = new Label("Address: ");
        Label lblCity = new Label("City: ");
        Label lblPhone = new Label("Phone#: ");
        Label lblZipCode = new Label("Zip code: ");

        TextField txtAddress = new TextField();
        txtAddress.setPromptText("Address");

        TextField txtAddress2 = new TextField();
        txtAddress2.setPromptText("Address con't");

        // Populate with cities from database
        ComboBox cboCity = new ComboBox();
        cboCity.getItems().addAll("New York","Phoenix","London");
        Label lblAddCity = new Label("Add city");
        lblAddCity.setFont(new Font("Verdana",10));
        lblAddCity.setTextFill(Color.RED);
        lblAddCity.setOnMouseClicked(e->{
            new AddCity().display();
        });

        TextField txtPhone = new TextField();
        txtPhone.setPromptText("phone number");

        TextField txtZip = new TextField();
        txtZip.setPromptText("zip code");


        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setVgap(10);
        layout.add(lblAddress1,0,0);
        layout.add(txtAddress,1,0);
        layout.add(lblAddress2,0,1);
        layout.add(txtAddress2,1,1);
        layout.add(lblCity,0,2);
        layout.add(cboCity,1,2);
        layout.add(lblAddCity,2,2);
        layout.add(lblPhone,0,3);
        layout.add(txtPhone,1,3);
        layout.add(lblZipCode,0,4);
        layout.add(txtZip,1,4);

        Scene scene = new Scene(layout,350,400);
        window.setScene(scene);

        window.show();
    }

}
