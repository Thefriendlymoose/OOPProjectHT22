package controller.articleControllers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.article.Article;

import java.io.IOException;


public class ArticleMenuCardController {

    @FXML
    private Label cardNumberLabel, cardNameLabel, cardDescriptionLabel;

    @FXML
    private Button cardGoToButton;

    private Article article;
    private WMS wms;

    public ArticleMenuCardController(WMS wms, Article article) {
        this.wms = wms;
        this.article = article;
    }


    @FXML
    public void initialize(){
        cardNumberLabel.setText(cardNumberLabel.getText() + article.getArticleId());
        cardNameLabel.setText(cardNameLabel.getText() + article.getArticleName());
        cardDescriptionLabel.setText(cardDescriptionLabel.getText() + article.getDescription());
    }

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
