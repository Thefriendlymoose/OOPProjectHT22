package controller.siteControllers;

import controller.dpi.StageDependencyInjection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;


public class SiteMenuSiteCardController {



    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton;
    private Sites sites;
    private Site site;

    public SiteMenuSiteCardController(Sites sites, Site site) {
        this.sites = sites;
        this.site = site;
    }


    public void initialize(){
        cardNameLabel.setText(cardNameLabel.getText() + site.getSiteName());
        cardAmountLabel.setText(cardAmountLabel.getText() + site.getTotalAmountItems());
    }

    public void onGoTo(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsController.class, params -> new SiteDetailsController(sites, site)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsModal.fxml");

        stage.setTitle("Site: " + site.getSiteId());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();

    }
}
