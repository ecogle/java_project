package pkgfinal2.customer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.*;

/**
 * Created by ecogle on 2/6/2017.
 */
public class Address extends Audit{
    private SimpleIntegerProperty addressId= new SimpleIntegerProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty address2 = new SimpleStringProperty();
    private SimpleIntegerProperty cityId = new SimpleIntegerProperty();
    private SimpleStringProperty phoneNumber = new SimpleStringProperty();
    private SimpleIntegerProperty postalCode = new SimpleIntegerProperty();

    public void setAddressId(int id){
        this.addressId.set(id);
    }

    public Integer getAddressId(){
        return this.addressId.get();
    }

    //****************************************
    public void setAddress (String addr){
        this.address.set(addr);
    }

    public String getAddress(){
        return this.address.get();
    }

    //****************************************
    public void setAddress2 (String addr){
        this.address2.set(addr);
    }

    public String getAddress2(){
        return this.address2.get();
    }

    //***************************************
    public void setCityId(int id){
        this.cityId.set(id);
    }

    public Integer getCityId(){
        return this.cityId.get();
    }

    //***************************************
    public void setPhoneNumber (String addr){
        this.phoneNumber.set(addr);
    }

    public String getPhoneNumber(){
        return this.phoneNumber.get();
    }

    //****************************************
    public void setPostalCode(int id){
        this.postalCode.set(id);
    }

    public Integer getPostalCode(){
        return this.postalCode.get();
    }
}
