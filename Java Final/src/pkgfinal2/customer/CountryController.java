package pkgfinal2.customer;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/7/2017.
 */
public class CountryController {

    private Country myCountry;
    private int highestCountryId;
    private int existingCountryId;

    public CountryController(String str){

        if(!countryExists(str)){
            this.highestCountryId = getHighestCountryId();
            addCountryToBase(str);
        }
        else{
            this.existingCountryId = -1;
        }
        //this.myCountry = new Country();
        //this.myCountry.setCountryId(getNextCountryId());

    }

    private int getHighestCountryId(){
        try{
            Statement stmnt= MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = stmnt.executeQuery("select max(countryId) as maxId from country");
            if(rs.next()){
                return rs.getInt("maxId");
            }

        }
        catch (SQLException e){
            e.getMessage();
        }
        return -1;
    }

    private void addCountryToBase(String countryName){

        try {
            String sql = "insert into country (countryId,country,createDate,createdBy,lastUpdate,lastUpdateBy) values "
                    + " (?,?,?,?,?,?)";
            PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);
            ps.setInt(1,(this.highestCountryId+1));
            ps.setString(2,countryName.trim());
            ps.setString(3, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            ps.setString(4, MainScreen.getAuthUser());
            ps.setString(5,"");
            ps.setString(6,"");

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private boolean countryExists(String str){

        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = stmnt.executeQuery("Select countryId, country from U03PfE.country where country = '" + str.trim() + "'");
            if(rs.next()){
                int x = rs.getInt("countryId");
                stmnt.close();
                MySQLDatabase.closeConnection();
                this.existingCountryId=x;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int getNextCountryId(){

        try {
            PreparedStatement smnt2 = MySQLDatabase.getMySQLConnection().prepareStatement("insert into " +
                    "country (countryid,country,createdBy,createDate,lastUpdate,lastUpdateBy) values(?,?,?,?,?,?)");
            //smnt2.setInt();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            MySQLDatabase.closeConnection();
        }
        return -1;
    }
}
