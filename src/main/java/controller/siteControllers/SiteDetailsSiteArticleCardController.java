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
import model.site.SiteArticle;

import java.io.IOException;
import java.util.Objects;

public class SiteDetailsSiteArticleCardController {

    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton;

    private SiteArticle siteArticle;
    private Site site;

    public void setSiteArticle(SiteArticle siteArticle){
        this.siteArticle = siteArticle;
    }

    public void setSite(Site site){
        this.site = site;
    }

    public void initialize(){
        Platform.runLater(() -> {
            cardNameLabel.setText(cardNameLabel.getText() + siteArticle.getArticle().getArticleName());
            cardAmountLabel.setText(cardAmountLabel.getText() + siteArticle.getAmount());

            cardGoToButton.setOnAction(actionEvent -> {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/siteViews/siteDetailsSiteArticleModal.fxml")));
                Stage stage;
                try {
                    stage = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                SiteDetailsSiteArticleModalController controller = loader.getController();
                controller.setSiteArticle(siteArticle);
                controller.setSite(site);

                stage.setTitle("Stock: " + siteArticle.getArticle().getArticleName());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                stage.show();

            });
        });
    }

}
