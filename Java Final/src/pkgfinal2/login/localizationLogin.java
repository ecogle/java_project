package pkgfinal2.login;

import java.util.*;

public class localizationLogin {
    
    public localizationLogin(Locale locale) {
        Locale.setDefault(locale);
    }
    
    
    
    public static void main(String[] args) {
        Locale defLocale = Locale.getDefault();

        Locale us = new Locale("en","US");
        Locale fr = new Locale("fr","FR");
        Locale es = new Locale("es","US");
        printProperties(us);
        printProperties(fr);
        printProperties(es);

        System.out.println(defLocale.equals(us) + " " + defLocale.equals(fr));
        
    }
    
    public static void printProperties(Locale locale){
        ResourceBundle rb = ResourceBundle.getBundle("login",locale);
        System.out.println(rb.getString("loginSuccess"));
        System.out.println(rb.getString("loginFailed"));
        
     
    }

}
