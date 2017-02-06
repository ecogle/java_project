package pkgfinal2.user;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.Audit;


public class User extends Audit {
    
    private SimpleIntegerProperty userId = new SimpleIntegerProperty();
    private SimpleStringProperty userName = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleBooleanProperty active = new SimpleBooleanProperty();

    public User(){
        super();
    }

    public Integer getUserId() {
        return userId.get();
    }

    public void setUserId(SimpleIntegerProperty userId) {
        this.userId = userId;
    }

    public void setUserId(int id){
        this.userId.set(id);
    }

    public String getUserName(){
        return userName.get();
    }

    public void setUserName(SimpleStringProperty userName) {
        this.userName = userName;
    }

    public void setUserNameString(String username){
        this.userName.set(username);
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
    public void setActiveBoolean(boolean active){
        this.active.set(active);
    }

    public String getCreatedBy() {
        return createdBy.get();
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy.set(createdBy);
    }
/*
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
    */
    @Override
    public String toString(){
        //return this.userName.toString() + " " + this.getCreatedByProperty().toString() + " " + this.active.toString();
        return "";
    }
}
