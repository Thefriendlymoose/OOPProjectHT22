package controller.sitecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;

/**
 * Controller for the modal which is used to find a site based on site id.
 *
 * @author David Al Amiri
 */
public class SiteOpenDetailsController {
    @FXML
    private TextField modalSearchField;

    @FXML
    private Label warningLabel;
    private Sites sites;
    private WMS wms;

    public SiteOpenDetailsController(WMS wms) {
        this.wms = wms;
        this.sites = wms.getSites();
    }

    /**
     * Handles the event when a user clicks the open button.
     * Parses the string that is returned by the TextField into a long.
     * If the parse fails then the user is informed to only provide a number
     * If the parse succeeds then gets the site from sites.
     * If no site is returned then user is informed that a site could not be found
     * If site is returned then the site details modal is opened
     * @param e
     * @throws IOException Throws exception if FXML fails to load.
     */
    public void onOpen(ActionEvent e) throws IOException {

        try {

            Site site = sites.getById(Long.parseLong(modalSearchField.getText()));

            if(site == null){
                warningLabel.setText("Can't find article");
            } else {

                StageDependencyInjection.addInjectionMethod(
                        SiteDetailsController.class, params -> new SiteDetailsController(wms, site)
                );

                Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsModal.fxml");

                stage.setTitle("Site: " + site.getSiteId());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
                stage.show();
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
            }

        } catch (NumberFormatException error){
            warningLabel.setText("Site ID needs to only contain numbers");
        }
    }

}

