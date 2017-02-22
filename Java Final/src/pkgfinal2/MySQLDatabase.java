package pkgfinal2;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import pkgfinal2.messages.MessageFactory;

/**
 * Created by ecogle on 2/4/2017.
 */

public class MySQLDatabase implements AutoCloseable{

    private static Connection conn;

    public static DataSource getMySQLDataSource(){
        Properties mySQL = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mds = null;
        System.out.println(System.getProperty("user.dir"));
        try{
            fis = new FileInputStream(System.getProperty("user.dir") + "Java Final/src/db.properties");
            mySQL.load(fis);
            mds = new MysqlDataSource();
            mds.setURL(mySQL.getProperty("MYSQL_DB_URL"));
            mds.setUser(mySQL.getProperty("MYSQL_DB_USERNAME"));
            mds.setPassword("MYSQL_DB_PASSWORD");
        }
        catch (IOException e){

        }
        return mds;
    }

    public static Connection getMySQLConnection(){

        try{
            conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03PfE","U03PfE","53688042834");
        }
        catch (CommunicationsException r){
            new MessageFactory().showMessage(()->{
                Alert h = new Alert(Alert.AlertType.ERROR);
                h.setHeaderText("Communication error");
                h.setContentText("There has been a communication error. Please try connecting to the internet and launch again");
                h.showAndWait();
            });
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }



    @Override
    public void close()  {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
