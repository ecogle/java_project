package pkgfinal2;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javafx.scene.control.Alert;

/**
 * Created by ecogle on 2/4/2017.
 */

public class MySQLDatabase {

    private static Connection conn;

    public static DataSource getMySQLDataSource(){
        Properties mySQL = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mds = null;

        try{
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/db.properties");
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
        catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(){

        try {
            conn.close();
            System.out.println("Connection CLOSED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
