package controller;


import controller.dpi.ParentDependencyInjection;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.authentication.Session;
import model.WMS;
import model.user.Permission;

public class MainMenuController {
    @FXML
    private Button menuSiteButton;
    @FXML
    private Button menuArticleButton;
    @FXML
    private Button menuCustomerButton;
    @FXML
    private Button menuUserButton;
    @FXML
    private Button menuOrderButton;

    private WMS wms;
    private Session session;

    public MainMenuController(WMS wms) {
        this.wms = wms;
        this.session = wms.getSession();
    }


    public void initialize(){
            if (!session.hasAccess(Permission.SITE)){
                menuSiteButton.setDisable(true);
            }

            if (!session.hasAccess(Permission.ARTICLE)){
                menuArticleButton.setDisable(true);
            }

            if (!session.hasAccess(Permission.CUSTOMER)){
                menuCustomerButton.setDisable(true);
            }

            if (!session.hasAccess(Permission.ORDER)){
                menuOrderButton.setDisable(true);
            }

            if (!session.hasAccess(Permission.USER)){
                menuUserButton.setDisable(true);
            }
    }

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
        Parent root = ParentDependencyInjection.load("fxml/"+newScene+".fxml");

        Stage window = (Stage) button.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
