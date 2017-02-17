package pkgfinal2.appointments.reminder;

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

    public Reminder build(){
        return this.reminder;
    }


}
