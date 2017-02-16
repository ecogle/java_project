package pkgfinal2.appointments;
import pkgfinal2.appointments.reminder.*;
/**
 * Created by ecogle on 2/16/2017.
 */
public interface Schedulable {

    public void scheduleMe(Appointment apt, Reminder reminder);
}
