package pkgfinal2.customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import pkgfinal2.Displayable;
import pkgfinal2.MySQLDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Adds a country to the database
 */
public class AddCountry implements Displayable {
    Stage window;
    private static int countryID;

    @Override
    public void display() {
        window = new Stage();
        window.setTitle("Add Country");

        GridPane layout = new GridPane();
        //****** Labels ***********
        Label lblCountryName = new Label("Country: ");

        //****** Textfield ********
        TextField txtCountry = new TextField();
        txtCountry.setPromptText("Country");

        //********* Buttons *********
        Button btnAdd = new Button("Add country");
        Button btnClear = new Button("Clear");


        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(20, 0, 20, 20));
        layout.add(lblCountryName, 0, 0);
        layout.add(txtCountry, 1, 0);
        Separator sep = new Separator();
        layout.add(sep, 0, 2, 2, 1);

        layout.add(btnClear, 0, 3);

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER_RIGHT);
        hb.getChildren().add(btnAdd);
        layout.add(hb, 1, 3);

        // Clear Button actions
        btnClear.setOnAction(e -> {
            txtCountry.setText("");
            txtCountry.requestFocus();
        });

        btnAdd.setOnAction(e -> {
            try {
                LocalDate nowDate = LocalDate.now();
                String date = nowDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
                Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
                String sql = "insert into U03PfE.country (countryId,country,createDate) values (" + getNextId() + ",'"+txtCountry.getText() + "'," + "'"+date+"')";
                stmnt.execute(sql);
            } catch (SQLException f) {
                System.out.println("There has been an error with the SQL statement");
            }
            window.close();
        });

        Scene scene= new Scene(layout,275,125);
        window.setScene(scene);
        window.show();
    }


    private static int getNextId() {
        int c = 0;
        int x = -1;
        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            String sql = "Select count(country) as myInt from country;";
            ResultSet rs = stmnt.executeQuery(sql);
            if(rs.next()){
                x = rs.getInt("myInt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ++x;
    }
}


