package controller;

import controller.interfaces.ISubMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.WMS;

import java.util.Objects;

public class MainMenuController {
    @FXML
    private Button menuSiteButton, menuArticleButton, menuCustomerButton, menuUserButton, menuOrderButton;
    private WMS wms;


    public void articleBtnHandler() throws Exception {
        changeScene("articleViews/articleMenu", menuArticleButton);
    }

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

    private void changeScene(String newScene, Button button) throws Exception{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../fxml/"+newScene+".fxml")));
        Parent root = loader.load();
        ISubMenu controller = loader.getController();
        controller.setWMS(wms);

        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void setWMS(WMS wms) {
        this.wms = wms;
    }
}
