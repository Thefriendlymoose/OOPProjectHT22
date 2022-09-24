package controller.articleControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;


public class ArticleDetailsController {
    @FXML
    Label detailsTitleLabel, numberLabel, nameLabel, descriptionLabel, categoryLabel, statusLabel;

    @FXML
    TextField numberTextField, nameTextField;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    ComboBox<ArticleCategory> categoryComboBox;

    @FXML
    ComboBox<ArticleStatus> statusComboBox;

    @FXML
    Button editButton, closeButton;


    private Article art;

    @FXML
    public void initialize(){

        Platform.runLater(() -> {
            detailsTitleLabel.setText(detailsTitleLabel.getText() + art.getArticleId());
            numberTextField.setText(String.valueOf(art.getArticleId()));
            nameTextField.setText(art.getArticleName());
            descriptionTextArea.setText(art.getDescription());
            categoryComboBox.setValue(art.getCategory());
            statusComboBox.setValue(art.getStatus());
            categoryComboBox.getItems().setAll(ArticleCategory.values());
            statusComboBox.getItems().setAll(ArticleStatus.values());
        });

    }

    public void setArticle(Article art){
        this.art = art;
    }

    public void editHandler() {

    }

    public void closeHandler(ActionEvent e) {
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    //TODO handlers for when user clicks edit and close buttons

}
