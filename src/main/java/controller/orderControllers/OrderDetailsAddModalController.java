package controller.orderControllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.article.ArticlesFacade;
import model.order.OrderRow;
import model.order.Orders;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;
import persistence.IPersistence;
import persistence.SitesDAO;

import java.util.List;
import java.util.Optional;

import java.util.List;

public class OrderDetailsAddModalController {

    @FXML
    private Button addArticleButton, cancelButton;

    @FXML
    private TextField amountTextField;


    @FXML
    private ListView<SiteArticle> chooseArticleListView;

    private List<SiteArticle> siteArticles;

    private SiteArticle current;
    private Site site;
    private Sites sites;

    private ObservableList<OrderRow> observableOrderRows;



    private SitesDAO jsonDao = SitesDAO.getInstance();

    public void initialize(){

        Platform.runLater(() -> {
            chooseArticleListView.getItems().addAll(siteArticles);

            chooseArticleListView.setCellFactory(param -> new ListCell<SiteArticle>(){
                @Override
                protected void updateItem(SiteArticle s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getArticle() == null){
                        setText(null);
                    } else {
                        setText(s.getArticle().getArticleName() + "\\n " + s.getAmount() + "x");
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
                    site.addSiteArticle(new SiteArticle(current.getArticle(), amount));
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
    public void setSites(Sites sites) {
        this.sites = sites;
    }

    public void setSite(Site site) {
        this.site = site;
    }

//    public void setSiteArticles(SiteArticle siteArticles){
//        this.siteArticles = siteArticles;
//    }
}
