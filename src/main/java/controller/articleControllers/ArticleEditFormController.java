package controller.articleControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.article.ArticleCategory;
import model.article.ArticleStatus;

public class ArticleEditFormController {

    @FXML
    Label titleLabel;

    @FXML
    TextField numberTextField, nameTextField;

    @FXML
    TextArea descriptionTextArea;

    @FXML
    ComboBox<ArticleCategory> categoryComboBox;

    @FXML
    ComboBox<ArticleStatus> statusComboBox;

    @FXML
    Button saveButton, cancelButton;


    @FXML
    public void initialize(){
        titleLabel.setText("Create Article");

        // TODO: Need the model to autogenerate the article number/ID
        numberTextField.setText("123451234");

        categoryComboBox.getItems().setAll(ArticleCategory.values());
        statusComboBox.getItems().setAll(ArticleStatus.values());

    }

    public void onSave(){
        // TODO: Need to check all fields, then create new object from data?

    }

    public void onCancel(){
        // TODO prompt user ask if sure
    }
}
