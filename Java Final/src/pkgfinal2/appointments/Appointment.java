package pkgfinal2.appointments;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.audit.Audit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
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
//    private ZonedDateTime apptStart;
//    private ZonedDateTime apptEnd;




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
//        TimeZoneController tx = new TimeZoneController();
        this.start.set(z);
        this.startDate.set(LocalDateTime.from(LocalDateTime.parse(z,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        this.startTime.set(LocalDateTime.from(LocalDateTime.parse(z,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofPattern("h:mm a")));
//        if(this.end!=null) {
//            LocalDate ld = LocalDate.parse(z,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            LocalTime lt = LocalTime.parse(z,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            this.apptStart = ZonedDateTime.of(ld,lt,tx.getCurrentTimeZone());
//        }

    }

    public void setEnd(String s){
//        TimeZoneController tx = new TimeZoneController();
        this.end.set(s);
        this.endDate.set(LocalDateTime.from(LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        this.endTime.set(LocalDateTime.from(LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).format(DateTimeFormatter.ofPattern("h:mm a")));
//        if(this.start != null){
//            LocalDate ld = LocalDate.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            LocalTime lt = LocalTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//            this.apptStart = ZonedDateTime.of(ld,lt,tx.getCurrentTimeZone());
//        }
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

    public boolean isConflicting(Appointment p){
        TimeZoneController tx = new TimeZoneController();
        LocalDate ldAppt1Date = LocalDate.parse(this.startDate.get(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime ltAppt1Start = LocalTime.parse(this.startTime.get(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime ltAppt1End = LocalTime.parse(this.endTime.get(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ZonedDateTime zdtAppt1Start = ZonedDateTime.of(ldAppt1Date,ltAppt1Start,tx.getCurrentTimeZone());
        ZonedDateTime zdtAppt1End = ZonedDateTime.of(ldAppt1Date,ltAppt1End,tx.getCurrentTimeZone());

        LocalDate ldAppt2Date = LocalDate.parse(p.getEndDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime ltAppt2Start = LocalTime.parse(p.getStartTime(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalTime ltAppt2End = LocalTime.parse(p.getEndTime(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ZonedDateTime zdtAppt2Start = ZonedDateTime.of(ldAppt2Date,ltAppt2Start,tx.getCurrentTimeZone());
        ZonedDateTime zdtAppt2End = ZonedDateTime.of(ldAppt2Date,ltAppt2End,tx.getCurrentTimeZone());


        /*
            if appointment 2's start is before appointment 1's end
                return conflicting appointment
         */


        return true;
    }




}
