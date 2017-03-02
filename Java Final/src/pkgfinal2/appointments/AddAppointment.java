package pkgfinal2.appointments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.MainScreen;
import pkgfinal2.appointments.reminder.Reminder;
import pkgfinal2.appointments.reminder.ReminderBuilder;
import pkgfinal2.appointments.reminder.ReminderController;
import pkgfinal2.messages.MessageFactory;

import javax.swing.text.LabelView;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ecogle on 2/12/2017.
 */
public class AddAppointment implements Displayable {

    // MAP of controls
    private Map<String,TextField> txtControls = new HashMap<>();
    private Appointment apt;
    private Reminder reminder;

    @Override
    public void display(){

        //appointment gui
        Stage window = new Stage();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("h:mma");
        BorderPane layout = new BorderPane();
        DatePicker dtpStart = new DatePicker();
        ComboBox<String> cboStartTime = new ComboBox();
        ComboBox<String> cboEndTime = new ComboBox();
        Button btnClear = new Button("Clear");
        Button btnSubmit = new Button("Submit");
        ComboBox<String> cboDescription = new ComboBox<>();
        cboDescription.setItems(FXCollections.observableArrayList("Physical","Illness","Checkup"));

        //reminder gui

        //CheckBox chkReminder = new CheckBox("Add Reminder");
        //DatePicker dtpReminderDate = new DatePicker();
        //Label lblReminderDate = new Label("Reminder date");
//        Label lblIncrementTypeDescription = new Label("Inc. Description");
//        lblIncrementTypeDescription.setAlignment(Pos.TOP_LEFT);
//        TextArea txtaDescription = new TextArea();
//        txtaDescription.setPrefColumnCount(5);
//        txtaDescription.setPrefWidth(100d);
//        txtaDescription.setWrapText(true);
        Label lblReminderType = new Label("Type");
        Label lblReminderText = new Label("Reminder Text:");
        TextField txtReminderText = new TextField();
        ChoiceBox cbSnoozeInc = new ChoiceBox();
        cbSnoozeInc.setItems(FXCollections.observableArrayList("5 min","10 min","15 min"));
        Label lblSnoozeInc = new Label("Snooze Inc.");
        ComboBox cboReminderType = new ComboBox();
        cboReminderType.setPromptText("Reminder Type");
        cboReminderType.setItems(FXCollections.observableArrayList("Illness","Physical","Procedure"));



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

        Label lblStartDate = new Label("Appt. Date");
        lblStartDate.setPrefWidth(80);
        Label lblOutputTime = new Label();

        // MAP of TextFields
        txtControls.put("title",new TextField());
        //txtControls.put("description",new TextField());
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
        dtpStart.setPrefWidth(130);
        
        GridPane gp = new GridPane();

        gp.setPadding(new Insets(8));
        gp.setHgap(8);
        gp.setVgap(5);
        StackPane sp = new StackPane();

        // reminder GUI
        GridPane gp2 = new GridPane();
        gp2.setMinWidth(245);
        gp2.setPadding(new Insets(8));
        gp2.setHgap(8);
        gp2.setVgap(5);

        lblReminderType.setVisible(true);
        cboReminderType.setVisible(true);
        gp2.add(lblReminderText,0,0);gp2.add(txtReminderText,1,0);
        gp2.add(lblSnoozeInc,0,1); gp2.add(cbSnoozeInc,1,1);
        sp.setPrefWidth(50);

        HBox hbButtons = new HBox();

        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(20);
        hbButtons.getChildren().addAll(btnClear,btnSubmit);
        
        //Put the buttons in the gridpane
        gp.add(lblCustomerName,0,0,3,1);    ;       ;
        gp.add(lblStartDate, 0, 1); gp.add(dtpStart, 1, 1);
        gp.add(cboStartTime, 0, 2); gp.add(cboEndTime, 1, 2);
        gp.add(lblTitle,0,3); gp.add(txtControls.get("title"),1,3);
        // todo change this to combobox
        gp.add(lblDescription,0,4); gp.add(cboDescription,1,4);
        gp.add(lblLocation,0,5); gp.add(txtControls.get("location"),1,5);
        gp.add(lblContact,0,6); gp.add(txtControls.get("contact"),1,6);
        gp.add(lblUrl,0,7); gp.add(txtControls.get("url"),1,7);
        gp.add(hbButtons,0,9,3,1);

        layout.setCenter(gp);
        layout.setLeft(sp);
        layout.setRight(gp2);

        //*************************************************
        //**              EVENT HANDLERS                 **
        //*************************************************

        TimeZoneController tzc = new TimeZoneController();

        btnSubmit.setOnAction(event -> {

            String t = cboStartTime.getSelectionModel().getSelectedItem().toString();

            apt = new AppointmentBuilder().setAppointmentId(AppointmentController.getHighestAppointmentId()+1)
                    .setContact(txtControls.get("contact").getText())
                    .setTitle(txtControls.get("title").getText())
                    .setDescription(cboDescription.getSelectionModel().getSelectedItem())
                    .setLocation(txtControls.get("location").getText())
                    .setUrl(txtControls.get("url").getText())
                    //ZonedDateTime
                    .setStart(tzc.dateTimePickersToUtc(dtpStart.getValue(),cboStartTime.getSelectionModel().getSelectedItem().toString()))
                    .setEnd(tzc.dateTimePickersToUtc(dtpStart.getValue(),cboEndTime.getSelectionModel().getSelectedItem().toString()))
                    .setFkCustomerId(MainScreen.getSelectedCustomer().getCustomerId())
                    .build();
            reminder = new ReminderBuilder()
                    .setFkAppointmentId(apt.getAppointmentId())
                    .setReminderDate(apt.getStart())
                    .setFkSnoozeIncrementTypeId(0)
                    .setReminderCol(" ") //textBox
                    .setReminderSnoozeIncrement(0) //comboBox
                    .setCreatedBy(MainScreen.getAuthUser())
                    .setCreateDate(apt.getStart())
                    .build();

            // pull all appointments for the Appt. Date
            ObservableList<Appointment> apptList = AppointmentController.getAppointmentListByDate(dtpStart.getValue().format(DateTimeFormatter.ofPattern("yyy-MM-dd")));

            // call the isConflicting on the appt
            boolean flag = false;

            if(apptList.isEmpty()){
                addToDatabase((a, b) -> {
                    new AppointmentController(a).addToDatabase();
                    new ReminderController(b).addReminderToDatabase(2, a.getAppointmentId());
                });
                window.close();
            }
            else{
                for(Appointment a : apptList){
                    if(Appointment.isConflicting(apt,a)){
                        flag=true;
                        break;
                    }
                }
                if(flag == false){
                    addToDatabase((a, b) -> {
                        new AppointmentController(a).addToDatabase();
                        new ReminderController(b).addReminderToDatabase(2, a.getAppointmentId());
                    });
                    window.close();
                }
                else{
                    new MessageFactory().showMessage(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Conflicting appointments");
                        alert.showAndWait();
                    });
                }
            }
        });

