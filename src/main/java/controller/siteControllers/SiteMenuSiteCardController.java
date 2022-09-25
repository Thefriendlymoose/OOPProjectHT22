package controller.siteControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;

import java.io.IOException;


public class SiteMenuSiteCardController {

    private Site site;

    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton;


    public void initialize(){

        Platform.runLater(() -> {
            cardNameLabel.setText(cardNameLabel.getText() + site.getSiteName());
            cardAmountLabel.setText(cardAmountLabel.getText() + site.getTotalAmountItems());

            cardGoToButton.setOnAction(actionEvent -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/siteViews/siteDetailsModal.fxml"));
                System.out.println("before load");
                Stage stage = null;
                try {
                    stage = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("after Load");

                SiteDetailsController controller = loader.getController();
                controller.setSite(site);

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


}
