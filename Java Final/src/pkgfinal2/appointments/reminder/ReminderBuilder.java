package pkgfinal2.appointments.reminder;

import pkgfinal2.MainScreen;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/14/2017.
 */
public class ReminderBuilder {

    private Reminder reminder;

    public ReminderBuilder(){
        reminder = new Reminder();

    }

    public ReminderBuilder setReminderId(int id){
        this.reminder.setReminderID(id);
        return this;
    }
    public ReminderBuilder setReminderDate(String date){

        this.reminder.setReminderDate(date);
        return this;
    }

    public ReminderBuilder setReminderSnoozeIncrement(int increment){
        this.reminder.setSnoozeIncrement(increment);
        return this;
    }

    public ReminderBuilder setFkSnoozeIncrementTypeId(int id){
        this.reminder.setFkSnoozeIncrementTypeId(id);
        return this;
    }

    public ReminderBuilder setFkAppointmentId(int id){
        this.reminder.setFkAppointmentId(id);
        return this;
    }

    public ReminderBuilder setReminderCol(String str){
        this.reminder.setReminderCol(str);
        return this;
    }

    public ReminderBuilder setCreatedBy(String str){
        this.reminder.setCreatedBy(str);
        return this;
    }

    public ReminderBuilder setCreateDate(String str){
        LocalDateTime ldt = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ZonedDateTime zdt = ZonedDateTime.of(ldt, MainScreen.getZoneId());
        zdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        this.reminder.setCreateDate(zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return this;
    }

    public Reminder build(){
        return this.reminder;
    }


}
