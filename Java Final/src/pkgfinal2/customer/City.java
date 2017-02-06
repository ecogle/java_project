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

    private void setCityId(int id){
        this.cityId.set(id);
    }
    private Integer getCityId(){
        return this.cityId.get();
    }

    private void setCityName(String name){
        this.cityName.set(name);
    }

    private String getCityName(){
        return this.cityName.get();
    }

    private void setCountryId(int id){
        this.countryId.set(id);
    }

    private Integer getCountryId(){
        return this.countryId.get();
    }

}
