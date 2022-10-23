package controller.sitecontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.article.Article;
import model.article.Articles;
import model.site.Site;

import java.util.Optional;

/**
 * Controller for the modal that opens when you want to add a site article to an existing site.
 *
 * @author David Al Amiri
 */
public class SiteDetailsSiteArticleAddModalController {

    @FXML
    private ListView<Article> chooseArticleListView;

    @FXML
    private TextField amountTextField;

    private Article current;

    private Site site;
    private WMS wms;
    private Articles articles;

    public SiteDetailsSiteArticleAddModalController(WMS wms, Site site) {
        this.wms = wms;
        this.site = site;
        this.articles = wms.getArticles();
    }

    /**
     * Initializes the Site Article modal.
     * Populates the listview with all the available articles in the wms.
     */
    public void initialize(){
        chooseArticleListView.getItems().addAll(articles.getInList());

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
    }

    /**
     * Handles the event when a user clicks save in the modal
     * @param e
     */
    public void onSave(ActionEvent e){
        if (current != null && !amountTextField.getText().isEmpty()){
            try {
                int amount = Integer.parseInt(amountTextField.getText());
                if (site.addSiteArticle(current, amount)){
                    ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                    wms.updateSite();
                } else {
                    //TODO add error popup
                    System.out.println("Failed");
                }
            } catch (NumberFormatException error){
                //TODO add error popup
                System.out.println("error number");
            }
        }
    }

    /**
     * Handles the event when a user clicks the cancel button in the modal
     * @param e
     */
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
