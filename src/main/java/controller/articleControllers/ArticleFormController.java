package controller.articleControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.article.Article;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.article.ArticlesFacade;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

public class ArticleFormController {

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

    private final ArticlesFacade facade = new ArticlesFacade();

    @FXML
    public void initialize(){
        titleLabel.setText("Create Article");

        // TODO: Need the model to autogenerate the article number/ID
        numberTextField.setText(String.valueOf(facade.getNextId()));

        categoryComboBox.getItems().setAll(facade.getCategories());
        statusComboBox.getItems().setAll(facade.getStatuses());

    }

    public void onSave(ActionEvent e){
        // TODO: Need to check all fields, then create new object from data?

        if (nameTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || categoryComboBox.getValue() == null || statusComboBox.getValue() == null){

        } else {
            Article art = new Article(facade.getNextId(), nameTextField.getText(), descriptionTextArea.getText(), categoryComboBox.getValue(),
                                      statusComboBox.getValue(), Float.parseFloat(costTextField.getText()), Float.parseFloat(sellPriceTextField.getText()),
                                        null , LocalDateTime.now(), LocalDateTime.now());

            facade.saveArticle(art);

            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }

    }

    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }
}
