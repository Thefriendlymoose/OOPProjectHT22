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

import java.util.Optional;

public class ArticleEditFormController {

    @FXML
    Label titleLabel;

    @FXML
    TextField numberTextField, nameTextField, costTextField, sellPriceTextField;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    ComboBox<ArticleCategory> categoryComboBox;

    @FXML
    ComboBox<ArticleStatus> statusComboBox;

    @FXML
    Button saveButton, cancelButton;

    private Article art;

    @FXML
    public void initialize(){

        Platform.runLater(() -> {
            titleLabel.setText("Edit Article: " + art.getArticleId());
            numberTextField.setText(String.valueOf(art.getArticleId()));
            nameTextField.setText(art.getArticleName());
            descriptionTextArea.setText(art.getDescription());
            categoryComboBox.getItems().setAll(ArticleCategory.values());
            statusComboBox.getItems().setAll(ArticleStatus.values());
            categoryComboBox.setValue(art.getCategory());
            statusComboBox.setValue(art.getStatus());
            costTextField.setText(String.valueOf(art.getCost()));
            sellPriceTextField.setText(String.valueOf(art.getSellPrice()));
        });
    }

    public void setArticle(Article art) {
        this.art = art;
    }

    public void onSave(){
        // TODO: Need to check all fields, then create new object from data?

    }

    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("All changes made will be cancelled");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }
}
