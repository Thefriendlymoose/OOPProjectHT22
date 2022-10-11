package controller.siteControllers;

import javafx.application.Platform;
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

    private Site site;

    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton;
    private Sites sites;


    public void initialize(){

        Platform.runLater(() -> {
            cardNameLabel.setText(cardNameLabel.getText() + site.getSiteName());
            cardAmountLabel.setText(cardAmountLabel.getText() + site.getTotalAmountItems());

            cardGoToButton.setOnAction(actionEvent -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/siteViews/siteDetailsModal.fxml"));

                Stage stage = null;
                try {
                    stage = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                SiteDetailsController controller = loader.getController();
                controller.setSite(site);
                controller.setSites(sites);

                stage.setTitle("Site: " + site.getSiteId());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                stage.show();

            });

        });

    }

    public void setSite(Site site){
        this.site = site;
    }


    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
