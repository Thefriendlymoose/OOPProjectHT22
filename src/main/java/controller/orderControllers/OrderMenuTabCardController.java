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
import model.site.Site;

import java.io.IOException;

public class OrderMenuTabCardController {

    @FXML
    private Label orderNum, priority, status, amount, deadline;

    @FXML
    private Button openButton;

    private Order order;

    private Site site;

    public void setOrder(Order order){
        this.order = order;
    }

    public void setSite(Site site){
        this.site = site;
    }

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
                    controller.setSite(site);

                    stage.setTitle("Order: " + order.getOrderNumber());
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
                    stage.show();

                }
            });
        });
    }


}
