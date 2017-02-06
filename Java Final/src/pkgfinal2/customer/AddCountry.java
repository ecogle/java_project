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
        //layout.setGridLinesVisible(true);
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
                PreparedStatement pstmnt = MySQLDatabase.getMySQLConnection().prepareStatement("insert into U03PfE.country (countryId,country,createDate)" +
                        "values (?,?,?)");
                pstmnt.setInt(1, getNextId());
                pstmnt.setString(2, txtCountry.getText());
                pstmnt.setString(3, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")));
                pstmnt.execute();
            } catch (SQLException f) {
                f.printStackTrace();
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


