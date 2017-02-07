package pkgfinal2.customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustomerControl {
    
    //general work
        // error check all inputs for correct types
    
    public static boolean textContainsNumbers(String str) throws InputTypeException{
        // if str contains numbers, throw exception
        Pattern pattern = Pattern.compile("\\D");
        
        Matcher matcher = pattern.matcher(str);
        
        if(matcher.find()){
            return true;            
        }
        else{
            return false;
        }
        
    }
    
    public static void main(String[] args) {
        String d= "This is the time my life;";
        
        try{
            System.out.println(textContainsNumbers(d));
        }
        catch(InputTypeException e){
            e.printStackTrace();
        }
        
    }
    
    public static boolean errorCheckNumbers(String str) throws InputTypeException{
        // if str contains characters, throw exception
        //Matcher
        return true;
    }
    
    
    //country work
        //search for matching country in database
            //if match get primarykey and insert that key in database
            //if NO match, add country to database
    
        //get primary key
            //look for highest value in the ID field, add 1 to it
    
        //add the audit information to the database for adding the country
    
    
    //city work
        //search for matching city in database
            //if match get primarykey and insert that key in database
            //if NO match, add country to database
    
        //get primary key
            //look for highest value in the ID field, add 1 to it
    
        //add the audit information to the database for adding the country
    
    
    //address work
        //search for matching address in database
            //if match get primarykey and insert that key in database
            //if NO match, add country to database
    
        //get primary key
            //look for highest value in the ID field, add 1 to it
    
        //add the audit information to the database for adding the country
    
    //customer work
        //look for customer with matching name and phone
            //if true, throw duplicate customer exception
}
