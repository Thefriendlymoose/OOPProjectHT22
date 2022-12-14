package controller.sitecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;
import model.site.SiteArticle;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller for showing a site article card in the article details view.
 *
 * @author David Al Amiri
 */
public class SiteDetailsSiteArticleCardController {

    @FXML
    private Label cardNameLabel;
    @FXML
    private Label cardAmountLabel;

    private SiteArticle siteArticle;
    private Site site;
    private WMS wms;

    public SiteDetailsSiteArticleCardController(WMS wms, Site site, SiteArticle siteArticle) {
        this.wms = wms;
        this.site = site;
        this.siteArticle = siteArticle;
    }

    /**
     * Initializes the card with the data from the site article
     */
    public void initialize(){
        cardNameLabel.setText(cardNameLabel.getText() + siteArticle.getArticle().getArticleName());
        cardAmountLabel.setText(cardAmountLabel.getText() + siteArticle.getAmount());
    }


    /**
     * Handles the event when a user clicks the go button in the card
     * @param e
     * @throws IOException Throws exception if the FXML fails to load.
     */
    public void onGoTo(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsSiteArticleModalController.class, params -> new SiteDetailsSiteArticleModalController(wms, site, siteArticle)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsSiteArticleModal.fxml");

        stage.setTitle("Stock: " + siteArticle.getArticle().getArticleName());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    /**
     * Handles the event when a user clicks the delete button in the card
     * Deletes the sitearticle from the site
     * @param e
     */
    public void onDelete(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("The Site Article Will Be Deleted");
        alert.setContentText("Are you sure? Will not be able to be revered");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            site.removeSiteArticles(siteArticle);
            wms.updateSite();
        }
    }
}
