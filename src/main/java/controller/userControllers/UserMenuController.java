package controller.userControllers;

import controller.MainMenuController;
import controller.interfaces.ISubMenu;
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

public class UserMenuController implements ISubMenu {
    @FXML
    private Button openButton, createButton, listButton, backButton;
    private WMS wms;





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
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Parent root = loader.load();
        MainMenuController controller = loader.getController();
        controller.setWMS(wms);
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

    @Override
    public void setWMS(WMS wms) {
        this.wms = wms;
    }
}
