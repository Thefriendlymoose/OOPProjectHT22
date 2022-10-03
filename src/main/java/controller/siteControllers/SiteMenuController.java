package controller.siteControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.site.Site;
import persistance.IPersistence;
import persistance.JSONDao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SiteMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;

    @FXML
    private VBox siteCardHolderVBox;

    private IPersistence testDao = new JSONDao();


    public void initialize() throws IOException {
        List<Site> sites = testDao.loadAllSites();

        for (Site site : sites){
            FXMLLoader siteLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteMenuSiteCard.fxml")));
            AnchorPane pane = siteLoader.load();
            SiteMenuSiteCardController controller = siteLoader.getController();

            controller.setSite(site);

            siteCardHolderVBox.getChildren().add(pane);
        }
    }

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
