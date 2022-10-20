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
import model.customer.CustomerModel;
import model.order.*;
import model.site.Site;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Controller for creating an Order, which is accessed by pressing the "Create Order" button.
 */

public class OrderFormModalController {

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
    private LocalDateTime orderDate;
    private WMS wms;
    private Orders orders;
    private Site chosenSite;
    private CustomerModel customerModel;

    public OrderFormModalController(WMS wms, Site chosenSite) {
        this.wms = wms;
        this.chosenSite = chosenSite;
        this.customerModel = wms.getCustomerModel();
        this.orders = wms.getOrders();
    }

    /**
     * Gives the user alternatives when pressing each Combo Box
     */
    @FXML
    public void initialize(){


        priorityComboBox.getItems().addAll(orders.getAllPriorities());

        numberTextField.setText(Long.toString(orders.getNextOrderNumber()));

        List<OrderStatus> orderStatusList = new ArrayList<>(EnumSet.allOf(OrderStatus.class));

        statusComboBox.getItems().addAll(orderStatusList);

        customerComboBox.getItems().addAll(customerModel.getCustomerList());
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

    /**
     * Adds the Order if inputted correctly (no empty fields or invalid deadline)
     *
     * @param e is the Action Event
     */
    public void saveOrder(ActionEvent e) throws IOException {
        if (df.isValidDeadline(deadline) && customerComboBox.getValue() != null && statusComboBox.getValue() != null && priorityComboBox.getValue() != null ){
            Order newOrder = new Order(wms.getSession().getUser(), orders.getNextOrderNumber(), customerComboBox.getValue(), statusComboBox.getValue(), priorityComboBox.getValue(), orderDate, deadline, new ArrayList<>(), chosenSite);
            wms.addOrder(newOrder);

            StageDependencyInjection.addInjectionMethod(
                    OrderDetailsModalController.class, param -> new OrderDetailsModalController(wms, newOrder)
            );

            Stage stage = StageDependencyInjection.load("fxml/orderViews/orderDetailsModal.fxml");
            stage.setTitle("Site: " + newOrder.getOrderNumber());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
            stage.show();

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
        orderDate = df.createOrderDate();

        if(df.isValidDeadline(deadline)){
            System.out.println("Deadline: " + deadline.getDayOfMonth() + "-"+ deadline.getMonthValue() + "-" + deadline.getYear());
        }
    }

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

    public void setPriorityComboBox(){
        System.out.println("Priority: "+ priorityComboBox.getValue());
    }

    public void setStatusComboBox(){
        System.out.println("Status: "+ statusComboBox.getValue());
    }

    public void setCustomerComboBox(){
        System.out.println("Customer: "+ customerComboBox.getValue().getCompanyName());
    }

}
