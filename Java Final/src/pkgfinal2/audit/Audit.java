package pkgfinal2.audit;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by ecogle on 2/5/2017.
 */
public class Audit {
    private SimpleStringProperty createDate = new SimpleStringProperty();;
    private SimpleStringProperty lastUpdate = new SimpleStringProperty();;
    private SimpleStringProperty lastUpdatedBy = new SimpleStringProperty();;
    public SimpleStringProperty createdBy = new SimpleStringProperty();;


    public void setCreateDate(String date){
        this.createDate.set(date);
    }

    public void setLastUpdate(String date){
        this.lastUpdate.set(date);
    }
    public void setLastUpdateBy(String user){
        this.lastUpdatedBy.set(user);
    }

    public String getCreateDateString(){
        return this.createDate.get();
    }
    public SimpleStringProperty getCreateDateProperty(){
        return this.createDate;
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }

    //java bean... must be formatted correctly
    public String getLastUpdate(){
        return this.lastUpdate.get();
    }
    public SimpleStringProperty getLastUpdateProperty(){
        return this.lastUpdate;
    }

    //java bean... must be formatted correctly
    public String getLastUpdateBy(){
        return this.lastUpdatedBy.get();
    }

    public SimpleStringProperty getLastUpdateByProperty(){
        return this.lastUpdate;
    }


}
