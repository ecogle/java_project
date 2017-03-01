package pkgfinal2.appointments;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pkgfinal2.Displayable;

public class ShowByWeek implements Displayable{
    
    @Override
    public void display(){
        Stage window = new Stage();
        window.setTitle("Show Appointments By Week");
        
        GridPane hbButtons = new GridPane();
        hbButtons.setPadding(new Insets(10));
        hbButtons.setHgap(70);
        Button btnPrevious = new Button("< Prev");
        Label lblWeek = new Label();
        Button btnNext = new Button("Next >");
        
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
        
        tvAppointments.getColumns().addAll(colCustName,colApptDate,colStartTime,colEndTime,colDescription,colLocation);
        
        GridPane p = new GridPane();
        
        p.add(hbButtons, 0, 0);
        p.add(tvAppointments, 0, 1);
        
        Scene scene = new Scene(p);
        window.setScene(scene);
        window.show();
    }
}
