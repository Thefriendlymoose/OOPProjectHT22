package controller.ordercontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.order.Order;


import java.io.IOException;

/**
 * Controller for the opening an order by pressing an "Open" button in the OrderMenu
 * on the Order cards.
 *
 * @author James Pålsson
 * @author David al Amiri
 */

public class OrderMenuTabCardController {

    @FXML
    private Label orderNum;
    @FXML
    private Label priority;
    @FXML
    private Label status;
    @FXML
    private Label amount;
    @FXML
    private Label deadline;

    private Order order;

    private WMS wms;

    public OrderMenuTabCardController(WMS wms, Order order) {
        this.wms = wms;
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
                OrderDetailsModalController.class, params -> new OrderDetailsModalController(wms, order)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsModal.fxml");

        stage.setTitle("Order: " + order.getOrderNumber());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

}
