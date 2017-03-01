package pkgfinal2.appointments;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pkgfinal2.Displayable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class ShowByWeek implements Displayable{
    int increment = 0;
    @Override
    public void display(){
        AppointmentViewController avc = new AppointmentViewController();
        Stage window = new Stage();
        window.setTitle("Show Appointments By Week");
        
        GridPane hbButtons = new GridPane();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setHgap(70);
        Button btnPrevious = new Button("< Prev");
        Label lblWeek = new Label();
        lblWeek.setText("Week of: " + avc.getStartOfWeek(LocalDate.now()).format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        Button btnNext = new Button("Next >");

        LocalDate today = LocalDate.now();
        hbButtons.add(btnPrevious, 0, 0);
        
        hbButtons.add(lblWeek, 1, 0);
        hbButtons.add(btnNext, 2, 0);
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.prefWidthProperty().bind(window.widthProperty());
        
        TableView tvAppointments = new TableView();
        TableColumn colCustName = new TableColumn("Name");
        TableColumn colApptDate = new TableColumn("Date");
        TableColumn colStartTime = new TableColumn("Start");
        TableColumn colEndTime = new TableColumn("End");
        TableColumn colDescription = new TableColumn("Description");
        TableColumn colLocation = new TableColumn("Location");

        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colApptDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));

        tvAppointments.setItems(avc.getApptByWeek(today));
        
        tvAppointments.getColumns().addAll(colCustName,colApptDate,colStartTime,colEndTime,colDescription,colLocation);

        //**********************************************
        //**                EVENT HANDLERS            **
        //**********************************************

        btnNext.setOnAction(event -> {
            LocalDate startOfWeek = avc.getStartOfWeek(today);
            increment = increment + 7;
            startOfWeek = startOfWeek.plusDays(increment);
            tvAppointments.setItems(avc.getApptByWeek(startOfWeek));
            lblWeek.setText("Week of: " + startOfWeek.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            tvAppointments.refresh();
        });


        
        GridPane p = new GridPane();
        
        p.add(hbButtons, 0, 0);
        p.add(tvAppointments, 0, 1);
        
        Scene scene = new Scene(p);
        window.setScene(scene);
        window.show();
    }
}
