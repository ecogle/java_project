package pkgfinal2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmWindow {
    
    static boolean answer;
    
    public static boolean display(String message){
        Stage window = new Stage();
        window.setTitle("Confirming...");
        window.initModality(Modality.APPLICATION_MODAL); //makes the window modal
        window.setOnCloseRequest(e -> {
            e.consume();
        });
        
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));
        layout.setVgap(8);
        layout.setHgap(10);
        //label
        Label lblMessage = new Label(message);
        layout.add(lblMessage, 0, 0,2,1);
        
        //Yes button
        Button btnYes = new Button("Yes");
        btnYes.setOnAction(e-> {
            answer = true;
            window.close();
        });
        layout.add(btnYes, 0, 1);
        
        Button btnNo = new Button("No");
        btnNo.setOnAction(e -> {
            answer = false;
            window.close();
        });
        layout.add(btnNo, 1, 1);
        
       
        Scene scene = new Scene(layout,300,200);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
    }
    
    
}
