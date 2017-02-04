package pkgfinal2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ecogle on 2/4/2017.
 */
public class UserList {

    public void display(){
        List<User> userList;
        Stage window = new Stage();
        window.setTitle("User list");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);

        TableView tblView = new TableView();
        TableColumn colUsername = new TableColumn("User Name");
        TableColumn colActive = new TableColumn("Active");
        TableColumn colCreatedBy = new TableColumn("Created By");

        tblView.getColumns().addAll(colUsername,colActive,colCreatedBy);


        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createBy"));
        tblView.setItems(FXCollections.observableArrayList(getUserList()));
        layout.add(tblView,0,0);
        Scene scene = new Scene(layout,250,500);
        window.setScene(scene);
        window.showAndWait();
    }

    private static List<User> getUserList(){
        Statement ps = null;
        List<User> userList = new ArrayList<User>();
        try {
            ps = MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = ps.executeQuery("Select username, active, createBy from user");
            while(rs.next()){
                userList.add(new User(rs.getString("username"),rs.getBoolean("active"),rs.getString("createBy")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(userList);
        return userList;

    }
}
