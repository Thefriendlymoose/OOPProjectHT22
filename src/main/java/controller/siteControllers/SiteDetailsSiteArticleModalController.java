package controller.siteControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;

import java.io.IOException;
import java.util.Objects;

public class SiteDetailsSiteArticleModalController {

    @FXML
    private TextField articleNameTextField, amountTextField;

    @FXML
    private Button editButton, closeButton;

    private SiteArticle siteArticle;
    private Site site;
    private Sites sites;

    public SiteDetailsSiteArticleModalController(Sites sites, Site site, SiteArticle siteArticle) {
        this.sites = sites;
        this.site = site;
        this.siteArticle = siteArticle;
    }

    public void initialize(){
        articleNameTextField.setDisable(true);
        articleNameTextField.getStyleClass().add("locked-form-field");

        amountTextField.setDisable(true);
        amountTextField.getStyleClass().add("locked-form-field");

        articleNameTextField.setText(siteArticle.getArticle().getArticleName());
        amountTextField.setText(String.valueOf(siteArticle.getAmount()));
    }


    public void onClose(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    public void onEdit(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteDetailsSiteArticleEditModal.fxml")));
        Stage stage = loader.load();
        SiteDetailsSiteArticleEditModalController controller = loader.getController();
        controller.setSiteArticle(siteArticle);
        controller.setSite(site);
        controller.setSites(sites);

        stage.setTitle("Stock: " + siteArticle.getArticle().getArticleName());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }


    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
