package controller.siteControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.site.Site;
import model.site.SiteArticle;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class SiteDetailsSiteArticleCardController {

    @FXML
    private Label cardNameLabel, cardAmountLabel;

    @FXML
    private Button cardGoToButton, deleteButton;

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

            deleteButton.setOnAction(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Deletion");
                alert.setHeaderText("The Site Article Will Be Deleted");
                alert.setContentText("Are you sure? Will not be able to be revered");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.CANCEL){
                    System.out.println("Clicked Cancel");
                } else {
                    site.removeSiteArticles(siteArticle);
                }
            });
        });
    }

}
