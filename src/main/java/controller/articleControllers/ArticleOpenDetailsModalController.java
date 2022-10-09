package controller.articleControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.article.Article;
import model.article.ArticlesFacade;

import java.io.IOException;
import java.util.Objects;

public class ArticleOpenDetailsModalController {

    @FXML
    private TextField modalSearchField;

    @FXML
    private Label warningLabel;

    private ArticlesFacade facade = new ArticlesFacade();
    public void modalOpenArticleButtonHandler(ActionEvent e) throws IOException {

        try {

            Article testArt = facade.findById(Long.parseLong(modalSearchField.getText()));

            if(testArt == null){
                warningLabel.setText("Can't find article");
            } else {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleDetailsModal.fxml")));
                Stage stage = loader.load();

                ArticleDetailsController cont = loader.getController();
                cont.setArticle(testArt);

                stage.setTitle("Article: " + testArt.getArticleId());
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
