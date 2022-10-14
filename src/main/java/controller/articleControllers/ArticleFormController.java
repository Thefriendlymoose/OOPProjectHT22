package controller.articleControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.article.*;

import java.time.LocalDateTime;
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

    private Articles articles;

    public ArticleFormController(Articles articles) {
        this.articles = articles;
    }

    @FXML
    public void initialize(){
        titleLabel.setText("Create Article");
        numberTextField.setText(String.valueOf(articles.getNextId()));
        categoryComboBox.getItems().setAll(articles.getCategories());
        statusComboBox.getItems().setAll(articles.getStatus());
    }

    public void onSave(ActionEvent e){
        // TODO: Need to check all fields, then create new object from data?

        if (nameTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || categoryComboBox.getValue() == null || statusComboBox.getValue() == null){

        } else {
            Article art = new Article(articles.getNextId(), nameTextField.getText(), descriptionTextArea.getText(), categoryComboBox.getValue(),
                                      statusComboBox.getValue(), Float.parseFloat(costTextField.getText()), Float.parseFloat(sellPriceTextField.getText()),
                                        null , LocalDateTime.now(), LocalDateTime.now());

            articles.addArticle(art);

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
