package controller.articlecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.WMS;
import model.article.Article;

import java.io.IOException;

/**
 * Controller for the modal which open when you click open article in the menu.
 * Used to find an article by id.
 *
 * @author David Al Amiri
 */
public class ArticleOpenDetailsModalController {

    @FXML
    private TextField modalSearchField;

    @FXML
    private Label warningLabel;

    private WMS wms;

    public ArticleOpenDetailsModalController(WMS wms) {
        this.wms = wms;
    }

    /**
     * Handles the event when the user presses to open an article.
     * Parses the string which is returned from the search field and tries to find the article in the wms.
     * @param e
     * @throws IOException
     */
    public void modalOpenArticleButtonHandler(ActionEvent e) throws IOException {

        try {

            Article article = wms.getArticles().findById(Long.parseLong(modalSearchField.getText()));

            if(article == null){
                warningLabel.setText("Can't find article");
            } else {
                Callback<Class<?>, Object> test = param -> new ArticleDetailsController(wms, article);

                StageDependencyInjection.addInjectionMethod(
                        ArticleDetailsController.class, test
                );

                Stage stage = StageDependencyInjection.load("fxml/articleViews/articleDetailsModal.fxml");

                stage.setTitle("Article: " + article.getArticleId());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
                stage.show();
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

            }

        } catch (NumberFormatException error){
            warningLabel.setText("Article ID needs to only contain numbers");
        }
    }
}
