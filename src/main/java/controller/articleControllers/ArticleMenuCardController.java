package controller.articleControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.article.Article;
import model.article.Articles;

import java.io.IOException;
import java.util.Objects;


public class ArticleMenuCardController {

    @FXML
    private Label cardNumberLabel, cardNameLabel, cardDescriptionLabel;

    @FXML
    private Button cardGoToButton;

    private Article art;
    private Articles arts;


    public void setArticle(Article art){
        this.art = art;
    }
    public void setArticles(Articles arts) {
        this.arts = arts;
    }

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            cardNumberLabel.setText(cardNumberLabel.getText() + art.getArticleId());
            cardNameLabel.setText(cardNameLabel.getText() + art.getArticleName());
            cardDescriptionLabel.setText(cardDescriptionLabel.getText() + art.getDescription());

            cardGoToButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleDetailsModal.fxml")));
                    Stage stage = null;
                    try {
                        stage = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    ArticleDetailsController cont = loader.getController();
                    cont.setArticle(art);
                    cont.setArticles(arts);
                    stage.setTitle("Article: " + art.getArticleId());
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                }
            });
        });
    }

}
