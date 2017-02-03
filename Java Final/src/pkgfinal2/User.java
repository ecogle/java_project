package pkgfinal2;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User {
    
    private int userId;
    private String userName;
    private String password;
    
    
    
    
    private String encryptPassword(String pass){
        String s = new String();
        String salt = "asdofiasdflosdifOnOAAS)(UFSDFSD!!@&*#^*@#%(W$%(#$&*%#$&^*(!@&!)@#U*WFUHO(W&*RFSDKSDIV&SKSD@#$(%Hvsdklfghser89tsldfjkbnpd";
        
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(pass.getBytes(),0,pass.length());
            s = new BigInteger(1,digest.digest()).toString(16);
            
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        
        
        
        return s;
        
    }

}
