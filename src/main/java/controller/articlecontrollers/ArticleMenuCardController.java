package controller.articlecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.article.Article;

import java.io.IOException;


/**
 * Controller for the card which is visible in the article menu.
 *
 * @author David Al Amiri
 */
public class ArticleMenuCardController {

    @FXML
    private Label cardNumberLabel, cardNameLabel, cardDescriptionLabel;

    private Article article;
    private WMS wms;

    public ArticleMenuCardController(WMS wms, Article article) {
        this.wms = wms;
        this.article = article;
    }


    /**
     * Initializes the card with the data from the provided article
     */
    @FXML
    public void initialize(){
        cardNumberLabel.setText(cardNumberLabel.getText() + article.getArticleId());
        cardNameLabel.setText(cardNameLabel.getText() + article.getArticleName());
        cardDescriptionLabel.setText(cardDescriptionLabel.getText() + article.getDescription());
    }

    /**
     * Handles the event when the user presses the button in the card.
     * Which opens the article in a new modal
     * @param actionEvent
     * @throws IOException Is thrown when the FXML fails to load
     */
    public void onGo(ActionEvent actionEvent) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                ArticleDetailsController.class, params -> new ArticleDetailsController(wms, article)
        );

        Stage stage = StageDependencyInjection.load("fxml/articleViews/articleDetailsModal.fxml");

        stage.setTitle("Article: " + article.getArticleId());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

}
