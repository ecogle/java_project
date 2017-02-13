package pkgfinal2.appointments;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.Audit;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;

/**
 * Created by ecogle on 2/12/2017.
 */
public class Appointment extends Audit {

    private SimpleIntegerProperty appointmentId = new SimpleIntegerProperty();
    private SimpleIntegerProperty fkCustomerId = new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty location = new SimpleStringProperty();
    private SimpleStringProperty contact = new SimpleStringProperty();
    private SimpleStringProperty url = new SimpleStringProperty();
    private ZonedDateTime start;
    private ZonedDateTime end;


    public void setAppointmentId(int id){
        this.appointmentId.set(id);
    }

    public void setFkCustomerId(int id){
        this.fkCustomerId.set(id);
    }

    public void setTitle(String str){
        this.title.set(str);
    }

    public void setDescription(String str){
        this.description.set(str);
    }

    public void setLocation(String str){
        this.location.set(str);
    }

    public void setContact(String str){
        this.contact.set(str);
    }

    public void setUrl(String str){
        this.url.set(str);
    }

    public void setStart(ZonedDateTime z){
        this.start = z;
    }

    public void setEnd(ZonedDateTime s){
        this.end = s;
    }

}
