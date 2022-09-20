package controller.orderControllers;

import database.DataBaseAdapter;
import database.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;


public class OpenOrderController {

    @FXML
    private TextField searchField;

    @FXML
    private Button openButton;

    public void openOrder(){
        DataBaseAdapter dba = DataBaseAdapter.getInstance();
        if(!searchField.getText().isEmpty()){
            Document doc = dba.findAndOpenOrder(searchField.getText());
            if (doc != null){
                System.out.println(doc.get("ordernumber") + " - " + doc.get("description"));
                Stage stage = (Stage) openButton.getScene().getWindow();
                stage.close();
            } else {

            }
        } else {

        }

    }

}
