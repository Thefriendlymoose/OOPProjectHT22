package controller.siteControllers;

import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.observer.Observer;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;

public class SiteMenuController implements Observer {
    @FXML
    private Button openButton, createButton, backButton;

    @FXML
    private VBox siteCardHolderVBox;

    private Sites sites;
    private WMS wms;

    public SiteMenuController(WMS wms) {
        this.wms = wms;
        this.sites = wms.getSites();
        wms.registerObserver(this);
    }

    public void initialize() throws IOException {
        loadCards();
    }

    private void loadCards() throws IOException {
        siteCardHolderVBox.getChildren().clear();
        for (Site site : sites.getInList()){

            ParentDependencyInjection.addInjectionMethod(
                    SiteMenuSiteCardController.class, params -> new SiteMenuSiteCardController(wms, site)
            );

            Parent pane = ParentDependencyInjection.load("fxml/siteViews/siteMenuSiteCard.fxml");

            siteCardHolderVBox.getChildren().add(pane);
        }
    }

    public void createButton(ActionEvent e) throws Exception{
        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteCreateModal.fxml");

        stage.setTitle("Create Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void onOpen(ActionEvent e) throws IOException {
        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteOpenDetailsModal.fxml");

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

}
