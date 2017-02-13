package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pkgfinal2.Displayable;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by ecogle on 2/12/2017.
 */
public class AddAppointment implements Displayable {

    // MAP of controls
    private Map<String,TextField> txtControls = new HashMap<>();

    @Override
    public void display(){
        Stage window = new Stage();
        window.setTitle("Add Appointment");

        BorderPane layout = new BorderPane();

        //Labels and TextFields
        Label lblTitle = new Label("Title");
        Label lblDescription = new Label("Description");
        Label lblLocation = new Label("Location");
        Label lblContact = new Label("Contact");
        Label lblUrl = new Label("URL");
        Label lblStart = new Label("Start");
        Label lblEnd = new Label("End");
        Label lblTime = new Label();

        // MAP of TextFields
        txtControls.put("title",new TextField());
        txtControls.put("description",new TextField());
        txtControls.put("location",new TextField());
        txtControls.put("contact",new TextField());
        txtControls.put("url",new TextField());

        DatePicker dtpStart = new DatePicker();


        ComboBox cboStartTime = new ComboBox();

        // populate the time boxes


        cboStartTime.setItems(AppointmentController.populateTimeSelection());
        cboStartTime.setPromptText("Start Time");


        GridPane gp = new GridPane();
        StackPane sp = new StackPane();
        sp.setPrefWidth(50);
        gp.add(dtpStart,0,1);

        gp.add(cboStartTime,0,2);
        gp.add(lblTime,0,4);
        layout.setCenter(gp);
        layout.setLeft(sp);

        Scene scene = new Scene(layout,400,375);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void main(String[] args){
        //ZoneId.getAvailableZoneIds().stream().filter(e-> e.contains("America")).sorted().forEach(System.out::println);

        String time = "1:45:32AM";
        LocalTime lTime = LocalTime.from(DateTimeFormatter.ofPattern("h:mm:ssa").parse(time));
        System.out.println(lTime.format(DateTimeFormatter.ofPattern("h:mma")));

    }
}
