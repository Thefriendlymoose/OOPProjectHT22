package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenuController {
    @FXML
    Button menuSiteButton, menuArticleButton, menuCustomerButton, menuUserButton, menuOrderButton;


    public void articleBtnHandler() throws Exception {
        changeScene("articleMenu", menuArticleButton);
    }

    public void orderBtnHandler() throws Exception {
        changeScene("orderMenu", menuOrderButton);
    }

    public void customerBtnHandler() throws Exception {
        changeScene("customerMenu", menuCustomerButton);
    }

    public void userBtnHandler() throws Exception {
        changeScene("userMenu", menuUserButton);
    }
    public void siteBtnHandler() throws Exception {
        changeScene("siteMenu", menuSiteButton);
    }

    private void changeScene(String newScene, Button button) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/"+newScene+".fxml")));
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
