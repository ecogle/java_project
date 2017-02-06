package pkgfinal2.customer;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.*;

/**
 * Created by ecogle on 2/6/2017.
 */
public class Country extends Audit {
    private SimpleIntegerProperty countryId = new SimpleIntegerProperty();
    private SimpleStringProperty countryName = new SimpleStringProperty();

    public void setCountryId(int id){
        this.countryId.set(id);
    }

    public void setCountryName(String name){
        this.countryName.set(name);
    }

    public Integer getCountryId(){
        return this.countryId.get();
    }

    public String getCountryName(){
        return this.countryName.get();
    }
}
