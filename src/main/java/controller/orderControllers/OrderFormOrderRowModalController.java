package controller.orderControllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.article.Article;
import model.order.OrderRow;
import model.site.SiteArticle;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                        setText(s.getArticle().getArticleName() + "\\n " + s.getAmount() + "x");
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
        observableOrderRows.add(new OrderRow(current.getArticle(), 5));
    }

}