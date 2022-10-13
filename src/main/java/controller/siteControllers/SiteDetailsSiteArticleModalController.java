package controller.siteControllers;

import javafx.application.Platform;
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

    private SiteArticle sa;
    private Site site;
    private Sites sites;

    public void initialize(){
        Platform.runLater(() -> {
            articleNameTextField.setDisable(true);
            articleNameTextField.getStyleClass().add("locked-form-field");

            amountTextField.setDisable(true);
            amountTextField.getStyleClass().add("locked-form-field");


            articleNameTextField.setText(sa.getArticle().getArticleName());
            amountTextField.setText(String.valueOf(sa.getAmount()));
        });
    }

    public void setSiteArticle(SiteArticle sa){
        this.sa = sa;
    }

    public void setSite(Site site){
        this.site = site;
    }

    public void onClose(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    public void onEdit(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteDetailsSiteArticleEditModal.fxml")));
        Stage stage = loader.load();
        SiteDetailsSiteArticleEditModalController controller = loader.getController();
        controller.setSiteArticle(sa);
        controller.setSite(site);
        controller.setSites(sites);

        stage.setTitle("Stock: " + sa.getArticle().getArticleName());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }


    public void setSites(Sites sites) {
        this.sites = sites;
    }
}
