package controller.orderControllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.order.OrderRow;
import model.site.SiteArticle;

import java.util.List;
import java.util.Optional;

/**
 * Controller for choosing an article through previous cards.
 */

public class OrderFormOrderRowModalController {

    @FXML
    private Button addArticleButton, cancelButton;

    @FXML
    private ListView<SiteArticle> siteArtListView;

    private List<SiteArticle> siteArticles;

    private SiteArticle current;

    private ObservableList<OrderRow> observableOrderRows;


    public void initialize(){

        Platform.runLater(() -> {
            siteArtListView.getItems().addAll(siteArticles);

            siteArtListView.setCellFactory(param -> new ListCell<SiteArticle>(){
                @Override
                protected void updateItem(SiteArticle s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getArticle() == null){
                        setText(null);
                    } else {
                        setText(s.getArticle().getArticleName() + " " + s.getAmount() + "x");
                    }
                }
            });
            siteArtListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
                current = siteArtListView.getSelectionModel().getSelectedItem();
            });
        });
    }

    public void cancelBtnHandler(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, another Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }
    public void setSiteArticles(List<SiteArticle> siteArticles){
        this.siteArticles = siteArticles;
    }

    public void setObservableOrderRows(ObservableList<OrderRow> oro){
        this.observableOrderRows = oro;
    }


    public void onAddArticleButton(){
        observableOrderRows.add(new OrderRow(current.getArticle(), current.getAmount()));

    }



}
