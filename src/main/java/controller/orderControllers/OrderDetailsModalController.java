package controller.orderControllers;

import controller.dpi.StageDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.WMS;
import model.customer.Customer;
import model.observer.Observer;
import model.order.Order;
import model.order.OrderRow;
import model.order.OrderStatus;

import java.io.IOException;

/**
 *  Controller when "Open Order" or "Open" button has been pressed in the Order Menu.
 *  The user may see what an Order consists of in this Modal, however all information is locked until
 *  "Edit" button is pressed.
 */

public class OrderDetailsModalController implements Observer {

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
    private TextField createdByTextField;

    @FXML
    private ListView<OrderRow> orderRowListView;

    @FXML
    private ComboBox<Customer> customerComboBox;

    private Order order;
    private WMS wms;

    public OrderDetailsModalController(WMS wms, Order order) {
        this.wms = wms;
        this.order = order;
        wms.registerObserver(this);
    }

    /**
     * Shows the uneditable information of the particular Order being opened.
     */

    public void initialize(){
        updateFields();

        titleLabel.setText(titleLabel.getText() + order.getOrderNumber());

        numberTextField.setText(String.valueOf(order.getOrderNumber()));

        statusComboBox.setDisable(true);
        statusComboBox.getStyleClass().add("locked-form-field");

        priorityComboBox.setDisable(true);
        priorityComboBox.getStyleClass().add("locked-form-field");

        orderDeadlineDatePicker.setDisable(true);
        orderDeadlineDatePicker.getStyleClass().add("locked-form-field");

        orderRowListView.setDisable(true);
        orderRowListView.getStyleClass().add("locked-form-field");

        createdByTextField.setDisable(true);

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

    /**
     * Takes the user to a new Modal where the Order can be edited.
     *
     * @param e is the ActionEvent.
     * @throws IOException is thrown if the stage doesn't exist.
     */

    public void onEdit(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderEditModalController.class, params -> new OrderEditModalController(wms, order)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderEditModal.fxml");

        stage.setTitle("Edit Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()));
        stage.show();

    }

    public void onClose(ActionEvent e){
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    public void onAddOrderRow(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderDetailsAddOrderRowController.class, param -> new OrderDetailsAddOrderRowController(wms, order)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsAddOrderRow.fxml");

        stage.setTitle("Add Order Row");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()));
        stage.show();

    }

    public void onRemoveOrderRow(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderDetailsReduceOrderRowController.class, param -> new OrderDetailsReduceOrderRowController(wms, order)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsReduceOrderRow.fxml");


        stage.setTitle("Reduce/Delete Order Row");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()));
        stage.show();
    }

    private void updateFields(){
        statusComboBox.setValue(order.getOrderStatus());
        priorityComboBox.setValue(order.isPriority());
        orderDeadlineDatePicker.setValue(order.getDeadline().toLocalDate());
        orderRowListView.getItems().clear();
        orderRowListView.getItems().addAll(order.getOrderRows());
        createdByTextField.setText(order.getUser().getName());
        customerComboBox.setValue(order.getCustomer());
    }

    @Override
    public void update() {
        updateFields();
    }
}

