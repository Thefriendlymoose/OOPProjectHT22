package controller.orderControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.order.Order;
import persistence.IPersistence;
import persistence.OrderDAO;


public class OrderOpenController {

    @FXML
    private TextField searchField;

    @FXML
    private Button openButton;

    public void openOrder(){
        IPersistence<Order> orders = OrderDAO.getInstance();
        if(!searchField.getText().isEmpty()){
            try {
                Long id = Long.parseLong(searchField.getText());
                if(orders.getAllMap().containsKey(id)){

                } else {
                    System.out.println("Order does not exist");
                }
            } catch (NumberFormatException error){
                System.out.println("Number error");
            }
        } else {
            System.out.println("Field Empty");
        }

    }

}
