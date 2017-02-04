package pkgfinal2;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
    
    private SimpleIntegerProperty userId;
    private SimpleStringProperty userName;
    private SimpleStringProperty password;
    private SimpleBooleanProperty active;
    private SimpleStringProperty createdBy;
    private SimpleStringProperty createDate;
    private SimpleStringProperty lastUpdate;
    private SimpleStringProperty lastUpdatedBy;


    public User(String username,boolean active,String createdBy){
        this.userName= new SimpleStringProperty(username);

        this.active = new SimpleBooleanProperty(active);
        this.createdBy = new SimpleStringProperty(createdBy);

    }
    public SimpleIntegerProperty getUserId() {
        return userId;
    }

    public void setUserId(SimpleIntegerProperty userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(SimpleStringProperty userName) {
        this.userName = userName;
    }

    public SimpleStringProperty getPassword() {
        return password;
    }

    public void setPassword(SimpleStringProperty password) {
        this.password = password;
    }

    public boolean getActive() {
        return active.get();
    }

    public void setActive(SimpleBooleanProperty active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public void setCreatedBy(SimpleStringProperty createdBy) {
        this.createdBy = createdBy;
    }

    public SimpleStringProperty getCreateDate() {
        return createDate;
    }

    public void setCreateDate(SimpleStringProperty createDate) {
        this.createDate = createDate;
    }

    public SimpleStringProperty getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(SimpleStringProperty lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public SimpleStringProperty getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(SimpleStringProperty lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    @Override
    public String toString(){
        return this.userName.toString() + " " + this.createdBy.toString() + " " + this.active.toString();
    }
}
