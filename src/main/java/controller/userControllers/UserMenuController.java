package controller.userControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class UserMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;

    public void createButtonHandler() throws Exception {
        changeScene("CreateUserMenu", createButton);
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    private void changeScene(String newScene, Button button) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/userViews/"+newScene+".fxml")));
        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
