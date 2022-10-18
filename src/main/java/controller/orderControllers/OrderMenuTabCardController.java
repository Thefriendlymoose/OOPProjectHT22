package controller.orderControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.order.Order;
import model.order.Orders;


import java.io.IOException;

/**
 * Controller for the opening an order by pressing an "Open" button in the OrderMenu
 * on the Order cards.
 */

public class OrderMenuTabCardController {

    @FXML
    private Label orderNum, priority, status, amount, deadline;

    @FXML
    private Button openButton;

    private Order order;

    private Orders orders;

    public void setOrder(Order order){
        this.order = order;
    }
    public void setOrders(Orders orders){this.orders = orders;}

    /**
     * Creates a modal with order details for the orders that can be open.
     */

    public void initialize(){
        Platform.runLater(() -> {
            orderNum.setText(String.valueOf(order.getOrderNumber()));
            priority.setText(Boolean.toString(order.isPriority()));
            status.setText(order.getOrderStatus().toString());
            amount.setText(Integer.toString(order.getTotalAmount()));
            deadline.setText(order.getDeadline().toString());

            openButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/orderViews/orderDetailsModal.fxml"));
                    Stage stage;
                    try {
                        stage = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    OrderDetailsModalController controller = loader.getController();
                    controller.setOrder(order);
                    controller.setOrders(orders);

                    stage.setTitle("Order: " + order.getOrderNumber());
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.show();
                }
            });
        });
    }
}
