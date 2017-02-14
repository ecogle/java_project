package pkgfinal2.appointments;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reminder {

    private SimpleIntegerProperty reminderID = new SimpleIntegerProperty();
    private SimpleStringProperty reminderDate = new SimpleStringProperty(); // DATETIME
    private SimpleIntegerProperty snoozeIncrement = new SimpleIntegerProperty(); // ? number of minutes to snooze ?
    private SimpleIntegerProperty snoozeIncrementTypeId = new SimpleIntegerProperty();// ? foreign key from increment type?
    private SimpleIntegerProperty fkAppointmentId = new SimpleIntegerProperty();
    private SimpleStringProperty reminderCol = new SimpleStringProperty();
    
    public int getReminderId(){
        return this.reminderID.get();
    }
    public String gerReminderDate(){
        return this.reminderDate.get();
    }
    public int getSnoozeIncrement(){
        return this.snoozeIncrement.get();
        
    }
    public int getSnoozeIncrementId(){
        return this.snoozeIncrementTypeId.get();
    }
    public int getFkAppointmentId(){
        return this.fkAppointmentId.get();
    }
    public String getReminderCol(){
        return this.reminderCol.get();
    }

    public void setReminderID(int id){
        this.reminderID.set(id);
    }

    public void setReminderDate(String str){
        this.reminderDate.set(str);
    }

    public void setSnoozeIncrement(int increment){
        this.snoozeIncrement.set(increment);
    }

    public void setSnoozeIncrementTypeId(int id){
        this.snoozeIncrementTypeId.set(id);
    }

    public void setFkAppointmentId(int id){
        this.fkAppointmentId.set(id);
    }

    public void setReminderCol(String str){
        this.reminderCol.set(str);
    }
    
    
}