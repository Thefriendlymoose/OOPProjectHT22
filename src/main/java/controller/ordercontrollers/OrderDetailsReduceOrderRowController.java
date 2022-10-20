package controller.ordercontrollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.WMS;
import model.order.Order;
import model.order.OrderRow;

import java.util.Optional;

/**
 * Controller when the user is in the process of creating or editing and Order.
 */

public class OrderDetailsReduceOrderRowController {



    @FXML
    private Button confirmButton, cancelButton;

    @FXML
    private TextField amountTextField;


    @FXML
    private ListView<OrderRow> chooseArticleListView;

    private final Order order;
    private final WMS wms;
    private OrderRow current;



    public OrderDetailsReduceOrderRowController(WMS wms, Order order) {
        this.wms = wms;
        this.order = order;
    }

    /**
     * Shows all the Article and amount, which the user can select when creating or editing an order.
     */

    public void initialize(){
        chooseArticleListView.getItems().addAll(order.getOrderRows());

        chooseArticleListView.setCellFactory(param -> new ListCell<OrderRow>(){
            @Override
            protected void updateItem(OrderRow s, boolean empty){
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
                if (order.reduceOrderRow(current, amount)) {
                    System.out.println("success");
                    ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                    wms.updateOrder();
                } else {
                    System.out.println("Failed");
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

}
