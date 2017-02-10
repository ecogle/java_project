package pkgfinal2.customer;

public class InputTypeException extends Exception{

    public InputTypeException(){
        super("Input invalid");
    }

    public InputTypeException(String str){
        super(str);
    }
}
