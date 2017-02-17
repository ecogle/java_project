package pkgfinal2.appointments;

import pkgfinal2.MySQLDatabase;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * Created by ecogle on 2/14/2017.
 */
public class TimeTester {
    static int w=1;
    public static void main(String[] args) {
        testMe();
        /*ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);

        ZoneId utc = ZoneId.of("UTC");

        //whatever the date is with whatever time zone.
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);

        //converts the same time to UTC
        ZonedDateTime zdtUTC = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println(zdtUTC);

        //convert back to zoned date time.
        ZonedDateTime zdtPacific = zdtUTC.withZoneSameInstant(ZoneId.of("US/Pacific"));
        System.out.println(zdtPacific);

        //testing
        Toolkit t = Toolkit.getDefaultToolkit();
        t.beep();

        System.out.println("Comparing... " + ZonedDateTime.now().compareTo(ZonedDateTime.now().plusHours(3)));


        ScheduledExecutorService scheduleMe = Executors.newScheduledThreadPool(5);

        //ScheduledFuture f = scheduleMe.scheduleAtFixedRate(()-> System.out.println("Hello there " + w++),1,3,TimeUnit.SECONDS);
        //ScheduledFuture x = scheduleMe.scheduleAtFixedRate(()-> System.out.println("BAMMM"),2,5,TimeUnit.SECONDS);
        ScheduledFuture y = scheduleMe.scheduleAtFixedRate(() -> pingBase(),4,5,TimeUnit.SECONDS);
        pingBase();*/



    }

    private static void pingBase(){
        String g = "";
        String sql = "select customerName from customer where customerName like '%Chad%'";
        try(Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement()){
            ResultSet rs = stmnt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("customerName")+ " " + LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm:ssa")));;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void testMe(){
        String date = "2017-02-16 13:58:36";
        System.out.println("Local time is: " + date);
        TimeZoneController tc = new TimeZoneController();
        String c = tc.stringToUTCTime(date);
        System.out.println("Stored in database as UTC time: " +c);
        System.out.println("Back to local time"+tc.stringToLocalTime(c));

        System.out.println("Now to UTC String: "+ tc.zonedDateTimeToUTCString(ZonedDateTime.now()));
    }

    public static void testMe2(){

    }
}
