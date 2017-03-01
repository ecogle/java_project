package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import sun.applet.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 3/1/2017.
 */
public class AppointmentViewController {


    /*
        pass in the current date
     */
    public ObservableList<AppointmentView> getApptByWeek(LocalDate ld){
        /*
        select c.customerName, a.title, a.description, a.location, a.contact, a.url,a.start,a.end from appointment a
        inner join
        customer c on a.customerId = c.customerId
        where a.createdBy = 'uname'
         */

        ObservableList<AppointmentView> apptList = FXCollections.observableArrayList();

        LocalDate startWeek = this.getStartOfWeek(ld);
        LocalDate endWeek = startWeek.plusDays(4);
        String sql = "select c.customerName, a.title, a.description, a.location, a.contact, a.url,a.start,a.end from " +
                "appointment a inner join " +
                "customer c on a.customerId = c.customerId where a.createdBy = ? and (start between ? and ?)";

        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){
            ps.setString(1,MainScreen.getAuthUser());
            ps.setString(2,startWeek.toString());
            ps.setString(3,endWeek.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                LocalDateTime ldtStart = LocalDateTime.from(LocalDateTime.parse(rs.getString("start"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n")));
                ZonedDateTime zdtStart = ZonedDateTime.of(ldtStart, ZoneId.of("UTC"));
                zdtStart = zdtStart.withZoneSameInstant(MainScreen.getZoneId());
                LocalTime ldStartTime = zdtStart.toLocalTime();

                LocalDateTime ldtEnd = LocalDateTime.from(LocalDateTime.parse(rs.getString("end"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n")));
                ZonedDateTime zdtEnd = ZonedDateTime.of(ldtEnd, ZoneId.of("UTC"));
                zdtEnd = zdtEnd.withZoneSameInstant(MainScreen.getZoneId());
                LocalTime ldEndTime = zdtEnd.toLocalTime();

                apptList.add(new AppointmentViewBuilder()
                        .setName(rs.getString("customerName"))
                        .setTitle(rs.getString("title"))
                        .setDate(zdtStart.toLocalDate().toString())
                        .setDescription(rs.getString("description"))
                        .setLocation(rs.getString("location"))
                        .setContact(rs.getString("contact"))
                        .setUrl(rs.getString("url"))
                        .setStartTime(zdtStart.format(DateTimeFormatter.ofPattern("hh:mma z")))
                        .setEndTime(zdtEnd.format(DateTimeFormatter.ofPattern("hh:mma z")))
                        .build()
                );
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return apptList;
    }

    /*
        pass in the current date and get the beginning of the work week
     */
    public LocalDate getStartOfWeek(LocalDate ld){
        switch (ld.getDayOfWeek().toString()){
            case "SUNDAY":
                return ld.plusDays(1);
            case "MONDAY":
                return ld;
            case "TUESDAY":
                return ld.minusDays(1);
            case "WEDNESDAY":
                return ld.minusDays(2);
            case "THURSDAY":
                return ld.minusDays(3);
            case "FRIDAY":
                return ld.minusDays(4);
            case "SATURDAY":
                return ld.minusDays(5);

        }
        return LocalDate.now();
    }

    public static void main(String[] s){
        AppointmentViewController av = new AppointmentViewController();
        System.out.println(av.getStartOfWeek(LocalDate.of(2017,3,4)).toString());
        ObservableList<AppointmentView> aptList = av.getApptByWeek(LocalDate.parse("2017-03-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    }

}
