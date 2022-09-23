package controller.articleControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    public void initialize(){

        // TODO Ask model for data user is asking for and present by populating the fields

        detailsTitleLabel.setText(detailsTitleLabel.getText() + "test");
        numberTextField.setText("123456789");
        nameTextField.setText("Test Article");
        descriptionTextArea.setText("This is a test description of the test article, it is 0x0x0");
        categoryComboBox.setValue(ArticleCategory.Office);
        statusComboBox.setValue(ArticleStatus.Active);
        categoryComboBox.getItems().setAll(ArticleCategory.values());
        statusComboBox.getItems().setAll(ArticleStatus.values());

    }

    //TODO handlers for when user clicks edit and close buttons

}
