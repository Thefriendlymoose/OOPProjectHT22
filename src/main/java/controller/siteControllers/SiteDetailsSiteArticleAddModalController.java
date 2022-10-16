package controller.siteControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.article.Article;
import model.article.ArticlesFacade;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;

import java.util.Optional;

public class SiteDetailsSiteArticleAddModalController {

    @FXML
    private ListView<Article> chooseArticleListView;

    @FXML
    private TextField amountTextField;

    @FXML
    private Button saveButton, CancelButton;

    private Article current;

    private Site site;
    private Sites sites;

    public SiteDetailsSiteArticleAddModalController(Sites sites, Site site) {
        this.sites = sites;
        this.site = site;
    }

    public void initialize(){
        Platform.runLater(() -> {
            chooseArticleListView.getItems().addAll(new ArticlesFacade().getAll());

            chooseArticleListView.setCellFactory(param -> new ListCell<Article>(){
                @Override
                protected void updateItem(Article s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getArticleName() == null){
                        setText(null);
                    } else {
                        setText(s.getArticleName());
                    }
                }
            });


            chooseArticleListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
                current = chooseArticleListView.getSelectionModel().getSelectedItem();
            });
        });
    }

    public void onSave(ActionEvent e){
        if (current != null && !amountTextField.getText().isEmpty()){
            try {
                int amount = Integer.parseInt(amountTextField.getText());
                if (site.checkIfOverCapacity(amount)){
                    System.out.println("over capacity");
                } else {
                    site.addSiteArticle(new SiteArticle(current, amount));
                    ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                    sites.updateSite();
                }
            } catch (NumberFormatException error){
                System.out.println("error number");
            }
        }
    }

    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("All changes made will be cancelled");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }

}
