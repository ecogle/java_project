package pkgfinal2.login;

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
