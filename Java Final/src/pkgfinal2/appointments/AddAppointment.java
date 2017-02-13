package pkgfinal2.appointments;

//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.MainScreen;
//import pkgfinal2.customer.CompleteCustomer;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
//import java.util.stream.Stream;

/**
 * Created by ecogle on 2/12/2017.
 */
public class AddAppointment implements Displayable {

    // MAP of controls
    private Map<String,TextField> txtControls = new HashMap<>();

    @Override
    public void display(){
        Stage window = new Stage();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("h:mma");
        BorderPane layout = new BorderPane();
        DatePicker dtpStart = new DatePicker();
        DatePicker dtpEnd = new DatePicker();
        ComboBox cboStartTime = new ComboBox();
        ComboBox cboEndTime = new ComboBox();
        Button btnClear = new Button("Clear");
        Button btnSubmit = new Button("Submit");

        window.setTitle("Add Appointment for " + MainScreen.getSelectedCustomer().getCustomerName());
        layout.setPadding(new Insets(8));
        //Labels and TextFields
        Label lblTitle = new Label("Title");
        Label lblDescription = new Label("Description");
        Label lblLocation = new Label("Location");
        Label lblContact = new Label("Contact");
        Label lblUrl = new Label("URL");
        Label lblStart = new Label("Start");
        Label lblEnd = new Label("End");
        Label lblCustomerName = new Label(MainScreen.getSelectedCustomer().getCustomerName());
        lblCustomerName.setStyle("-fx-font-family:'sans-serif'; -fx-font-size:14pt ");

        Label lblStartDate = new Label("Start Date");
        Label lblEndDate = new Label("End Date");
        //Label lblTime = new Label("Time");
        Label lblOutputTime = new Label();

        // MAP of TextFields
        txtControls.put("title",new TextField());
        txtControls.put("description",new TextField());
        txtControls.put("location",new TextField());
        txtControls.put("contact",new TextField());
        txtControls.put("url",new TextField());




        // populate the time boxes
        cboStartTime.setItems(AppointmentController.populateTimeSelection());
        cboEndTime.setItems(AppointmentController.populateTimeSelection());
        cboStartTime.setPromptText("Start Time");
        cboEndTime.setPromptText("End Time");
        cboStartTime.setMaxWidth(130);
        cboEndTime.setMaxWidth(130);
        dtpStart.setMaxWidth(100);
        dtpEnd.setMaxWidth(100);

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(8));
        gp.setHgap(8);
        gp.setVgap(5);
        StackPane sp = new StackPane();

        sp.setPrefWidth(50);

        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        HBox hbButtons = new HBox();

        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(20);
        hbButtons.getChildren().addAll(btnClear,btnSubmit);
        hb1.setAlignment(Pos.CENTER_LEFT);
        hb1.setSpacing(20);
        hb2.setAlignment(Pos.CENTER_LEFT);
        hb2.setSpacing(20);
        hb1.getChildren().addAll(lblStartDate,dtpStart,cboStartTime);
        hb2.getChildren().addAll(lblEndDate,dtpEnd,cboEndTime);

        //Put the buttons in the gridpane
        gp.add(lblCustomerName,0,0,3,1);    ;       ;
        gp.add(hb1,0,1,3,1);
        gp.add(hb2,0,2,3,1);
        gp.add(lblTitle,0,3); gp.add(txtControls.get("title"),1,3);
        gp.add(lblDescription,0,4); gp.add(txtControls.get("description"),1,4);
        gp.add(lblLocation,0,5); gp.add(txtControls.get("location"),1,5);
        gp.add(lblContact,0,6); gp.add(txtControls.get("contact"),1,6);
        gp.add(lblUrl,0,7); gp.add(txtControls.get("url"),1,7);
        gp.add(hbButtons,0,9,3,1);



        layout.setCenter(gp);
        layout.setLeft(sp);

        // todo check for valid date
        cboStartTime.setOnAction(event -> {
            //todo check for valid times
            String s = cboStartTime.getSelectionModel().getSelectedItem().toString();
            LocalTime t = AppointmentController.parseTime(s);
            lblOutputTime.setText(t.format(f));
        });

        btnSubmit.setOnAction(event -> {
            Appointment apt = new AppointmentBuilder().setAppointmentId(AppointmentController.getHighestAppointmentId())
                    .setContact(txtControls.get("contact").getText())
                    .setTitle(txtControls.get("title").getText())
                    .setDescription(txtControls.get("description").getText())
                    .setLocation(txtControls.get("location").getText())
                    .setUrl(txtControls.get("url").getText())
                    //ZonedDateTime
                    .setStart(ZonedDateTime.of(dtpStart.getValue(),AppointmentController.parseTime(cboStartTime.getSelectionModel().getSelectedItem().toString()),ZoneId.systemDefault()))
                    .setEnd(ZonedDateTime.of(dtpEnd.getValue(),AppointmentController.parseTime(cboEndTime.getSelectionModel().getSelectedItem().toString()),ZoneId.systemDefault()))
                    .setFkCustomerId(MainScreen.getSelectedCustomer().getCustomerId())
                    .build();

            AppointmentController ac = new AppointmentController(apt);

            ac.addToDatabase();

        });


        Scene scene = new Scene(layout,600,475);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void main(String[] args){
        //ZoneId.getAvailableZoneIds().stream().filter(e-> e.contains("America")).sorted().forEach(System.out::println);

        String time = "10:45:32AM CST";
        System.out.println(AppointmentController.parseTime(time).format(DateTimeFormatter.ofPattern("h:mma")));


    }
}
