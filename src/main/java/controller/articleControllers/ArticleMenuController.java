package controller.articleControllers;

import controller.dpi.DependencyInjection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.article.Article;
import model.article.Articles;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ArticleMenuController implements Observer {
    @FXML
    private Button openArticleButton, createButton, listButton, backButton;

    @FXML
    private VBox articlesCardHolder;

    private Articles articles;
    private WMS wms;

    public ArticleMenuController(WMS wms) {
        this.wms = wms;
        this.articles = wms.getArticles();
        articles.registerObserver(this);
    }

    public void initialize() throws IOException {
        loadCards();
    }

    private void loadCards() throws IOException {
        List<Article> arts = articles.getInList();
        articlesCardHolder.getChildren().clear();
        for (Article art : arts){
            FXMLLoader cardLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleDetailsMenuCard.fxml")));

            AnchorPane pane = cardLoader.load();
            ArticleMenuCardController cont =  cardLoader.getController();

            cont.setArticle(art);
            cont.setArticles(articles);

            articlesCardHolder.getChildren().add(pane);
        }
    }

    public void backBtnHandler() throws Exception{
        Parent root = DependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void openButtonHandler(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleOpenDetailsModal.fxml")));
        Stage stage = loader.load();
        ArticleOpenDetailsModalController controller = loader.getController();
        controller.setArticles(articles);
        stage.setTitle("Open Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void createButtonHandler(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleFormModal.fxml")));
        Stage stage = loader.load();
        ArticleFormController controller = loader.getController();
        controller.setArticles(articles);
        stage.setTitle("Create Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }


    @Override
    public void update() {
        try {
            loadCards();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
