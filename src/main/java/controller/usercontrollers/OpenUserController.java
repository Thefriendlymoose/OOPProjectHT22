package controller.usercontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

/**
 * Controller for opening a user based on userID
 */
public class OpenUserController {

    @FXML
    private TextField searchField;

    @FXML
    private Button openButton;

    public void openOrder(){
        if(!searchField.getText().isEmpty()){
            // removed databaseadapter
            Document doc = null;
            if (doc != null){
                System.out.println(doc.get("userID") + " - " + doc.get("description"));
                Stage stage = (Stage) openButton.getScene().getWindow();
                stage.close();
            } else {

            }
        } else {

        }

    }

}