package controller.siteControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import persistence.IPersistence;
import persistence.SitesDAO;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SiteMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;

    @FXML
    private VBox siteCardHolderVBox;

    private IPersistence<Site> testDao = SitesDAO.getInstance();

    public void initialize() throws IOException {
        List<Site> sites = testDao.getAll();

        for (Site site : sites){
            FXMLLoader siteLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteMenuSiteCard.fxml")));
            AnchorPane pane = siteLoader.load();
            SiteMenuSiteCardController controller = siteLoader.getController();

            controller.setSite(site);

            siteCardHolderVBox.getChildren().add(pane);
        }
    }

    public void createButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteDetailsEditModal.fxml")));
        stage.setTitle("Create Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
