package pkgfinal2.customer;

import javafx.scene.control.Alert;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Created by ecogle on 2/8/2017.
 */
public class AddressController {
    private Address myAddress;
    private int highestAddressId;
    public int existingAddressId;
    public boolean newAddressAdded;

    /**
     *
     * @param addressFields
     * @param cityId
     */
    public AddressController(Map<String,String> addressFields,int cityId){

        /*
            if the city does not exist, get the max value of the existing
            primary keys then add the new value to the database. A new myCounty
            object is created with the values.

            if the city does exist, a new myCity object is created with
            the existing values.
        
        */
        if(!addressExists(addressFields)){
            this.highestAddressId = getHighestAddressId();
            addAddressToBase(addressFields,cityId);
        }
        else{
            myAddress.setAddressId(existingAddressId);
        }
    }

    /*
        returns the max value of the primary keys of the city table

        -- could probably return void and just set the private property
    */
    private int getHighestAddressId(){
        try{
            Statement stmnt= MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = stmnt.executeQuery("select max(addressId) as maxId from address");
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
     *
     * @param addressFields
     * @param cityId
     */
    public void addAddressToBase(Map<String,String> addressFields, int cityId){

        try {
            String sql = "insert into address (addressId,address,address2, cityId,createDate,createdBy,lastUpdate,lastUpdateBy,phone,postalCode) values "
                    + " (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);
            String nowDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            String nowTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.myAddress = new Address();

            this.myAddress.setAddressId(this.highestAddressId+1);
            this.myAddress.setAddress(addressFields.get("address").trim());
            this.myAddress.setAddress2(addressFields.get("address2").trim());
            this.myAddress.setCityId(cityId);
            this.myAddress.setCreateDate(nowDate + " " + nowTime);
            this.myAddress.setCreatedBy(MainScreen.getAuthUser());
            this.myAddress.setLastUpdate(nowDate + " " + nowTime);
            this.myAddress.setLastUpdateBy(MainScreen.getAuthUser());
            this.myAddress.setPhoneNumber(addressFields.get("phone").trim());
            this.myAddress.setPostalCode(addressFields.get("postalCode").trim());

            ps.setInt(1,this.myAddress.getAddressId());
            ps.setString(2,this.myAddress.getAddress());
            ps.setString(3,this.myAddress.getAddress2());
            ps.setInt(4,this.myAddress.getCityId());
            ps.setString(5, this.myAddress.getCreateDate());
            ps.setString(6, this.myAddress.getCreatedBy());
            ps.setString(7, this.myAddress.getLastUpdate());
            ps.setString(8, this.myAddress.getLastUpdateBy());
            ps.setString(9,this.myAddress.getPhoneNumber());
            ps.setString(10,this.myAddress.getPostalCode());

            ps.execute();
            /*Alert alrt = new Alert(Alert.AlertType.INFORMATION,"New address Added to database");
            alrt.showAndWait();*/
            this.newAddressAdded=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param addressFields
     * @return
     */
    private boolean addressExists(Map<String,String> addressFields){

        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            String sql = "Select * from U03PfE.address where address = '" + addressFields.get("address") + "'";
            ResultSet rs = stmnt.executeQuery(sql);
            if(rs.next()){

                this.myAddress = new Address();

                this.myAddress.setAddressId(rs.getInt("addressId"));
                this.myAddress.setAddress(rs.getString("address"));
                this.myAddress.setAddress2(rs.getString("address2"));
                this.myAddress.setCityId(rs.getInt("cityId"));
                this.myAddress.setCreateDate(rs.getString("createDate"));
                this.myAddress.setCreatedBy(rs.getString("createdBy"));
                this.myAddress.setLastUpdate(rs.getString("lastUpdate"));
                this.myAddress.setLastUpdateBy(rs.getString("lastUpdateBy"));
                this.myAddress.setPhoneNumber(rs.getString("phone"));
                this.myAddress.setPostalCode(rs.getString("postalCode"));
                stmnt.close();
                MySQLDatabase.closeConnection();

                this.existingAddressId = this.myAddress.getAddressId();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns an instance of the myCountry object
     * @return
     */
    public Address getMyAddress (){
        Address c = new Address();
        c = this.myAddress;
        return c;
    }
}
