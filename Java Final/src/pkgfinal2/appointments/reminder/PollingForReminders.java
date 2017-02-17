package pkgfinal2.appointments.reminder;

import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import pkgfinal2.appointments.AppointmentController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by ecogle on 2/16/2017.
 */
public class PollingForReminders {

    List<Reminder> today;

    public PollingForReminders(){

    }

    // pull all of the appointments for today

        // write to a Map of some sort (treeset?)

    //start a thread for polling the list for appointments times that at 15 minutes from current

        // at that time, pop up a window that will display the reminder.
        // have a spot on there for snooze

    public void startMe(){


        //System.out.println(getTime(today.get(0).getReminderDate()));


        ScheduledExecutorService pool = Executors.newScheduledThreadPool(6);


        ScheduledFuture every15Minutes = pool.scheduleAtFixedRate(()-> {
            today=getTodaysAppointmentReminders();
            today.stream().map(e-> AppointmentController.getZonedDateTime(e.getReminderDate())).forEach(System.out::println);
        },0,5,TimeUnit.SECONDS);

    }

    public List<Reminder> getTodaysAppointmentReminders(){
        String sql ="select * from reminder where date_format(date(reminderDate),'%Y-%m-%d') <= ? ";
        List<Reminder> todaysAppts = new ArrayList<>();

        try( PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){
            ps.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
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

//    private ZonedDateTime getTime(String s){
//        //ZonedDateTime time = ZonedDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        //time = time.withZoneSameInstant(MainScreen.getZoneId());
//        return time;
//    }


}
