package pkgfinal2.appointments;

import pkgfinal2.MySQLDatabase;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.*;

/**
 * Created by ecogle on 2/14/2017.
 */
public class TimeTester {
    static int w=1;
    public static void main(String[] args) {
        // testMe();
        //testMe2();
        //testMe3();
        //testMe4();
        testMe5();
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
        TimeZoneController tzc = new TimeZoneController();
        LocalDate ld = LocalDate.of(2017,02,15);
        String lt = "10:00AM EDT";
        LocalTime lts = LocalTime.from(DateTimeFormatter.ofPattern("HH:mma z").parse(lt));
        ZonedDateTime zdt = ZonedDateTime.of(ld,lts,ZoneId.of("US/Eastern")).withZoneSameInstant(ZoneId.of("GMT"));
        String asdf = tzc.dateTimePickersToUtc(ld,lt);
    }

    public static void testMe3(){
        TimeZoneController tx = new TimeZoneController();
        String s = "2017-02-17 13:30:00.0";
        System.out.println(tx.stringZonedLocalTimeFromBase(s));
    }

    public static void testMe4(){
        ZonedDateTime zdt = ZonedDateTime.of(LocalDateTime.now(),ZoneId.of("GMT"));

        ZonedDateTime zdt2 = zdt.withZoneSameInstant(ZoneId.of("US/Eastern"));
    }

    public static void testMe5(){
        Instant nowTime = ZonedDateTime.now(ZoneId.of("US/Central")).toInstant();
        ZonedDateTime apptTime = ZonedDateTime.of(2017,02,17,14,10,00,00,ZoneId.of("US/Central"));
        Instant spppp = apptTime.toInstant();

        System.out.println(nowTime.compareTo(spppp.minus(Duration.ofMinutes(15))));

    }
}