        /*chkReminder.setOnAction(event -> {
            if(chkReminder.isSelected()){
                lblReminderDate.setVisible(true);
                dtpReminderDate.setVisible(true);

                lblIncrementTypeDescription.setVisible(true);
                txtaDescription.setVisible(true);
            }
            else{
                dtpReminderDate.getEditor().clear();
                cboReminderType.getSelectionModel().clearSelection();

                lblReminderDate.setVisible(false);
                dtpReminderDate.setVisible(false);
                lblReminderType.setVisible(false);
                cboReminderType.setVisible(false);
                lblIncrementTypeDescription.setVisible(false);
                txtaDescription.setVisible(false);
            }
        });*/

        
        cboStartTime.setOnAction(event -> {
            cboEndTime.getSelectionModel().select(cboStartTime.getSelectionModel().getSelectedIndex());
        });

        //*************************************************
        //**            END EVENT HANDLERS               **
        //*************************************************

        layout.setCenter(gp);
        Scene scene = new Scene(layout,700,350);
        window.setScene(scene);
        window.showAndWait();

    }

    private void addToDatabase(Schedulable d){
        d.scheduleMe(apt,reminder);
    }

    private boolean dateIsValid(ZonedDateTime start, ZonedDateTime end){

        //if end time is before start time -> false
        if (!start.isBefore(end)){
            return false;
        }
        //if on weekend -> false
        else if (start.getDayOfWeek().equals(DayOfWeek.SATURDAY) || start.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            return false;
        }

        else if(end.toLocalDate().isAfter(start.toLocalDate())){
            new MessageFactory().showMessage(()->{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Appointment can't span more than one day");
                a.showAndWait();
            });
            return false;
        }
        else{
            return true;
        }
    }
}
