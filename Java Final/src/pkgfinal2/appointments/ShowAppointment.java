package pkgfinal2.appointments;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pkgfinal2.MainScreen;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ecogle on 2/14/2017.
 */
public class ShowAppointment {
    // MAP of controls
    private Map<String,TextField> txtControls = new HashMap<>();
    private Appointment apt;

    public ShowAppointment(Appointment a){
        super();
        this.apt = a;
    }

    public ShowAppointment(){
        super();
    }
    public void display(){
        //appointment gui
        Stage window = new Stage();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("h:mma");
        BorderPane layout = new BorderPane();
        DatePicker dtpStart = new DatePicker();
        DatePicker dtpEnd = new DatePicker();
        ComboBox cboStartTime = new ComboBox();
        ComboBox cboEndTime = new ComboBox();
        Button btnEdit = new Button("Edit");
        Button btnDelete = new Button("Delete");
        CheckBox chkEdit = new CheckBox("Edit");

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

        //populate fields
        txtControls.get("title").setText(this.apt.getTitle());
        txtControls.get("description").setText(this.apt.getDescription());
        txtControls.get("location").setText(this.apt.getLocation());
        txtControls.get("contact").setText(this.apt.getContact());
        txtControls.get("url").setText(this.apt.getUrl());

        GridPane gp = new GridPane();
        HBox hbButtons = new HBox();
        hbButtons.setAlignment(Pos.CENTER);
        hbButtons.setSpacing(20);
        hbButtons.getChildren().addAll(btnEdit,btnDelete);

        gp.setPadding(new Insets(8));

        gp.setHgap(8);
        gp.setVgap(10);
        StackPane sp = new StackPane();

        gp.add(chkEdit,0,0,3,1);
        gp.add(lblCustomerName,0,1,3,1);    ;       ;
        gp.add(lblTitle,0,5); gp.add(txtControls.get("title"),1,5);
        gp.add(lblDescription,0,6); gp.add(txtControls.get("description"),1,6);
        gp.add(lblLocation,0,7); gp.add(txtControls.get("location"),1,7);
        gp.add(lblContact,0,8); gp.add(txtControls.get("contact"),1,8);
        gp.add(lblUrl,0,9); gp.add(txtControls.get("url"),1,9);
        gp.add(hbButtons,0,10,3,1);

        // disable the text controls for editing
        txtControls.forEach((x,y)-> {
            y.setEditable(false);
            y.setStyle("-fx-background-color:lightgray");
        });
        btnDelete.setVisible(false);
        btnEdit.setVisible(false);


        //************************************************
        //                EVENT HANDLERS                **
        //************************************************

        chkEdit.setOnAction(event -> {
            if(chkEdit.isSelected()){
                txtControls.forEach((x,y)-> {
                    y.setEditable(true);
                    y.setStyle("-fx-background-color:white; -fx-border-width:1; -fx-border-style:solid; -fx-border-color:gray; -fx-border-radius:3");
                });
                btnDelete.setVisible(true);
                btnEdit.setVisible(true);
            }
            else {
                txtControls.forEach((x,y)-> {
                    y.setEditable(false);
                    y.setStyle("-fx-background-color:lightgray");
                });
                btnDelete.setVisible(false);
                btnEdit.setVisible(false);
            }
        });

        layout.setCenter(gp);
        Scene scene = new Scene(layout,450,350);
        window.setScene(scene);
        window.showAndWait();
    }
}
