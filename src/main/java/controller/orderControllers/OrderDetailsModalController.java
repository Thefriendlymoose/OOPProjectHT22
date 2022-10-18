package controller.orderControllers;

import controller.dpi.StageDependencyInjection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.customer.Customer;
import model.order.Order;
import model.order.OrderRow;
import model.order.OrderStatus;
import model.order.Orders;
import model.site.Site;
import model.site.Sites;

import java.io.IOException;

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

    @FXML
//    private ComboBox<Customer> customerComboBox;
    private ComboBox<Customer> customerComboBox;

    private Order order;

    private Orders orders;

    public OrderDetailsModalController(Orders orders, Order order) {
        this.orders = orders;
        this.order = order;
    }

    public void initialize(){
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

            customerComboBox.setValue(order.getCustomer());
            customerComboBox.setDisable(true);
            customerComboBox.getStyleClass().add("locked-form-field");
            customerComboBox.setConverter(new StringConverter<Customer>() {
                @Override
                public String toString(Customer c) {
                    return c.getCompanyName();
                }

                @Override
                public Customer fromString(String c) {
                    return null;
                }
            });

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
    }

    public void onEdit(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderEditModalController.class, params -> new OrderEditModalController(orders, order)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderEditModal.fxml");

        stage.setTitle("Edit Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();

        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }

    public void onClose(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }


}

