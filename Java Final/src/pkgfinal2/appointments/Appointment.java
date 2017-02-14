package pkgfinal2.appointments;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.Audit;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
    private SimpleStringProperty startDate = new SimpleStringProperty();
    private SimpleStringProperty startTime = new SimpleStringProperty();
    private SimpleStringProperty endDate = new SimpleStringProperty();
    private SimpleStringProperty endTime = new SimpleStringProperty();
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
        this.startDate.set(z.format(DateTimeFormatter.ofPattern("MM/dd/YYYY")));
        this.startTime.set(z.format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    public void setEnd(ZonedDateTime s){
        this.end = s;
        this.endDate.set(s.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        this.endTime.set(s.format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    public int getAppointmentId(){
        return this.appointmentId.get();
    }

    public int getFkCustomerId(){
        return this.fkCustomerId.get();
    }

    public String getTitle(){
        return this.title.get();
    }

    public String getDescription(){
        return this.description.get();
    }

    public String getLocation(){
        return this.location.get();
    }

    public String getContact(){
        return this.contact.get();
    }

    public String getUrl(){
        return this.url.get();
    }

    public ZonedDateTime getStart(){
        return this.start;
    }

    public ZonedDateTime getEnd(){
        return this.end;
    }

    public String getStartTime(){
        return this.startTime.get();
    }
    public String getStartDate(){
        return this.startDate.get();
    }

    public String getEndDate(){
        return this.endDate.get();
    }

    public String getEndTime(){
        return this.endTime.get();
    }




}
