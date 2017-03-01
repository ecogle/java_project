package pkgfinal2.appointments;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.Audit;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
    private SimpleStringProperty start= new SimpleStringProperty();
    private SimpleStringProperty end= new SimpleStringProperty();

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


    public void setStart(String z){

        if(z.length()>16){
            z = z.substring(0,16);
        }
        this.start.set(z);
        this.startDate.set(LocalDateTime.from(LocalDateTime.parse(z,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        this.startTime.set(LocalDateTime.from(LocalDateTime.parse(z,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofPattern("h:mm a")));
    }

    public void setEnd(String s){
        if(s.length()>16){
            s = s.substring(0,16);
        }
        this.end.set(s);
        this.endDate.set(LocalDateTime.from(LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        this.endTime.set(LocalDateTime.from(LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofPattern("h:mm a")));
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

    public String getStart(){
        return this.start.get();
    }

    public String getEnd(){
        return this.end.get();
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

    public static  boolean isConflicting(Appointment newAppt, Appointment existing){

        TimeZoneController tx = new TimeZoneController();

        LocalTime existingStart = LocalTime.parse(existing.getStart(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime existingEnd = LocalTime.parse(existing.getEnd(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime newApptStart = LocalTime.parse(newAppt.getStart(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime newApptEnd = LocalTime.parse(newAppt.getEnd(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if((newApptStart.isBefore(existingStart))&& (newApptEnd.isBefore(existingStart))){
            return false;
        }
        else if(newApptStart.isAfter(existingEnd)){
            return false;
        }
        else{
            return true;
        }
    }




}
