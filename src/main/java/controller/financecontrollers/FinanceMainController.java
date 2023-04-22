package controller.financecontrollers;

import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.WMS;
import model.observer.Observer;

import java.io.IOException;

public class FinanceMainController implements Observer {

    @FXML
    private Button backButton;
    private WMS wms;

    public FinanceMainController(WMS wms){

        this.wms = wms;
        wms.registerObserver(this);
    }


    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    @Override
    public void update() {

    }
}
