package controller.articleControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.article.Article;
import model.article.ArticlesFacade;
import persistance.ArticlesDAO;
import persistance.IPersistence;
import persistance.IPersistenceNew;
import persistance.JSONDao;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ArticleMenuController {
    @FXML
    private Button openArticleButton, createButton, listButton, backButton;

    @FXML
    private VBox articlesCardHolder;

    private ArticlesFacade facade = new ArticlesFacade();

    public void initialize() throws IOException {
        List<Article> articles = facade.getAll();
        ObservableList<Article> arts = FXCollections.observableArrayList(articles);

        for (Article art : articles){
            FXMLLoader cardLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleDetailsMenuCard.fxml")));

            AnchorPane pane = cardLoader.load();
            ArticleMenuCardController cont =  cardLoader.getController();

            cont.setCard(art);

            articlesCardHolder.getChildren().add(pane);
        }


    }

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void openButtonHandler(ActionEvent e) throws IOException {
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleOpenDetailsModal.fxml")));
        stage.setTitle("Open Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();
    }

    public void createButtonHandler(ActionEvent e) throws IOException {
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/articleViews/articleFormModal.fxml")));
        stage.setTitle("Create Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }



}
