package controller.menucontrollers;

import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import model.WMS;

import java.io.IOException;

/**
 * Controller for the menubar
 *
 * @author David al Amiri
 */
public class MenuController {

    @FXML
    private MenuItem logout;

    @FXML
    private Label loggedInLabel;

    @FXML
    private Node test;

    private WMS wms;

    public MenuController(WMS wms){
        this.wms = wms;
    }

    public void initialize(){
        if (wms.getSession() == null) {
            loggedInLabel.setText("Not Logged in!");
        } else {
            loggedInLabel.setText("Logged in as: " + wms.getSession().getUser().getName());
        }
    }
    public void onLogout(ActionEvent e) throws IOException {
        if (wms.getSession() != null){
            wms.setSession(null);
            Parent parent = ParentDependencyInjection.load("fxml/startScreen.fxml");
            Stage window = (Stage) test.getScene().getWindow();
            window.setScene(new Scene(parent));
        }
    }

}
