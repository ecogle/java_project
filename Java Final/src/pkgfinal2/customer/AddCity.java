package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.audit.Audit;

/**
 * Created by ecogle on 2/6/2017.
 */
public class AddCity extends Audit implements Displayable {
    Stage window;
    @Override
    public void display(){
        window = new Stage();
        window.setTitle("Add City");

        GridPane layout = new GridPane();
        //****** Labels ***********
        Label lblCityName = new Label("City: ");

        //****** Textfield ********
        TextField txtCity = new TextField();
        txtCity.setPromptText("Country");

        //********* Buttons *********
        Button btnAdd = new Button("Add city");
        Button btnClear = new Button("Clear");


        layout.setHgap(10);
        layout.setVgap(10);
        //layout.setGridLinesVisible(true);
        layout.setPadding(new Insets(20,0,20,20));
        layout.add(lblCityName,0,0);
        layout.add(txtCity,1,0);
        Separator sep = new Separator();
        layout.add(sep,0,2,2,1);

        layout.add(btnClear,0,3);

        HBox hb=new HBox();
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.getChildren().add(btnAdd);
        layout.add(hb,1,3);

        // Clear Button actions
        btnClear.setOnAction(e->{
            txtCity.setText("");
            txtCity.requestFocus();
        });

        btnAdd.setOnAction(e->{
            Alert alrt = new Alert(Alert.AlertType.INFORMATION);
            alrt.setHeaderText("Coming Soon!");
            alrt.setTitle("Under development...");

            alrt.setContentText("Providing functionality for error checking input\n" +
                    "and inserting the value into the database");
            alrt.showAndWait();
            window.close();
        });



        Scene scene= new Scene(layout,275,125);
        window.setScene(scene);
        window.show();


    }

    public static void main(String[] args) {
        AddCountry m = new AddCountry();
        m.display();
    }
}
