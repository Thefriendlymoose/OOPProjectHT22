package controller.siteControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.Sites;
import persistence.SitesDAO;

import java.io.IOException;
import java.util.Objects;

public class SiteOpenDetailsController {
    @FXML
    private TextField modalSearchField;

    @FXML
    private Label warningLabel;
    private Sites sites;

    public void onOpen(ActionEvent e) throws IOException {

        try {

            Site site = SitesDAO.getInstance().findById(Long.parseLong(modalSearchField.getText()));

            if(site == null){
                warningLabel.setText("Can't find article");
            } else {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteDetailsModal.fxml")));
                Stage stage = loader.load();

                SiteDetailsController cont = loader.getController();
                cont.setSite(site);

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

    public void setSites(Sites sites) {
        this.sites = sites;
    }
}

