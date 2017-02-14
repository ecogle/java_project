package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ecogle on 2/12/2017.
 */
public class AppointmentController {

    private Appointment appointment;


    public AppointmentController(){
        super();
    }

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

    public ObservableList<Appointment> getAppointmentList(){
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        //String sql = "select * from appointment where customerId = ?";
        //try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){
        String sql = "Select * from appointment where customerId = " + MainScreen.getSelectedCustomer().getCustomerId();
        try(Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement()){
            //ps.setInt(1, MainScreen.getSelectedCustomer().getCustomerId());
            //System.out.println(ps);
            //ResultSet rs = ps.executeQuery(sql);
            ResultSet rs = stmnt.executeQuery(sql);
            while(rs.next()){
                Appointment apt = new AppointmentBuilder()
                        .setAppointmentId(rs.getInt("appointmentId"))
                        .setFkCustomerId(rs.getInt("customerId"))
                        .setStart(getZonedDateTime(rs.getString("start")))
                        .setEnd(getZonedDateTime(rs.getString("end")))
                        .build();

                apptList.add(apt);
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return apptList;
    }

    private static String hackTheDot(String s){
        String d = s.substring(0,s.indexOf("."));
        return d;
    }

    private static ZonedDateTime getZonedDateTime(String str){
        ZonedDateTime zdt = ZonedDateTime
                .of(LocalDateTime
                        .parse(hackTheDot(str),DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm:ss")),ZoneId.systemDefault());

        return zdt;

    }
}
