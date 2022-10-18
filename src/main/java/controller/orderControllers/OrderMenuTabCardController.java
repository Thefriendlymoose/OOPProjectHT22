package controller.orderControllers;

import controller.dpi.StageDependencyInjection;
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

    public OrderMenuTabCardController(Orders orders, Order order) {
        this.orders = orders;
        this.order = order;
    }

    /**
     * Creates a modal with order details for the orders that can be open.
     */

    public void initialize(){
        orderNum.setText(String.valueOf(order.getOrderNumber()));
        priority.setText(Boolean.toString(order.isPriority()));
        status.setText(order.getOrderStatus().toString());
        amount.setText(Integer.toString(order.getTotalAmount()));
        deadline.setText(order.getDeadline().toString());
    }

    public void onOpen(ActionEvent e) throws IOException{

        StageDependencyInjection.addInjectionMethod(
                OrderDetailsModalController.class, params -> new OrderDetailsModalController(orders, order)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsModal.fxml");

        stage.setTitle("Order: " + order.getOrderNumber());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

}
