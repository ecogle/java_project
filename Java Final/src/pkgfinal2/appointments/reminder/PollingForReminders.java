package pkgfinal2.appointments.reminder;

import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import pkgfinal2.appointments.AppointmentController;
import pkgfinal2.appointments.TimeZoneController;
import sun.applet.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * Created by ecogle on 2/16/2017.
 */
public class PollingForReminders {

    List<Reminder> today;

    public PollingForReminders(){

    }



        // write to a Map of some sort (treeset?)

    //start a thread for polling the list for appointments times that at 15 minutes from current

        // at that time, pop up a window that will display the reminder.
        // have a spot on there for snooze

    public void startMe(){
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(6);
        TimeZoneController tzc = new TimeZoneController();

        ScheduledFuture every5Minutes = pool.scheduleAtFixedRate(()-> {
            today=getTodaysAppointmentReminders();

            Instant nowTime = ZonedDateTime.now().toInstant();

            // today contains time where user is.
            List<Reminder> temp = new ArrayList<>();
            for(Reminder d: today){
                String sdf = d.getReminderDate();
                LocalDateTime ldt = LocalDateTime.from(LocalDateTime.parse(d.getReminderDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n")));
                ZonedDateTime apptTime = ZonedDateTime.of(ldt,tzc.getUTCTimeZone());
                Instant ind = apptTime.toInstant();
            }
        },0,5,TimeUnit.MINUTES);
    }

    public List<Reminder> getTodaysAppointmentReminders(){
        String sql ="select * from reminder where date_format(date(reminderDate),'%Y-%m-%d') = ? and createdBy = ?";
        List<Reminder> todaysAppts = new ArrayList<>();
        TimeZoneController tz = new TimeZoneController();
        try( Connection conn = MySQLDatabase.getMySQLConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ps.setString(2,MainScreen.getAuthUser());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                todaysAppts.add(new ReminderBuilder()
                        .setReminderId(rs.getInt("reminderId"))
                        .setReminderDate(rs.getString("reminderDate"))
                        .setReminderSnoozeIncrement(rs.getInt("snoozeIncrement"))
                        .setFkSnoozeIncrementTypeId(rs.getInt("snoozeIncrementTypeId"))
                        .setFkAppointmentId(rs.getInt("appointmentId"))
                        .setReminderCol(rs.getString("remindercol"))
                        .build()
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return todaysAppts;
    }

    //passing in the localized date from the base
    //  comparing it to  the time now. If the
    //  time is within 15 minutes, return true
    //  else, false





}
