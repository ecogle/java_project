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
    
    
    
    
}
