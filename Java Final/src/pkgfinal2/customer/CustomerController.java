package pkgfinal2.customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import pkgfinal2.MainScreen;
import pkgfinal2.MySQLDatabase;

/**
 * Created by ecogle on 2/8/2017.
 */
public class CustomerController {
    private Customer myCustomer;
    private int highestCustomerId;
    public int existingCustomerId;
    public boolean newCustomerAdded;

    /**
     *
     * @param customerFields
     * @param addressId
     */
    public CustomerController(Map<String,String> customerFields,int addressId){

        /*
            if the customer does not exist, get the max value of the existing
            primary keys then add the new value to the database. A new myCounty
            object is created with the values.

            if the customer does exist, a new myCity object is created with
            the existing values.
        */
        if(!customerExists(customerFields)){
            this.highestCustomerId = getHighestCustomerId();
            addCustomerToBase(customerFields,addressId);
        }
        else{
            myCustomer.setCustomerId(existingCustomerId);
        }
    }

    /*
        returns the max value of the primary keys of the customer table

        -- could probably return void and just set the private property
    */
    private int getHighestCustomerId(){
        try{
            Statement stmnt= MySQLDatabase.getMySQLConnection().createStatement();
            ResultSet rs = stmnt.executeQuery("select max(customerId) as maxId from customer");
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
     *
     * @param customerFields
     * @param addressId
     */
    public void addCustomerToBase(Map<String,String> customerFields, int addressId){

        try {
            String sql = "insert into customer (customerId,customerName, addressId, active,createDate, createdBy,lastUpdate,lastUpdateBy) values "
                    + " (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);
            String nowDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            String nowTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            this.myCustomer = new Customer();

            this.myCustomer.setCustomerId(this.highestCustomerId+1);
            this.myCustomer.setCustomerName(customerFields.get("customerName").trim());
            this.myCustomer.setActive(new Boolean(customerFields.get("active")));
            this.myCustomer.setAddressId(addressId);
            this.myCustomer.setCreateDate(nowDate + " " + nowTime);
            this.myCustomer.setCreatedBy(MainScreen.getAuthUser());
            this.myCustomer.setLastUpdate(nowDate + " " + nowTime);
            this.myCustomer.setLastUpdateBy(MainScreen.getAuthUser());           

            ps.setInt(1,this.myCustomer.getCustomerId());
            ps.setString(2,this.myCustomer.getCustomerName());            
            ps.setInt(3,this.myCustomer.getAddressId());
            ps.setBoolean(4, myCustomer.getActive());
            ps.setString(5, this.myCustomer.getCreateDate());
            ps.setString(6, this.myCustomer.getCreatedBy());
            ps.setString(7, this.myCustomer.getLastUpdate());
            ps.setString(8, this.myCustomer.getLastUpdateBy());
            

            ps.execute();
            /*Alert alrt = new Alert(Alert.AlertType.INFORMATION,"New customer Added to database");
            alrt.showAndWait();*/
            this.newCustomerAdded=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param customerFields
     * @return
     */
    private boolean customerExists(Map<String,String> customerFields){

        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            String sql = "Select * from U03PfE.customer where customerName = '" + customerFields.get("customerName") + "'";
            ResultSet rs = stmnt.executeQuery(sql);
            if(rs.next()){

                this.myCustomer = new Customer();
                
                this.myCustomer.setCustomerId(rs.getInt("customerId"));
                this.myCustomer.setCustomerName(rs.getString("customerName"));
                this.myCustomer.setAddressId(rs.getInt("addressId"));
                this.myCustomer.setActive(rs.getBoolean("active"));
                this.myCustomer.setCreateDate(rs.getString("createDate"));
                this.myCustomer.setCreatedBy(rs.getString("createdBy"));
                this.myCustomer.setLastUpdate(rs.getString("lastUpdate"));
                this.myCustomer.setLastUpdateBy(rs.getString("lastUpdateBy"));
                
                stmnt.close();
                MySQLDatabase.closeConnection();

                this.existingCustomerId = this.myCustomer.getCustomerId();
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
    public Customer getMyCustomer (){
        Customer c = new Customer();
        c = this.myCustomer;
        return c;
    }
}