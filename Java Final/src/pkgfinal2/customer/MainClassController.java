package pkgfinal2.customer;

import com.sun.scenario.effect.impl.prism.ps.PPSDrawable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pkgfinal2.MySQLDatabase;

/**
 * Created by ecogle on 2/9/2017.
 */
public class MainClassController {

    private static CompleteCustomer completeCustomer;

    

    public static CompleteCustomer getCompleteCustomer(){
        CompleteCustomer c = new CompleteCustomer();
        c = completeCustomer;
        return c;
    }

    public static void editCustomer(CompleteCustomer c){
        
        // edit the customer information
        updateCountry(c);
        updateCity(c);
        updateAddress(c);
        updateCustomer(c);       
    }
    
    public static void deleteCustomer(CompleteCustomer compCust){
        String sql = "delete from customer where customerId = ?";
        try(PreparedStatement pstmnt = MySQLDatabase.getMySQLConnection().prepareStatement(sql);){
            pstmnt.setInt(1,compCust.getCustomerId());
            pstmnt.execute();
        }
        catch (SQLException e){
            e.getMessage();
        }
    }
    
    private static boolean updateCountry(CompleteCustomer c){
        String sql = "UDATE country set countryId= ?,countryName = ?";
        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);){            
            ps.setInt(1,c.getFkCountryId());
            ps.setString(2, c.getCountry());
            boolean flag = ps.execute();
            if(flag){
                return true;
            }
        }   
        catch(SQLException e){
            System.out.println(e.getMessage());
        }        
        return false;
    }

    private static boolean updateCity(CompleteCustomer c){
        String sql = "UPDATE city set cityId=?,city=?,countryId=?";
        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);){            
            ps.setInt(1, c.getFkCityId());
            ps.setString(2, c.getCity());
            ps.setInt(3, c.getFkCountryId());
            boolean flag = ps.execute();
            if(flag){
                return true;
            }
        }
        catch(SQLException s){
            System.out.println(s.getMessage());
        }
        return false;
    }
    
    private static boolean updateAddress(CompleteCustomer compCust){
        String sql = "UPDATE address set addressId=?,address=?,address2=?,cityId=?,postalCode=?,phone=?";
        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);) {            
            ps.setInt(1, compCust.getFkAddressId());
            ps.setString(2, compCust.getAddress());
            ps.setString(3, compCust.getAddress2());
            ps.setInt(4, compCust.getFkCityId());
            ps.setString(5, compCust.getPostalCode());
            ps.setString(6, compCust.getPhone());
            
            boolean flag = ps.execute();
            if(flag){
                return true;
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException.getMessage());
        }
        return false;        
    }
    
    private static boolean updateCustomer(CompleteCustomer compCust){
        String sql = "UPDATE customer set customerId = ?,customerName=?, addressId = ?";
        try(PreparedStatement ps = MySQLDatabase.getMySQLConnection().prepareStatement(sql);) {            
            ps.setInt(1, compCust.getCustomerId());
            ps.setString(2, compCust.getCustomerName());
            ps.setInt(3, compCust.getFkAddressId());
            boolean flag = ps.execute();
            if(flag){
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static ObservableList<CompleteCustomer> buildCustList(){
        ObservableList<CompleteCustomer> custList = FXCollections.observableArrayList();
        try {
            Statement stmnt = MySQLDatabase.getMySQLConnection().createStatement();
            String sql = "select customerId,customerName,active,addr.addressId,address,address2,postalCode,phone,ci.cityId,city,co.countryId,country from customer cou inner join\n" +
                    "address addr on cou.addressId = addr.addressId inner join city ci on addr.cityId = ci.cityId inner join\n" +
                    "country co on ci.countryId = co.countryId";
            ResultSet rs = stmnt.executeQuery(sql);
            while(rs.next()){
                CompleteCustomer c = new CompleteCustomer();
                c.setCustomerId(rs.getInt("customerId"));
                c.setCustomerName(rs.getString("customerName"));
                c.setActive(rs.getBoolean("active"));
                c.setFkAddressId(rs.getInt("addressId"));
                c.setAddress(rs.getString("address"));
                c.setAddress2(rs.getString("address2"));
                c.setPostalCode(rs.getString("postalCode"));
                c.setPhone(rs.getString("phone"));
                c.setFkCityId(rs.getInt("cityId"));
                c.setCity(rs.getString("city"));
                c.setFkCountryId(rs.getInt("countryId"));
                c.setCountry(rs.getString("country"));

                custList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
}
