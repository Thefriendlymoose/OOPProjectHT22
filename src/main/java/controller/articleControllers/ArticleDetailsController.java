package controller.articleControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.article.Articles;

import java.io.IOException;


public class ArticleDetailsController {
    @FXML
    Label detailsTitleLabel, numberLabel, nameLabel, descriptionLabel, categoryLabel, statusLabel;

    @FXML
    TextField numberTextField, nameTextField, costTextField, sellPriceTextField;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    ComboBox<ArticleCategory> categoryComboBox;

    @FXML
    ComboBox<ArticleStatus> statusComboBox;

    @FXML
    Button editButton, closeButton;

    private Article article;
    private Articles articles;

    public ArticleDetailsController(Articles articles, Article article) {
        this.articles = articles;
        this.article = article;
    }

    @FXML
    public void initialize(){
        detailsTitleLabel.setText(detailsTitleLabel.getText() + article.getArticleId());
        numberTextField.setText(String.valueOf(article.getArticleId()));
        nameTextField.setText(article.getArticleName());
        descriptionTextArea.setText(article.getDescription());
        categoryComboBox.setValue(article.getCategory());
        statusComboBox.setValue(article.getStatus());
        categoryComboBox.getItems().setAll(ArticleCategory.values());
        statusComboBox.getItems().setAll(ArticleStatus.values());
        costTextField.setText(String.valueOf(article.getCost()));
        sellPriceTextField.setText(String.valueOf(article.getSellPrice()));
    }

    public void editHandler(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/articleViews/articleEditFormModal.fxml"));
        Stage stage = loader.load();

        ArticleEditFormController cont = loader.getController();
        cont.setArticle(article);
        cont.setArticles(articles);

        stage.setTitle("Open Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();

        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }

    public void closeHandler(ActionEvent e) {
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }


}
