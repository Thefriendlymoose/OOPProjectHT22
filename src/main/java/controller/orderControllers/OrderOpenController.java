package controller.orderControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.order.Order;
import model.order.Orders;
import persistence.IPersistence;
import persistence.OrderDAO;


import java.io.IOException;


public class OrderOpenController {

    private Orders orders;
    @FXML
    private TextField searchField;

    @FXML
    private Button openButton;

    public void openOrder(ActionEvent event){
        IPersistence<Order> orders = OrderDAO.getInstance();
        if(!searchField.getText().isEmpty()){
            try {
                Long id = Long.parseLong(searchField.getText());
                if(orders.getAllMap().containsKey(id)){
                    Order order = orders.findById(id);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/orderViews/orderDetailsModal.fxml"));
                    Stage stage = loader.load();
                    OrderDetailsModalController controller = loader.getController();
                    controller.setOrder(order);

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

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
}
