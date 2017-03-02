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
        return zdt.withZoneSameInstant(getUTCTimeZone()).format(getDtf());
    }

    public String stringToLocalTime(String str){
        ZonedDateTime zdt = ZonedDateTime
                .of(LocalDateTime
                        .parse(str,dtf), getUTCTimeZone());
        return zdt.withZoneSameInstant(currentTimeZone).format(getDtf());
    }

    public ZonedDateTime zonedDateTimeToLocal(ZonedDateTime zdt){
        return zdt.withZoneSameInstant(currentTimeZone);
    }

    public String zonedDateTimeToUTCString(ZonedDateTime zdt){
        ZonedDateTime time = zdt.withZoneSameInstant(getUTCTimeZone());
        return time.format(dtf);
    }

    public String dateTimePickersToUtc(LocalDate ld, String str){

        LocalTime lt = LocalTime.from(DateTimeFormatter.ofPattern("h:mma z").parse(str));
        ZonedDateTime zdt = ZonedDateTime.of(ld,lt,this.currentTimeZone);
        return zdt.withZoneSameInstant(getUTCTimeZone()).format(dtf);

    }

    public String dateTimePickersToUtc(LocalDate ld, LocalTime lt){
        ZonedDateTime zdt = ZonedDateTime.of(ld,lt,this.currentTimeZone);
        return zdt.withZoneSameInstant(getUTCTimeZone()).format(dtf);

    }
    public String ldtToZdtStringUTC(LocalDateTime ldt){
        ZonedDateTime zdt = ZonedDateTime.of(ldt,this.currentTimeZone);
        zdt = zdt.withZoneSameInstant(this.getUTCTimeZone());
        return zdt.format(this.getDtf());
    }

    public String stringZonedLocalTimeFromBase(String s){
        LocalDateTime ldt = LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n"));
        ZonedDateTime zdt = ZonedDateTime.of(ldt, this.getUTCTimeZone());
        return zdt.withZoneSameInstant(this.currentTimeZone).format(dtf);
    }


    private static String hackTheDot(String s){
        String d = s.substring(0,s.indexOf("."));
        return d;
    }

    public ZoneId getCurrentTimeZone(){
        return this.currentTimeZone;
    }

    public ZonedDateTime zdtFromBase(String s){
        LocalDateTime ldt = LocalDateTime.parse(s,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n"));
        ZonedDateTime zdt = ZonedDateTime.of(ldt,this.UTCTimeZone);
        return zdt;
    }

    public DateTimeFormatter getDtf() {
        return dtf;
    }

    public ZoneId getUTCTimeZone() {
        return UTCTimeZone;
    }
}
