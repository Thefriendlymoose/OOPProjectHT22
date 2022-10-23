package controller.articlecontrollers;

import controller.dpi.ParentDependencyInjection;
import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.WMS;
import model.article.Article;
import model.article.Articles;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the article menu view.
 *
 * @author David Al Amiri
 */
public class ArticleMenuController implements Observer {
    @FXML
    private Button backButton;

    @FXML
    private VBox articlesCardHolder;

    private Articles articles;
    private WMS wms;

    public ArticleMenuController(WMS wms) {
        this.wms = wms;
        this.articles = wms.getArticles();
        wms.registerObserver(this);
    }

    /**
     * Initializes the cards in the view.
     * Creates a card for each article in the wms
     * @throws IOException
     */
    public void initialize() throws IOException {
        loadCards();
    }

    /**
     * Creates and adds a card with data from the articles in the wms.
     * @throws IOException throws an expection when the FXML fails to load
     */
    private void loadCards() throws IOException {
        List<Article> arts = articles.getInList();
        articlesCardHolder.getChildren().clear();
        for (Article article : arts){

            Callback<Class<?>, Object> temp = params -> new ArticleMenuCardController(wms, article);
            ParentDependencyInjection.addInjectionMethod(
                    ArticleMenuCardController.class, temp
            );

            Parent pane = ParentDependencyInjection.load("fxml/articleViews/articleDetailsMenuCard.fxml");

            articlesCardHolder.getChildren().add(pane);
        }
    }

    /**
     * Handles the event when a user presses the back button which takes the user back to the main menu.
     * @throws Exception throws an execution when the FXML fails to load.
     */
    public void backBtnHandler() throws Exception{
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /**
     * Handles the event when a user presses the open button.
     * Opens a new modal where the user can input an article id to open the requested article
     * @param e
     * @throws IOException
     */
    public void openButtonHandler(ActionEvent e) throws IOException {
        Stage stage = StageDependencyInjection.load("fxml/articleViews/articleOpenDetailsModal.fxml");
        stage.setTitle("Open Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    /**
     * Handles the event when a user presses the create button.
     * Opens a new modal with an article form
     * @param e
     * @throws IOException
     */
    public void createButtonHandler(ActionEvent e) throws IOException {
        Stage stage = StageDependencyInjection.load("fxml/articleViews/articleFormModal.fxml");
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
