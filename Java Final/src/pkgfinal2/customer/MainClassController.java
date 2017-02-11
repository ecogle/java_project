package pkgfinal2.customer;

import sun.text.resources.mk.CollationData_mk;

/**
 * Created by ecogle on 2/9/2017.
 */
public class MainClassController {

    private CompleteCustomer completeCustomer;



    public CompleteCustomer getCompleteCustomer(){
        CompleteCustomer c = new CompleteCustomer();
        c = this.completeCustomer;
        return c;
    }


}
