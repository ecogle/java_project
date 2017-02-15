package pkgfinal2.appointments.reminder;

import pkgfinal2.MySQLDatabase;
import pkgfinal2.appointments.reminder.Reminder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ecogle on 2/13/2017.
 */
public class ReminderController {

    private Reminder reminder;

    public ReminderController(Reminder r){
        this.reminder = r;
        this.reminder.setReminderID(getHighestReminderId()+1);
    }

    public boolean addReminderToDatabase(int incrementTypeId,int appointmentId){
        String sql = "insert into reminder (reminderId) values (?)";
        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){
            ps.setInt(1,this.reminder.getReminderId());
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    public Reminder selectReminder(){

        return new Reminder();
    }

    // todo have this go ahead and return the NEXT Primary Key
    private int getHighestReminderId(){
        String str = "select max(reminderId) from reminder";
        try(Statement stmnt = MySQLDatabase.getMySQLDataSource().getConnection().createStatement()){
            ResultSet rs = stmnt.executeQuery(str);
            if(rs.next()){
                return rs.getInt(0);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


}
