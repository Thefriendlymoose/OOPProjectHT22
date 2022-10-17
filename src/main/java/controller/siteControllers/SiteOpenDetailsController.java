package controller.siteControllers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;

public class SiteOpenDetailsController {
    @FXML
    private TextField modalSearchField;

    @FXML
    private Label warningLabel;
    private Sites sites;

    public SiteOpenDetailsController(Sites sites) {
        this.sites = sites;
    }

    public void onOpen(ActionEvent e) throws IOException {

        try {

            Site site = sites.getById(Long.parseLong(modalSearchField.getText()));

            if(site == null){
                warningLabel.setText("Can't find article");
            } else {

                StageDependencyInjection.addInjectionMethod(
                        SiteDetailsController.class, params -> new SiteDetailsController(sites, site)
                );

                Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsModal.fxml");

                stage.setTitle("Site: " + site.getSiteId());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
                stage.show();
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
            }

        } catch (NumberFormatException error){
            warningLabel.setText("Article ID needs to only contain numbers");
        }
    }

}

