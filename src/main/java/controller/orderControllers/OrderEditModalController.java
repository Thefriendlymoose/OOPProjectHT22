package controller.orderControllers;

import controller.dpi.StageDependencyInjection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.WMS;
import model.customer.Customer;
import model.customer.CustomerModel;
import model.order.*;
import persistence.CustomersDAO;
import persistence.IPersistence;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * Controller for the editing an existing order.
 *
 */

public class OrderEditModalController {

    @FXML
    private Label titleLabel;
    @FXML
    private TextField numberTextField;
    @FXML
    private ComboBox<Boolean> priorityComboBox;
    @FXML
    private ComboBox<OrderStatus> statusComboBox;
    @FXML
    private ComboBox<Customer> customerComboBox;
    @FXML
    private DatePicker orderDeadlineDatePicker;

    private DateFunctions df = new DateFunctions();

    private LocalDateTime deadline;

    @FXML
    private Button addOrderRowButton, saveButton, cancelButton;
    @FXML
    private ListView<OrderRow> orderRowListView;

    private Order order;
    private WMS wms;
    private Orders orders;

    private CustomerModel customers;


    public OrderEditModalController(WMS wms, Order order) {
        this.wms = wms;
        this.order = order;
        this.orders = wms.getOrders();
        this.customers = wms.getCustomerModel();
        deadline = order.getDeadline();
    }

    /**
     * Sets the text fields upon start from the active order.
     */

    @FXML
    public void initialize(){
        titleLabel.setText("Order: " + order.getOrderNumber());
        numberTextField.setText(Long.toString(order.getOrderNumber()));
        priorityComboBox.setValue(order.isPriority());
        statusComboBox.setValue(order.getOrderStatus());
        customerComboBox.setValue(order.getCustomer());
        customerComboBox.getItems().addAll(customers.getCustomerList());

/*
        orderRowListView.setItems(addedRows);
        orderRowListView.setCellFactory(param -> new ListCell<OrderRow>(){
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
*/
        priorityComboBox.getItems().addAll(orders.getAllPriorities());

        List<OrderStatus> orderStatusList = new ArrayList<>(EnumSet.allOf(OrderStatus.class));
        statusComboBox.getItems().addAll(orderStatusList);

        orderDeadlineDatePicker.setValue(order.getDeadline().toLocalDate());

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

    }


    public void setPriorityComboBox(){
        System.out.println("Priority: "+ priorityComboBox.getValue());
    }

    public void setStatusComboBox(){
        System.out.println("Status: "+ statusComboBox.getValue());
    }

    public void setCustomerComboBox(){
        System.out.println("Customer: "+ customerComboBox.getValue().getCompanyName());
    }

    /**
     * Takes user to modal where user can select Article and amount
     *
     * @param e is the Action Event
     * @throws IOException throws is stage doesn't exist.
     */

/*    public void onAddOrderRowButton(ActionEvent e) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                OrderDetailsAddModalController.class, params -> new OrderDetailsAddModalController(order.getSite(), addedRows)
        );

        Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsAddOrderRow.fxml");

        stage.setTitle("Choose Article and Amount");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }*/


    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }

    /**
     * Sets the date by using the calendar in the Create Order Menu
     */

    public void deadlineDatePicker(){
        int day = orderDeadlineDatePicker.getValue().getDayOfMonth();
        int month = orderDeadlineDatePicker.getValue().getMonthValue();
        int year = orderDeadlineDatePicker.getValue().getYear();
        
        deadline = df.createDeadline(day,month,year);
    }

    /**
     * Makes sure the deadline is chosen correctly, i.e., set to a future date.
     *
     * Since the user was directed here from an existing Order, then that Order must be
     * correctly inputted, otherwise it can't have been created in the first place.
     * However, if it's an error from the .JSON file, that is another question.
     *
     * @param e is the Action Event
     *
     */

    public void saveOrder(ActionEvent e){
        System.out.println("before: " + orders.getInList().size());
        try {
            if (df.isValidDeadline(deadline)){
                order.setCustomer(customerComboBox.getValue());
                order.setOrderStatus(statusComboBox.getValue());
                order.setPriority(priorityComboBox.getValue());
                order.setDeadline(deadline);
                ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
                wms.updateOrder();
            } else {
                System.out.println("your date is bajs");
            }
            System.out.println("after: " + orders.getInList().size());
        } catch(NullPointerException np){
            System.out.println("Future deadlines only!");
        }
    }


}
