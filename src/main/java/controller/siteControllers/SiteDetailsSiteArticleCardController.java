package controller.siteControllers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;

import java.io.IOException;
import java.util.Optional;

public class SiteDetailsSiteArticleCardController {

    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton, deleteButton;

    private SiteArticle siteArticle;
    private Site site;
    private Sites sites;

    public SiteDetailsSiteArticleCardController(Sites sites, Site site, SiteArticle siteArticle) {
        this.sites = sites;
        this.site = site;
        this.siteArticle = siteArticle;
    }

    public void initialize(){
        cardNameLabel.setText(cardNameLabel.getText() + siteArticle.getArticle().getArticleName());
        cardAmountLabel.setText(cardAmountLabel.getText() + siteArticle.getAmount());
    }

    public void onGoTo(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsSiteArticleModalController.class, params -> new SiteDetailsSiteArticleModalController(sites, site, siteArticle)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsSiteArticleModal.fxml");

        stage.setTitle("Stock: " + siteArticle.getArticle().getArticleName());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

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
            sites.updateSite();
        }
    }
}
