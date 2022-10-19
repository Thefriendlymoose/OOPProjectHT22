package controller.orderControllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.order.OrderRow;
import model.site.Site;
import model.site.SiteArticle;
import java.util.List;
import java.util.Optional;

/**
 * Controller when the user is in the process of creating or editing and Order.
 */

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

    private ObservableList<OrderRow> observableOrderRows;

    /**
     * Shows all the Article and amount, which the user can select when creating or editing an order.
     */

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
                        setText(s.getArticle().getArticleName() + " " + s.getAmount() + "x");
                    }
                }
            });
            chooseArticleListView.getSelectionModel().selectedItemProperty().addListener((observableValue, site, t1) -> {
                current = chooseArticleListView.getSelectionModel().getSelectedItem();
            });
        });
    }

    /**
     * Make sure there is wrong input from user.
     *
     * @param e is ActionEvent
     */

    public void onSave(ActionEvent e){
        if (current != null && !amountTextField.getText().isEmpty()){
            try {
                int amount = Integer.parseInt(amountTextField.getText());
                if ((amount > current.getAmount() ) || ( amount == 0 )){
                    System.out.println("Not enough in stock or equal to 0");
                } else {
                    observableOrderRows.add(new OrderRow(current.getArticle(), amount));
                    current.setAmount(current.getAmount() - amount);
                    ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
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

    public void setSite(Site site) {
        this.site = site;
    }

    public void setSiteArticles(List<SiteArticle> siteArticles){
        this.siteArticles = siteArticles;
    }

    public void setObservableOrderRows(ObservableList<OrderRow> addedRows) {
        this.observableOrderRows = addedRows;
    }
}
