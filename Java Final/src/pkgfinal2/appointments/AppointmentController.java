package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pkgfinal2.MySQLDatabase;

import java.sql.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/12/2017.
 */
public class AppointmentController {

    private Appointment appointment;


    public AppointmentController(Appointment a){
        this.appointment = a;
        this.appointment.setAppointmentId(getHighestAppointmentId()+1);
    }


    public static ObservableList populateTimeSelection(){
        ObservableList<String> filler = FXCollections.observableArrayList();
        ZonedDateTime zdtTimes = ZonedDateTime.of(2016,01,01,06,00,00,00, ZoneId.systemDefault());
        ZonedDateTime zdtTimesEnd = zdtTimes.plusMinutes(750);

        while (zdtTimes.isBefore(zdtTimesEnd)){
            filler.add(zdtTimes.format(DateTimeFormatter.ofPattern("h:mma z")));
            zdtTimes = zdtTimes.plusMinutes(30);
        }
        return filler;
    }

    public static LocalTime parseTime(String str){

        //find the "M" in the time string and parse out the time zone
        String d = str.substring(0,str.indexOf("M")+1);
        LocalTime lTime = LocalTime.from(DateTimeFormatter.ofPattern("h:mma").parse(d));
        return lTime;
    }

    public static int getHighestAppointmentId(){
        try(Statement stmnt= MySQLDatabase.getMySQLConnection().createStatement()){
            ResultSet rs = stmnt.executeQuery("select max(appointmentId) as maxId from appointment");
            if(rs.next()){
                return rs.getInt("maxId");
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
        return 1;
    }


    public void addToDatabase(){
        String mysqldg = "insert into appointment (appointmentId, customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(mysqldg)){
            ps.setInt(1,this.appointment.getAppointmentId());
            ps.setInt(2,this.appointment.getFkCustomerId());
            ps.setString(3,this.appointment.getTitle());
            ps.setString(4,this.appointment.getDescription());
            ps.setString(5,this.appointment.getLocation());
            ps.setString(6,this.appointment.getContact());
            ps.setString(7,this.appointment.getUrl());
            ps.setString(8,this.appointment.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setString(9,this.appointment.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            ps.setString(10,this.appointment.getCreateDate());
            ps.setString(11,this.appointment.getCreatedBy());
            ps.setString(12,this.appointment.getLastUpdate());
            ps.setString(13,this.appointment.getLastUpdateBy());

            ps.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
