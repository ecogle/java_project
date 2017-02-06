package pkgfinal2.customer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.Audit;

/**
 * Property class for the Customer
 */
public class Customer extends Audit{

    private SimpleIntegerProperty customerId= new SimpleIntegerProperty();
    private SimpleBooleanProperty active= new SimpleBooleanProperty();
    private SimpleIntegerProperty addressId = new SimpleIntegerProperty();
    private SimpleStringProperty customerName = new SimpleStringProperty();

    public void setCustomerId(int id){
        this.customerId.set(id);
    }

    public Integer getCustomerId(){
        return this.customerId.get();
    }

    public void setActive(boolean active){
        this.active.set(active);
    }

    public Boolean getActive(){
        return this.active.get();
    }

    public void setAddressId(int id){
        this.addressId.set(id);
    }

    public Integer getAddressId(){
        return this.addressId.get();
    }

    public void setCustomerName(String fname, String lname){
        this.customerName.set(fname + " " + lname);
    }

    public String getCustomerName(){
        return this.customerName.getName();
    }



}
