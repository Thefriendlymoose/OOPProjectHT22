package controller.orderControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.order.Order;
import model.order.OrderRow;
import model.order.OrderStatus;
import model.site.SiteArticle;

public class OrderDetailsModalController {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField numberTextField;
    @FXML
    private ComboBox<OrderStatus> statusComboBox;
    @FXML
    private ComboBox<Boolean> priorityComboBox;
    @FXML
    private DatePicker orderDeadlineDatePicker;
    @FXML
    private ListView<OrderRow> orderRowListView;

    private Order order;

    public void setOrder(Order order){
        this.order = order;
    }

    public void initialize(){
        Platform.runLater(() -> {

            titleLabel.setText(titleLabel.getText() + order.getOrderNumber());

            numberTextField.setText(String.valueOf(order.getOrderNumber()));

            statusComboBox.setValue(order.getOrderStatus());
            statusComboBox.setDisable(true);
            statusComboBox.getStyleClass().add("locked-form-field");

            priorityComboBox.setValue(order.isPriority());
            priorityComboBox.setDisable(true);
            priorityComboBox.getStyleClass().add("locked-form-field");

            orderDeadlineDatePicker.setValue(order.getDeadline().toLocalDate());
            orderDeadlineDatePicker.setDisable(true);
            orderDeadlineDatePicker.getStyleClass().add("locked-form-field");

            orderRowListView.getItems().addAll(order.getOrderRows());
            orderRowListView.setDisable(true);
            orderRowListView.getStyleClass().add("locked-form-field");

            orderRowListView.setCellFactory(param -> new ListCell<OrderRow>(){
                @Override
                protected void updateItem(OrderRow s, boolean empty){
                    super.updateItem(s, empty);

                    if(empty || s == null || s.getArticle() == null){
                        setText(null);
                    } else {
                        setText(s.getArticle().getArticleName() + ", " + s.getAmount() + "x");
                    }
                }
            });

        });
    }

    public void onEdit(ActionEvent e){

    }

    public void onClose(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

}
