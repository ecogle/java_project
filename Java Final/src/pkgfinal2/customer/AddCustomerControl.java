package pkgfinal2.customer;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCustomerControl {

    private static CountryController c;
    private static CityController ci;
    private static AddressController adr;
    private static CustomerController ct;
    //general work
    // error check all inputs for correct types

    /**
     * Checks to see if an expected character exclusive string contains numbers
     *
     * @param str
     * @return false if string contains only characters
     * @throws InputTypeException if string contains a number
     */
    public static boolean textContainsNumbers(String str) throws InputTypeException {
        // if str contains numbers, throw exception
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            throw new InputTypeException("There are no numbers allowed in the field");
        } else {
            return false;
        }
    }

    /**
     * Checks to see if an expected all numberic string contains characters
     *
     * @param str
     * @return false if no character present
     * @throws InputTypeException if there are characters present
     */
    public static boolean numbersContainText(String str) throws InputTypeException {

        //regular expression pattern matcher
        Pattern pattern = Pattern.compile("[a-zA-Z]");

        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            throw new InputTypeException("There are no characters allowed in the field");
        } else {
            return false;
        }
    }


    public static boolean checkForEmpty(String s) {
        if(s == null || s.trim().equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * checks if value is empty string or null
     *
     * @param str
     * @return
     */
    public static boolean isEmptyValue(String str) {
        if (str.trim().equals("") || str.trim() == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Instantiates a new CountryController
     *
     * @param str
     */
    public static void countryWork(String str) {
        c = new CountryController(str);
    }

    public static void cityWork(String str) {
        ci = new CityController(str, c.getMyCountry().getCountryId());

    }

    public static void addressWork(Map<String, String> addressFields) {
        adr = new AddressController(addressFields, ci.getMyCity().getCityId());
    }

    public static void customerWork(Map<String,String> customerFields){
        ct = new CustomerController(customerFields,adr.getMyAddress().getAddressId());
    }

}

    
    

