package pkgfinal2.appointments.reminder;

import com.mysql.jdbc.ResultSetRow;
import pkgfinal2.MySQLDatabase;
import pkgfinal2.appointments.reminder.Reminder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ecogle on 2/13/2017.
 */
public class ReminderController {

    private Reminder reminder;

    public ReminderController(){
        super();
    }

    public ReminderController(Reminder r){
        this.reminder = r;
        this.reminder.setReminderID(getHighestReminderId()+1);
    }

    public boolean addReminderToDatabase(int incrementTypeId,int appointmentId){
        String sql = "insert into reminder (reminderId,reminderDate,snoozeIncrement,snoozeIncrementTypeId,appointmentId,createdBy,createdDate,reminderCol) values" +
                " (?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){
            ps.setInt(1,this.reminder.getReminderId());
            ps.setString(2,this.reminder.getReminderDate());
            ps.setInt(3,this.reminder.getSnoozeIncrement());
            ps.setInt(4,this.reminder.getSnoozeIncrementId());
            ps.setInt(5,this.reminder.getFkAppointmentId());
            ps.setString(6,"Heathen");
            ps.setString(7, ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            ps.setString(8,this.reminder.getReminderCol());
            ps.execute();

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
        String str = "select max(reminderId) as maxId from reminder";
        try(Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement()){
            ResultSet rs = stmnt.executeQuery(str);
            if(rs.next()){
                return rs.getInt("maxId");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }







}
