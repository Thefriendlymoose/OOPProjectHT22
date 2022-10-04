package controller.orderControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.site.SiteArticle;

import java.util.List;

public class OrderFormOrderRowModalController {

    @FXML
    private Button addArticleButton, cancelButton;

    @FXML
    private ListView<SiteArticle> siteArtListView;

    private List<SiteArticle> siteArticles;

    private SiteArticle current;

    public void initialize(){
        Platform.runLater(() -> {
            siteArtListView.setCellFactory(param -> new ListCell<SiteArticle>(){
                @Override
                protected void updateItem(SiteArticle s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getArticle() == null){
                        setText(null);
                    } else {
                        setText(s.getArticle().getArticleName() + "----- Amount: " + s.getAmount());
                    }
                }
            });
            siteArtListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
                current = siteArtListView.getSelectionModel().getSelectedItem();
            });
        });
    }

    public void setSiteArticles(List<SiteArticle> siteArticles){
        this.siteArticles = siteArticles;
    }

    public void onAddArticleButton(){

    }

}
