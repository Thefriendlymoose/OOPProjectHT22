package controller.articlecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.article.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Controller for the view which shows the create article form.
 * Used when creating new articles.
 *
 * @author David Al Amiri
 */
public class ArticleFormController {

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

    private WMS wms;
    private Articles articles;

    public ArticleFormController(WMS wms) {
        this.wms = wms;
        this.articles = wms.getArticles();
    }

    /**
     * Initializes the form with data
     */
    @FXML
    public void initialize(){
        titleLabel.setText("Create Article");
        numberTextField.setText(String.valueOf(articles.getNextId()));
        categoryComboBox.getItems().setAll(articles.getCategories());
        statusComboBox.getItems().setAll(articles.getStatus());
    }

    /**
     * Handles the event when the save button in the form is pressed
     * @param e
     */
    public void onSave(ActionEvent e){
        // TODO: Need to check all fields, then create new object from data?

        if (nameTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || descriptionTextArea.getText().isEmpty() || categoryComboBox.getValue() == null || statusComboBox.getValue() == null){

        } else {
            Article art = new Article(articles.getNextId(), nameTextField.getText(), descriptionTextArea.getText(), categoryComboBox.getValue(),
                                      statusComboBox.getValue(), Float.parseFloat(costTextField.getText()), Float.parseFloat(sellPriceTextField.getText()),
                                        wms.getSession().getUser(), LocalDateTime.now(), LocalDateTime.now());

            wms.addArticle(art);

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
