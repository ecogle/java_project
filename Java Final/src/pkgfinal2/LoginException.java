package pkgfinal2;

public class LoginException extends Exception {
    
    public LoginException(){
        super();
    }
    
    public LoginException(Exception e){
        super(e);
    }
    
    public LoginException(String message){
        super(message);
        
    }

}
