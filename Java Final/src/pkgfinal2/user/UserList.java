package pkgfinal2.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import pkgfinal2.Displayable;
import pkgfinal2.MySQLDatabase;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by ecogle on 2/4/2017.
 */
public class UserList implements Displayable {
    private ObservableList<User> users = FXCollections.observableArrayList();

    @Override
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
        TableColumn colLastUpdate = new TableColumn("Last Update");
        TableColumn colUserId = new TableColumn("User Id");
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        colCreatedBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        colLastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        tblView.getColumns().addAll(colUserId,colUsername,colActive,colCreatedBy,colLastUpdate);

        buildUsers();
        tblView.setItems(users);

        layout.add(tblView,0,0);
        Scene scene = new Scene(layout,325,500);
        window.setScene(scene);
        window.showAndWait();
    }

    private void buildUsers(){
        Statement ps = null;
        try(Connection conn = MySQLDatabase.getMySQLConnection()) {

            ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("Select userId,username, active, createBy,lastUpdate from U03Pfe.user");

            /*
                List userList = Arrays.asList(rs)
                userList.stream(). ...
                Try to use a stream to copy data from resultset into the ObservableList
             */
            while(rs.next()){
                User tempUser = new User();
                tempUser.setUserId(rs.getInt("userId"));
                tempUser.setCreatedBy(rs.getString("createBy"));
                tempUser.setUserNameString(rs.getString("username"));
                tempUser.setActiveBoolean(rs.getBoolean("active"));
                tempUser.setLastUpdate(rs.getString("lastUpdate"));
                this.users.add(tempUser);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
