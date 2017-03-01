package pkgfinal2.appointments;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import pkgfinal2.audit.Audit;

public class AppointmentView extends Audit{
    
    private SimpleStringProperty custName = new SimpleStringProperty();
    private SimpleStringProperty date = new SimpleStringProperty();
    private SimpleStringProperty startTime = new SimpleStringProperty();
    private SimpleStringProperty endTime = new SimpleStringProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty location = new SimpleStringProperty();
    private SimpleStringProperty contact = new SimpleStringProperty();
    private SimpleStringProperty url = new SimpleStringProperty();
    
    public void setCustName(String s){
        this.custName.set(s);
    }
    
    public void setDate(String s){
        this.date.set(s);
    }
    
    public void setStartTime(String s){
        this.startTime.set(s);
    }

    public void setEndTime(String s){
        this.endTime.set(s);
    }
    
    public void setTitle(String s){
        this.title.set(s);
    }
    
    public void setDescription(String s){
        this.description.set(s);
    }
    
    public void setLocation(String s){
        this.location.set(s);
    }
    
    public void setContact(String s){
        this.contact.set(s);
    }
    
    public void setUrl(String s){
        this.url.set(s);
    }
}
