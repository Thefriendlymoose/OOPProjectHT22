package controller.userControllers;

import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;

import java.util.Objects;

public class UserMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;
    private WMS wms;

    public UserMenuController(WMS wms) {
        this.wms = wms;
    }





    /*

    public void orderBtnHandler() throws Exception {
        changeScene("orderViews/orderMenu", menuOrderButton);
    }

    public void customerBtnHandler() throws Exception {
        changeScene("customerViews/customerMenu", menuCustomerButton);
    }

    public void userBtnHandler() throws Exception {
        changeScene("userViews/userMenu", menuUserButton);
    }
    public void siteBtnHandler() throws Exception {
        changeScene("siteViews/siteMenu", menuSiteButton);
    }

     */

    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void createButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/CreateUserMenu.fxml")));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();

    }

    public void openButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/openUser.fxml")));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();

    }

}
