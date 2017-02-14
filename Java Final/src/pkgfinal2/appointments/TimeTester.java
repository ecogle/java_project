package pkgfinal2.appointments;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by ecogle on 2/14/2017.
 */
public class TimeTester {

    public static void main(String[] args) {
        ZoneId.getAvailableZoneIds().stream().sorted().forEach(System.out::println);

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



    }
}
