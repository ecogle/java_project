package pkgfinal2.customer;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javafx.scene.control.Alert;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This is the controller class for the country aspect of the database.
 * Created by ecogle on 2/7/2017.
 */
public class CountryController {

    private Country myCountry;
    private int highestCountryId;
    public int existingCountryId;
    public boolean newCountryAdded;

    /**
     * This is the brains of the Country class
     * everything is done in the constructor. All you need is the text from
     * the textfield.
     * @param str text from the txtCountry control
     */
    public CountryController(String str){
        
        /*
            if the country does not exist, get the max value of the existing
            primary keys then add the new value to the database. A new myCounty
            object is created with the values.
        
            if the country does exist, a new myCountry object is created with
            the existing values.
        */
        if(!countryExists(str)){
            this.highestCountryId = getHighestCountryId();
            addCountryToBase(str);
        }
        else{
            myCountry.setCountryId(existingCountryId);
        }
    }

    /*
        returns the max value of the primary keys of the country table
        
        -- could probably return void and just set the private property
    */
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

    /**
     * adds the country to the database and creates a new myCountry object
     * 
     * @param countryName the text from the textField
     */
    public void addCountryToBase(String countryName){

        try {
            String sql = "insert into country (countryId,country,createDate,createdBy,lastUpdate,lastUpdateBy) values "
                    + " (?,?,?,?,?,?)";
            PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);
            String nowDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            String nowTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.myCountry = new Country();
            this.myCountry.setCountryName(countryName.trim());
            this.myCountry.setCountryId(this.highestCountryId+1);
            this.myCountry.setCreateDate(nowDate + " " + nowTime);
            this.myCountry.setCreatedBy(MainScreen.getAuthUser());
            this.myCountry.setLastUpdate(nowDate + " " + nowTime);
            this.myCountry.setLastUpdateBy(MainScreen.getAuthUser());
            ps.setInt(1,this.myCountry.getCountryId());
            ps.setString(2,this.myCountry.getCountryName());
            ps.setString(3, this.myCountry.getCreateDate());
            ps.setString(4, this.myCountry.getCreatedBy());
            ps.setString(5, this.myCountry.getLastUpdate());
            ps.setString(6, this.myCountry.getLastUpdateBy());
            System.out.println(ps);
            ps.execute();
            Alert alrt = new Alert(Alert.AlertType.INFORMATION,"New country Added to database");
            alrt.showAndWait();
            this.newCountryAdded=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Determines if the country exists
     * 
     * @param str text from the country textfield
     * @return 
     */
    private boolean countryExists(String str){

        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = stmnt.executeQuery("Select * from U03PfE.country where country = '" + str.trim() + "'");
            if(rs.next()){
                //int x = rs.getInt("countryId");
                this.myCountry = new Country();
                this.myCountry.setCountryId(rs.getInt("countryId"));
                this.myCountry.setCountryName(rs.getString("country"));
                this.myCountry.setCreateDate(rs.getString("createDate"));
                this.myCountry.setCreatedBy(rs.getString("createdBy"));
                this.myCountry.setLastUpdate(rs.getString("lastUpdate"));
                this.myCountry.setLastUpdateBy(rs.getString("lastUpdateBy"));
                stmnt.close();
                MySQLDatabase.closeConnection();
                //this.existingCountryId=x;
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    private int getNextCountryId(){
//        try {
//            PreparedStatement smnt2 = MySQLDatabase.getMySQLConnection().prepareStatement("insert into " +
//                    "country (countryid,country,createdBy,createDate,lastUpdate,lastUpdateBy) values(?,?,?,?,?,?)");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            MySQLDatabase.closeConnection();
//        }
//        return -1;
//    }

    public Country getMyCountry (){
        Country c = new Country();
        c = this.myCountry;
        return c;
    }
}
