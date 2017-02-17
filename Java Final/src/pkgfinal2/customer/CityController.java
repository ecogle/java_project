package pkgfinal2.customer;

import javafx.scene.control.Alert;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import pkgfinal2.appointments.TimeZoneController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ecogle on 2/7/2017.
 */
public class CityController {
    private City myCity;
    private int highestCityId;
    public int existingCityId;
    public boolean newCityAdded;

    /**
     * This is the brains of the City class
     * everything is done in the constructor. All you need is the text from
     * the textfield.
     * @param str text from the txtCity control
     */
    public CityController(String str,int countryId){

        /*
            if the city does not exist, get the max value of the existing
            primary keys then add the new value to the database. A new myCounty
            object is created with the values.

            if the city does exist, a new myCity object is created with
            the existing values.
        */
        if(!cityExists(str)){
            this.highestCityId = getHighestCityId();
            addCityToBase(str,countryId);
        }
        else{
            myCity.setCityId(existingCityId);
        }
    }

    /*
        returns the max value of the primary keys of the city table

        -- could probably return void and just set the private property
    */
    private int getHighestCityId(){
        try(Statement stmnt= MySQLDatabase.getMySQLConnection().createStatement()){            
            ResultSet rs = stmnt.executeQuery("select max(cityId) as maxId from city");
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
     * adds the city to the database and creates a new myCity object
     *
     * @param cityName the text from the textField
     */
    public void addCityToBase(String cityName, int countryId){

        //todo fix the functionality of the add customer interface
        String sql = "insert into city (cityId,city,countryId,createDate,createdBy,lastUpdate,lastUpdateBy) values "
                    + " (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){

            TimeZoneController tzc = new TimeZoneController();
            this.myCity = new City();
            this.myCity.setCityName(cityName.trim());
            this.myCity.setCityId(this.highestCityId+1);
            this.myCity.setCountryId(countryId);
            this.myCity.setCreateDate(tzc.zonedDateTimeToUTCString(ZonedDateTime.now()));
            this.myCity.setCreatedBy(MainScreen.getAuthUser());
            this.myCity.setLastUpdate(tzc.zonedDateTimeToUTCString(ZonedDateTime.now()));
            this.myCity.setLastUpdateBy(MainScreen.getAuthUser());
            ps.setInt(1,this.myCity.getCityId());
            ps.setString(2,this.myCity.getCityName());
            ps.setInt(3,this.myCity.getCountryId());
            ps.setString(4, this.myCity.getCreateDate());
            ps.setString(5, this.myCity.getCreatedBy());
            ps.setString(6, this.myCity.getLastUpdate());
            ps.setString(7, this.myCity.getLastUpdateBy());

            ps.execute();
            
            this.newCityAdded=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Determines if the city exists
     *
     * @param str text from the city textfield
     * @return
     */
    private boolean cityExists(String str){

        try (Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement()){            
            ResultSet rs = stmnt.executeQuery("Select * from U03PfE.city where city = '" + str.trim() + "'");
            if(rs.next()){
                this.myCity = new City();
                this.myCity.setCityId(rs.getInt("cityId"));
                this.myCity.setCityName(rs.getString("city"));
                this.myCity.setCreateDate(rs.getString("createDate"));
                this.myCity.setCreatedBy(rs.getString("createdBy"));
                this.myCity.setLastUpdate(rs.getString("lastUpdate"));
                this.myCity.setLastUpdateBy(rs.getString("lastUpdateBy"));
                this.existingCityId = this.myCity.getCityId();
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Returns an instance of the myCountry object
     * @return
     */
    public City getMyCity (){
        City c = new City();
        c = this.myCity;
        return c;
    }
}


