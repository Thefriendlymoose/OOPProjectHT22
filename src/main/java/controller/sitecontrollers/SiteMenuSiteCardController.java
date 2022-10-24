package controller.sitecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;

import java.io.IOException;


/**
 * controller for the site cards which is shown in the site menu
 *
 * @author David Al Amiri
 */
public class SiteMenuSiteCardController {

    @FXML
    private Label cardNameLabel;

    @FXML
    private Label cardAmountLabel;

    private WMS wms;
    private Site site;

    public SiteMenuSiteCardController(WMS wms, Site site) {
        this.wms = wms;
        this.site = site;
    }


    /**
     * Initializes the Labels in the card with data from the provided site
     */
    public void initialize(){
        cardNameLabel.setText(cardNameLabel.getText() + site.getSiteName());
        cardAmountLabel.setText(cardAmountLabel.getText() + site.getTotalAmountItems());
    }

    /**
     * Handles the event when a user clicks the goto button in the card.
     * If clicked opens a modal with the site details
     * @param e
     * @throws IOException Throws exception if the FXML fails to load
     */
    public void onGoTo(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsController.class, params -> new SiteDetailsController(wms, site)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsModal.fxml");

        stage.setTitle("Site: " + site.getSiteId());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();

    }
}
