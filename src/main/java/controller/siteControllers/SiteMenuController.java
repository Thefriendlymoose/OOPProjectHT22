package controller.siteControllers;

import controller.MainMenuController;
import controller.dpi.DependencyInjection;
import controller.interfaces.ISubMenu;
import javafx.application.Platform;
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
import model.WMS;
import model.observer.Observer;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;
import java.util.Objects;

public class SiteMenuController implements Observer, ISubMenu {
    @FXML
    private Button openButton, createButton, backButton;

    @FXML
    private VBox siteCardHolderVBox;

    private Sites sites;
    private WMS wms;

    public void initialize() {
        Platform.runLater(() -> {
            sites = wms.getSites();
            sites.registerObserver(this);
            try {
                loadCards();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void loadCards() throws IOException {
        siteCardHolderVBox.getChildren().clear();
        for (Site site : sites.getInList()){
            FXMLLoader siteLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteMenuSiteCard.fxml")));
            AnchorPane pane = siteLoader.load();
            SiteMenuSiteCardController controller = siteLoader.getController();

            controller.setSite(site);
            controller.setSites(sites);

            siteCardHolderVBox.getChildren().add(pane);
        }
    }

    public void createButton(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteCreateModal.fxml")));
        Stage stage = loader.load();
        SiteCreateController controller = loader.getController();
        controller.setSites(sites);
        stage.setTitle("Create Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void backBtnHandler() throws Exception{
        Parent root = DependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void onOpen(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteOpenDetailsModal.fxml")));
        Stage stage = loader.load();
        SiteOpenDetailsController controller = loader.getController();
        controller.setSites(sites);
        stage.setTitle("Open Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    @Override
    public void update() {
        try {
            loadCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setWMS(WMS wms) {
        this.wms = wms;
    }
}
