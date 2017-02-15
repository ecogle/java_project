package pkgfinal2.customer;

import javafx.scene.control.Alert;
import pkgfinal2.messages.MessageFactory;

/**
 * Created by ecogle on 2/15/2017.
 */
public class InputEmptyException extends Exception {

    public InputEmptyException(){
        super();
        new MessageFactory().showMessage(() -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Required fields are missing");
            a.setHeaderText("Missing Fields");
            a.showAndWait();
        });
    }

    public InputEmptyException(String s){
        super(s);
        new MessageFactory().showMessage(() -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Required fields are missing");
            a.setHeaderText("Missing Fields");
            a.showAndWait();
        });
    }



}
