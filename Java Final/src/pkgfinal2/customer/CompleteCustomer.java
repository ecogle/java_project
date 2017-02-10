package pkgfinal2.customer;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ecogle on 2/9/2017.
 */
public class CompleteCustomer {

    private SimpleIntegerProperty customerId = new SimpleIntegerProperty();
    private SimpleStringProperty customerName = new SimpleStringProperty();
    private SimpleBooleanProperty active = new SimpleBooleanProperty();
    private SimpleIntegerProperty fkAddressId = new SimpleIntegerProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty address2 = new SimpleStringProperty();
    private SimpleIntegerProperty fkCityId = new SimpleIntegerProperty();
    private SimpleStringProperty city = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty postalCode = new SimpleStringProperty();
    private SimpleIntegerProperty fkCountryId = new SimpleIntegerProperty();
    private SimpleStringProperty country = new SimpleStringProperty();

    //********************************************************
    //*                   SETTERS                            *
    //********************************************************

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public void setCustomerName(String customerName){
        this.customerName.set(customerName);
    }

    public void setActive(boolean active){
        this.active.set(active);
    }

    public void setFkAddressId(int fkAddressId){
        this.fkAddressId.set(fkAddressId);
    }

    public void setAddress(String address){
        this.address.set(address);
    }

    public void setAddress2(String address2){
        this.address2.set(address2);
    }

    public void setPostalCode(String postalCode){
        this.postalCode.set(postalCode);
    }

    public void setPhone(String phone){
        this.phone.set(phone);
    }

    public void setFkCityId(int fkCityId){
        this.fkCityId.set(fkCityId);
    }

    public void setCity(String city){
        this.city.set(city);
    }

    public void setFkCountryId(int fkCountryId){
        this.fkCountryId.set(fkCountryId);
    }

    public void setCountry(String country){
        this.country.set(country);
    }

    //*****************************************************
    //*                 GETTERS                           *
    //*****************************************************


    public int getCustomerId(){
        return this.customerId.get();
    }

    public String getCustomerName(){
        return this.customerName.get();
    }

    public boolean getActive(){
        return this.active.get();
    }

    public int getFkAddressId(){
        return this.fkAddressId.get();
    }

    public String getAddress(){
        return this.address.get();
    }

    public String getAddress2(){
        return this.address2.get();
    }

    public String getPostalCode(){
        return this.postalCode.get();
    }

    public String getPhone(){
        return this.phone.get();
    }

    public int getFkCityId(){
        return this.fkCityId.get();
    }

    public String getCity(){
        return this.city.get();
    }

    public int getFkCountryId(){
        return this.fkCountryId.get();
    }

    public String getCountry(){
        return this.country.get();
    }


}
