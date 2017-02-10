package pkgfinal2.customer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.*;
/**
 * Created by ecogle on 2/5/2017.
 */
public class City extends Audit {

    private SimpleIntegerProperty cityId = new SimpleIntegerProperty();
    private SimpleStringProperty cityName = new SimpleStringProperty();
    private SimpleIntegerProperty countryId = new SimpleIntegerProperty();

    public void setCityId(int id){
        this.cityId.set(id);
    }
    public Integer getCityId(){
        return this.cityId.get();
    }

    public void setCityName(String name){
        this.cityName.set(name);
    }

    public String getCityName(){
        return this.cityName.get();
    }

    public void setCountryId(int id){
        this.countryId.set(id);
    }

    public Integer getCountryId(){
        return this.countryId.get();
    }



}
