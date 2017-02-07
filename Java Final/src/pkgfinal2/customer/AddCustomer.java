package pkgfinal2.customer;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.audit.Audit;


/**
 * Customer form for adding information.
 *
 *  search for existing country first, if exists, return the id, if not, add it, then return the id.
 *  do the same for the city and address
 */
public class AddCustomer extends Audit implements Displayable {

    @Override
    public void display(){

        Stage window = new Stage();
        window.setTitle("Add Customer");

        Label lblCustomerName = new Label("Customer Name: ");
        Label lblAddress1 = new Label("Address: ");
        Label lblAddress2 = new Label("Address2: ");
        Label lblCity = new Label("City: ");
        Label lblZipCode = new Label("Zip: ");
        Label lblPhoneNumber = new Label("Phone #: ");

    }




}
