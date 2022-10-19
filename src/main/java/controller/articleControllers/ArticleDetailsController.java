package controller.articleControllers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.WMS;
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
    private WMS wms;

    public ArticleDetailsController(WMS wms, Article article) {
        this.wms = wms;
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
        Callback<Class<?>, Object> test = param -> new ArticleEditFormController(wms, article);

        StageDependencyInjection.addInjectionMethod(
                ArticleEditFormController.class, test
        );

        Stage stage = StageDependencyInjection.load("fxml/articleViews/articleEditFormModal.fxml");

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
