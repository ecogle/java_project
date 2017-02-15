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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ecogle on 2/8/2017.
 */
public class AddressController {
    private Address myAddress; // Address property
    private int highestAddressId; // Highest address id (for generating the PK)
    public int existingAddressId; // current Address id
    public boolean newAddressAdded; // flag for address if added or not

    private CompleteCustomer completeCustomer;

    /**
     * Adds the Address to the database with the city ID
     * @param addressFields
     * @param cityId
     */
    public AddressController(Map<String,String> addressFields,int cityId){

        /*
            if the address does not exist, get the max value of the existing
            primary keys then add the new value to the database. A new myCounty
            object is created with the values.

            if the address does exist, a new myAddress object is created with
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

    /**
     * Constructor that instantiates the CompleteCustomer selected from the MainScreen
     * @param c CompleteCustomer from the MainScreen
     */
    public AddressController(CompleteCustomer c){
        this.completeCustomer = c;
    }

    /*
        returns the max value of the primary keys of the city table

        -- could probably return void and just set the private property
    */
    private int getHighestAddressId(){
        try(Statement stmnt= MySQLDatabase.getMySQLConnection().createStatement()){            
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
     * Adds address to the database
     * @param addressFields
     * @param cityId
     */
    public void addAddressToBase(Map<String,String> addressFields, int cityId){
        String sql = "insert into address (addressId,address,address2, cityId,createDate,createdBy,lastUpdate,lastUpdateBy,phone,postalCode) values "
                    + " (?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);){
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
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determines if the address exists (only tests on the address1 value
     *
     * @param addressFields
     * @return
     */
    private boolean addressExists(Map<String,String> addressFields){

        try (Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement()){            
            String sql = "Select * from U03PfE.address where address = '" + addressFields.get("address") + "'";
            ResultSet rs = stmnt.executeQuery(sql);
            if(rs.next()){
                // todo not sure if I need to assign all of these values...
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

                this.existingAddressId = this.myAddress.getAddressId();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public void changeAddress(CompleteCustomer modifiedFields){
//
//    }

    /**
     * Returns an instance of the myCountry object
     * @return
     */
    public Address getMyAddress (){
        Address c = new Address();
        c = this.myAddress;
        return c;
    }


    public void updateCustomerAddress(){
        String sql = "update address set address = ?,address2 = ?,phone=?,postalCode = ? where addressId = ?";

        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql)){
            ps.setString(1,this.completeCustomer.getAddress());
            ps.setString(2,this.completeCustomer.getAddress2());
            ps.setString(3,this.completeCustomer.getPhone());
            ps.setString(4,this.completeCustomer.getPostalCode());
            ps.setInt(5,this.completeCustomer.getFkAddressId());
            ps.execute();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
