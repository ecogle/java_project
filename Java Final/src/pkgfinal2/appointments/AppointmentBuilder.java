package pkgfinal2.appointments;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import pkgfinal2.MainScreen;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/13/2017.
 */
public class AppointmentBuilder {

    private Appointment appt= new Appointment();

    private SimpleIntegerProperty appointmentId = new SimpleIntegerProperty();
    private SimpleIntegerProperty fkCustomerId = new SimpleIntegerProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty description = new SimpleStringProperty();
    private SimpleStringProperty location = new SimpleStringProperty();
    private SimpleStringProperty contact = new SimpleStringProperty();
    private SimpleStringProperty url = new SimpleStringProperty();
    private SimpleStringProperty start = new SimpleStringProperty();
    private SimpleStringProperty end = new SimpleStringProperty();

    public AppointmentBuilder setAppointmentId(int id){
        this.appointmentId.set(id);
        return this;
    }

    public AppointmentBuilder setFkCustomerId(int id){
        this.fkCustomerId.set(id);
        return this;
    }

    public AppointmentBuilder setTitle(String str){
        this.title.set(str);
        return this;
    }

    public AppointmentBuilder setDescription(String str){
        this.description.set(str);
        return this;
    }

    public AppointmentBuilder setLocation(String str){
        this.location.set(str);
        return this;
    }

    public AppointmentBuilder setContact(String str){
        this.contact.set(str);
        return this;
    }

    public AppointmentBuilder setUrl(String str){
        this.url.set(str);
        return this;
    }

    // set DateTime as UTC
    public AppointmentBuilder setStart(String z){
        this.start.set(z);
        return this;
    }

    //set as UTC
    public AppointmentBuilder setEnd(String s){
        this.end.set(s);
        return this;
    }

    public Appointment build(){
        TimeZoneController tzc = new TimeZoneController();

        this.appt.setAppointmentId(this.appointmentId.get());
        this.appt.setFkCustomerId(this.fkCustomerId.get());
        this.appt.setTitle(this.title.get());
        this.appt.setDescription(this.description.get());
        this.appt.setLocation(this.location.get());
        this.appt.setContact(this.contact.get());
        this.appt.setUrl(this.url.get());
        this.appt.setStart(this.start.get());
        this.appt.setEnd(this.end.get());
        this.appt.setCreateDate(tzc.ldtToZdtStringUTC(LocalDateTime.now()));
        this.appt.setCreatedBy(MainScreen.getAuthUser());
        this.appt.setLastUpdate(tzc.ldtToZdtStringUTC(LocalDateTime.now()));
        this.appt.setLastUpdateBy(MainScreen.getAuthUser());

        return appt;
    }

    private static ZonedDateTime convertToUTC(ZonedDateTime z){
        ZonedDateTime utc = z.withZoneSameInstant(ZoneId.of("UTC"));
        return utc;
    }

}
