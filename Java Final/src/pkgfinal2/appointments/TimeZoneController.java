package pkgfinal2.appointments;

import pkgfinal2.MainScreen;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/16/2017.
 */
public class TimeZoneController {

    private ZoneId currentTimeZone;
    private ZoneId UTCTimeZone = ZoneId.of("UTC");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");



    public TimeZoneController(){
        super();
        //currentTimeZone=ZoneId.systemDefault();
        currentTimeZone=MainScreen.getZoneId();
    }



    /**
     * Takes a string and returns the UTC time
     * @param str
     * @return
     */
    public String stringToUTCTime(String str){
        ZonedDateTime zdt = ZonedDateTime
                .of(LocalDateTime
                        .parse(str, getDtf()), currentTimeZone);
        return zdt.withZoneSameInstant(UTCTimeZone).format(getDtf());
    }



    public String stringToLocalTime(String str){
        ZonedDateTime zdt = ZonedDateTime
                .of(LocalDateTime
                        .parse(str,dtf), UTCTimeZone);
        return zdt.withZoneSameInstant(currentTimeZone).format(getDtf());
    }

    public ZonedDateTime zonedDateTimeToLocal(ZonedDateTime zdt){
        return zdt.withZoneSameInstant(currentTimeZone);
    }

    public String zonedDateTimeToUTCString(ZonedDateTime zdt){
        ZonedDateTime time = zdt.withZoneSameInstant(UTCTimeZone);
        return time.format(dtf);
    }

    public String dateTimePickersToUtc(LocalDate ld, String str){

        String d = str.substring(0,str.indexOf("M")+1);
        LocalTime lt = LocalTime.from(DateTimeFormatter.ofPattern("h:mma").parse(d));
        ZonedDateTime zdt = ZonedDateTime.of(ld,lt,this.currentTimeZone);
        return zdt.withZoneSameInstant(UTCTimeZone).format(dtf);

    }

    public String dateTimePickersToUtc(LocalDate ld, LocalTime lt){
        ZonedDateTime zdt = ZonedDateTime.of(ld,lt,this.currentTimeZone);
        return zdt.withZoneSameInstant(UTCTimeZone).format(dtf);

    }


    private static String hackTheDot(String s){
        String d = s.substring(0,s.indexOf("."));
        return d;
    }

    public DateTimeFormatter getDtf() {
        return dtf;
    }
}
