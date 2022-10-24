package controller.sitecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.site.Site;
import model.site.SiteArticle;

import java.io.IOException;

/**
 * Controller for the modal which shows more details for the site article.
 *
 * @author David Al Amiri
 */
public class SiteDetailsSiteArticleModalController {

    @FXML
    private TextField articleNameTextField;
    @FXML
    private TextField amountTextField;

    private SiteArticle siteArticle;
    private Site site;
    private WMS wms;

    public SiteDetailsSiteArticleModalController(WMS wms, Site site, SiteArticle siteArticle) {
        this.wms = wms;
        this.site = site;
        this.siteArticle = siteArticle;
    }

    /**
     * Initializes the textfields with data from the sitearticle
     */
    public void initialize(){
        articleNameTextField.setDisable(true);
        articleNameTextField.getStyleClass().add("locked-form-field");

        amountTextField.setDisable(true);
        amountTextField.getStyleClass().add("locked-form-field");

        articleNameTextField.setText(siteArticle.getArticle().getArticleName());
        amountTextField.setText(String.valueOf(siteArticle.getAmount()));
    }


    /**
     * Handles the event when a user clicks close in the modal
     * Closes the modal
     * @param e
     */
    public void onClose(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    /**
     * Handles the event when a user clicks edit in the modal
     * Open a new modal where a user can edit the sitearticle
     * @param e
     * @throws IOException Throws exception if the FXML fails to load
     */
    public void onEdit(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteDetailsSiteArticleEditModalController.class, params -> new SiteDetailsSiteArticleEditModalController(wms, site, siteArticle)
        );

        Stage stage = StageDependencyInjection.load("fxml/siteViews/siteDetailsSiteArticleEditModal.fxml");

        stage.setTitle("Stock: " + siteArticle.getArticle().getArticleName());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }


}
