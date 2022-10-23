package controller.articlecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controller for the view which shows the article edit form.
 * Used when editing existing articles.
 *
 * @author David Al Amiri
 */
public class ArticleEditFormController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField numberTextField, nameTextField, costTextField, sellPriceTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ComboBox<ArticleCategory> categoryComboBox;

    @FXML
    private ComboBox<ArticleStatus> statusComboBox;


    private Article article;
    private WMS wms;

    public ArticleEditFormController(WMS wms, Article article) {
        this.wms = wms;
        this.article = article;
    }

    /**
     * Initializes the form with data from the provided article.
     */
    @FXML
    public void initialize(){
        titleLabel.setText("Edit Article: " + article.getArticleId());
        numberTextField.setText(String.valueOf(article.getArticleId()));
        nameTextField.setText(article.getArticleName());
        descriptionTextArea.setText(article.getDescription());
        categoryComboBox.getItems().setAll(ArticleCategory.values());
        statusComboBox.getItems().setAll(ArticleStatus.values());
        categoryComboBox.setValue(article.getCategory());
        statusComboBox.setValue(article.getStatus());
        costTextField.setText(String.valueOf(article.getCost()));
        sellPriceTextField.setText(String.valueOf(article.getSellPrice()));
    }

    /**
     * Handles the event when the save button in the form is pressed
     * @param e
     */
    public void onSave(ActionEvent e){
        // TODO: Need to check all fields, then create new object from data?
        if (nameTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || categoryComboBox.getValue() == null || statusComboBox.getValue() == null){

        } else {
            article.setArticleName(nameTextField.getText());
            article.setDescription(descriptionTextArea.getText());
            article.setCategory(categoryComboBox.getValue());
            article.setStatus(statusComboBox.getValue());
            article.setCost(Float.parseFloat(costTextField.getText()));
            article.setSellPrice(Float.parseFloat(sellPriceTextField.getText()));
            article.setLastEdited(LocalDateTime.now());

            wms.updateArticle();

            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }

    /**
     * Handles the event when the cancel button in the form is pressed.
     * Sends a confirmation modal to the user to confirm cancellation
     * @param e
     */
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
