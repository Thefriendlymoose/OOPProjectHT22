package controller.articleControllers;

import controller.dpi.StageDependencyInjection;
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
import javafx.util.Callback;
import model.article.Article;
import model.article.Articles;

import java.io.IOException;
import java.util.Objects;


public class ArticleMenuCardController {

    @FXML
    private Label cardNumberLabel, cardNameLabel, cardDescriptionLabel;

    @FXML
    private Button cardGoToButton;

    private Article article;
    private Articles articles;

    public ArticleMenuCardController(Articles articles, Article article) {
        this.articles = articles;
        this.article = article;
    }


    @FXML
    public void initialize(){
        cardNumberLabel.setText(cardNumberLabel.getText() + article.getArticleId());
        cardNameLabel.setText(cardNameLabel.getText() + article.getArticleName());
        cardDescriptionLabel.setText(cardDescriptionLabel.getText() + article.getDescription());
    }

    public void onGo(ActionEvent actionEvent) throws IOException {
        Callback<Class<?>, Object> test = param -> new ArticleDetailsController(articles, article);

        StageDependencyInjection.addInjectionMethod(
                ArticleDetailsController.class, test
        );

        Stage stage = StageDependencyInjection.load("fxml/articleViews/articleDetailsModal.fxml");

        stage.setTitle("Article: " + article.getArticleId());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        stage.show();
    }

}
