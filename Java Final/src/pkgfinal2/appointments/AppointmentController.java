package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/12/2017.
 */
public class AppointmentController {

    public static ObservableList populateTimeSelection(){
        ObservableList<String> filler = FXCollections.observableArrayList();
        ZonedDateTime zdtTimes = ZonedDateTime.of(2016,01,01,06,00,00,00, ZoneId.systemDefault());
        ZonedDateTime zdtTimesEnd = zdtTimes.plusMinutes(750);

        while (zdtTimes.isBefore(zdtTimesEnd)){
            filler.add(zdtTimes.format(DateTimeFormatter.ofPattern("hh:mma z")));
            zdtTimes = zdtTimes.plusMinutes(30);
        }
        return filler;
    }
}
