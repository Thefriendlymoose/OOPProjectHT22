package controller.ordercontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;
import model.order.Order;
import model.order.Orders;


import java.io.IOException;

/**
 * Controller for opening an order by pressing the "Open Order" button in the OrderMenu
 * on the top left, after having provided an order number in the text field.
 */

public class OrderOpenController {

    private WMS wms;
    private Orders orders;
    @FXML
    private TextField searchField;

    public OrderOpenController(WMS wms) {
        this.wms = wms;
        this.orders = wms.getOrders();

    }

    /**
     * Creates a modal with order details for the orders that can be open.
     */

    public void openOrder(ActionEvent event){

        if(!searchField.getText().isEmpty()){
            try {
                Long id = Long.parseLong(searchField.getText());
                if(orders.checkIfExist(id)){
                    Order order = orders.findById(id);

                    StageDependencyInjection.addInjectionMethod(
                        OrderDetailsModalController.class, params -> new OrderDetailsModalController(wms, order)
                    );

                    Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsModal.fxml");
                    stage.setTitle("Order: " + order.getOrderNumber());
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Stage) ((Node)event.getSource()).getScene().getWindow()).getOwner());
                    stage.show();
                    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();


                } else {
                    System.out.println("Order does not exist");
                }
            } catch (NumberFormatException error){
                System.out.println("Number error");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Field Empty");
        }

    }
}
