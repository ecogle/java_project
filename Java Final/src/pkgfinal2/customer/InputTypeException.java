package pkgfinal2.customer;

import javafx.scene.control.Alert;
import pkgfinal2.messages.MessageFactory;

public class InputTypeException extends Exception{

    public InputTypeException(){
        super("Input invalid");
        new MessageFactory().showMessage(() -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Wrong data type");
            a.setHeaderText("Type mismatch");
            a.showAndWait();
        });
    }

    public InputTypeException(String str){
        super(str);
        new MessageFactory().showMessage(() -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Wrong data type");
            a.setHeaderText("Type mismatch");
            a.showAndWait();
        });
    }
}
