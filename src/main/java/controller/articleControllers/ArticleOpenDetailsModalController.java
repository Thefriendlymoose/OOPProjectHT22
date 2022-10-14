package controller.articleControllers;

import controller.SignInController;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.article.Article;
import model.article.Articles;
import model.article.ArticlesFacade;

import java.io.IOException;
import java.util.Objects;

public class ArticleOpenDetailsModalController {

    @FXML
    private TextField modalSearchField;

    @FXML
    private Label warningLabel;

    private Articles articles;

    public ArticleOpenDetailsModalController(Articles articles) {
        this.articles = articles;
    }

    public void modalOpenArticleButtonHandler(ActionEvent e) throws IOException {

        try {

            Article article = articles.findById(Long.parseLong(modalSearchField.getText()));

            if(article == null){
                warningLabel.setText("Can't find article");
            } else {
                Callback<Class<?>, Object> test = param -> new ArticleDetailsController(articles, article);

                StageDependencyInjection.addInjectionMethod(
                        ArticleDetailsController.class, test
                );

                //FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleDetailsModal.fxml")));
                Stage stage = StageDependencyInjection.load("fxml/articleViews/articleDetailsModal.fxml");

                //ArticleDetailsController cont = loader.getController();
                //cont.setArticle(article);
                //cont.setArticles(articles);

                stage.setTitle("Article: " + article.getArticleId());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
                stage.show();
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

              //  StageDependencyInjection.removeInjectionMethod(ArticleDetailsController.class);

            }

        } catch (NumberFormatException error){
            warningLabel.setText("Article ID needs to only contain numbers");
        }
    }
}
