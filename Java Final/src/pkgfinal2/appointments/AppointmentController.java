package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import pkgfinal2.messages.MessageFactory;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/12/2017.
 */

    // todo store all times as UTC, then adjust the offset based on timezone.
public class AppointmentController {

    private Appointment appointment;


    public AppointmentController() {
        super();
    }

    public AppointmentController(Appointment a) {
        this.appointment = a;
        this.appointment.setAppointmentId(getHighestAppointmentId() + 1);
    }


    public static ObservableList<String> populateTimeSelection() {
        TimeZoneController tzc = new TimeZoneController();
        ObservableList<String> filler = FXCollections.observableArrayList();
        ZonedDateTime zdtTimes = ZonedDateTime.of(2016, 01, 01, 06, 00, 00, 00, MainScreen.getZoneId());

        ZonedDateTime zdtTimesEnd = zdtTimes.plusMinutes(750);

        while (zdtTimes.isBefore(zdtTimesEnd)) {
            filler.add(zdtTimes.format(DateTimeFormatter.ofPattern("h:mma z")));
            zdtTimes = zdtTimes.plusMinutes(30);
        }
        return filler;
    }

    public static ObservableList<LocalTime> populateLocalTimes() {
        ObservableList<LocalTime> newList = FXCollections.observableArrayList();
        LocalTime start = LocalTime.of(06, 00);
        LocalTime end = LocalTime.of(18, 30);
        while (start.isBefore(end)) {
            newList.add(start);
            start = start.plusMinutes(15);
        }
        return newList;
    }

    public static LocalTime parseTime(String str) {

        //find the "M" in the time string and parse out the time zone
        String d = str.substring(0, str.indexOf("M") + 1);
        LocalTime lTime = LocalTime.from(DateTimeFormatter.ofPattern("h:mma").parse(d));
        return lTime;
    }

    public static int getHighestAppointmentId() {
        try (Connection conn = MySQLDatabase.getMySQLConnection()) {
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery("select max(appointmentId) as maxId from appointment");
            if (rs.next()) {
                return rs.getInt("maxId");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return 1;
    }


    public void addToDatabase() {
        String mysqldg = "insert into appointment (appointmentId, customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy)" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?)";


        try (Connection conn = MySQLDatabase.getMySQLConnection()) {
            PreparedStatement ps = conn.prepareStatement(mysqldg);
            ps.setInt(1, this.appointment.getAppointmentId());
            ps.setInt(2, this.appointment.getFkCustomerId());
            ps.setString(3, this.appointment.getTitle());
            ps.setString(4, this.appointment.getDescription());
            ps.setString(5, this.appointment.getLocation());
            ps.setString(6, this.appointment.getContact());
            ps.setString(7, this.appointment.getUrl());
            ps.setString(8, this.appointment.getStart());
            ps.setString(9, this.appointment.getEnd());
            ps.setString(10, this.appointment.getCreateDate());
            ps.setString(11, this.appointment.getCreatedBy());
            ps.setString(12, this.appointment.getLastUpdate());
            ps.setString(13, this.appointment.getLastUpdateBy());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Appointment> getAppointmentList() {
        TimeZoneController tzc = new TimeZoneController();
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        String sql = "Select * from appointment where customerId = ? and createdBy = ?";
        try (Connection conn = MySQLDatabase.getMySQLConnection()) {
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setInt(1, MainScreen.getSelectedCustomer().getCustomerId());
            stmnt.setString(2, MainScreen.getAuthUser());
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                Appointment apt = new AppointmentBuilder()
                        .setAppointmentId(rs.getInt("appointmentId"))
                        .setFkCustomerId(rs.getInt("customerId"))
                        .setContact(rs.getString("contact"))
                        .setDescription(rs.getString("description"))
                        .setLocation(rs.getString("location"))
                        .setTitle(rs.getString("title"))
                        .setUrl(rs.getString("url"))
                        .setStart(tzc.stringZonedLocalTimeFromBase(rs.getString("start")))
                        .setEnd(tzc.stringZonedLocalTimeFromBase(rs.getString("end")))
                        .build();
                apptList.add(apt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apptList;
    }

    public static ObservableList<Appointment> getAppointmentListByDate(String sql) {
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        TimeZoneController tzc = new TimeZoneController();
        String sqls = "Select * from appointment where start like ? and createdBy = ?";
        try (Connection conn = MySQLDatabase.getMySQLConnection()) {
            PreparedStatement stmnt = conn.prepareStatement(sqls);
            stmnt.setString(1, sql + "%");
            stmnt.setString(2, MainScreen.getAuthUser());
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                Appointment apt = new AppointmentBuilder()
                        .setAppointmentId(rs.getInt("appointmentId"))
                        .setFkCustomerId(rs.getInt("customerId"))
                        .setContact(rs.getString("contact"))
                        .setDescription(rs.getString("description"))
                        .setLocation(rs.getString("location"))
                        .setTitle(rs.getString("title"))
                        .setUrl(rs.getString("url"))
                        .setStart(rs.getString("start"))
                        .setEnd(rs.getString("end"))
                        .build();
                apptList.add(apt);
            }
        } catch (SQLException s) {
            new MessageFactory().showMessage(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("There has been a SQL error");
                alert.showAndWait();
            });
        }
        return apptList;
    }

    private static String hackTheDot(String s) {
        String d = s.substring(0, s.indexOf("."));
        return d;
    }
}