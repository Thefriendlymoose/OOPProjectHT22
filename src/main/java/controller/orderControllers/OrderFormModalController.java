package controller.orderControllers;

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
import model.customer.Customer;
import model.customer.CustomerModel;
import model.order.*;
import model.site.Site;
import model.site.Sites;
import persistence.CustomersDAO;
import persistence.IPersistence;
import persistence.OrderDAO;

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
    @FXML
    private Button addOrderRowButton, saveButton, cancelButton;
    @FXML
    private ListView<OrderRow> orderRowListView;

    private ObservableList<OrderRow> addedRows;

    private DateFunctions df = new DateFunctions();
    private LocalDateTime deadline;
    private LocalDateTime orderDate;
    private Orders orders;
    private Site site;
    private CustomerModel customerModel;

    public OrderFormModalController(Orders orders, Site site, CustomerModel customerModel) {
        this.orders = orders;
        this.site = site;
        this.customerModel = customerModel;
    }

    /**
     * Gives the text fields
     */
    @FXML
    public void initialize(){

        addedRows = FXCollections.observableArrayList();
        orderRowListView.setItems(addedRows); //.toList() -> OrderList
        orderRowListView.setCellFactory(param -> new ListCell<OrderRow>(){
            @Override
            protected void updateItem(OrderRow s, boolean empty){
                super.updateItem(s, empty);

                if(empty || s == null || s.getArticle() == null){
                    setText(null);
                } else {
                    setText(s.getArticle().getArticleName() + "\\n " + s.getAmount() + "x");
                }
            }
        });

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

    public void saveOrder(ActionEvent e){
        System.out.println("orderRowListView.getItems().size(): " + orderRowListView.getItems().size());
            if (df.isValidDeadline(deadline) && customerComboBox.getValue() != null && statusComboBox.getValue() != null && priorityComboBox.getValue() != null && orderRowListView.getItems().size() > 0){
                orders.addOrder(new Order(null, orders.getNextOrderNumber(), customerComboBox.getValue(), statusComboBox.getValue(), priorityComboBox.getValue(), orderDate, deadline, addedRows.stream().toList(), site));
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        orders.updateOrder();
        }

    }


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

    public void onAddOrderRowButton(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/orderViews/orderDetailsAddModal.fxml"));
        Stage stage = loader.load();
        OrderDetailsAddModalController controller = loader.getController();
        controller.setSite(site);
        controller.setSiteArticles(site.getSiteArticles());
        controller.setObservableOrderRows(addedRows);


        stage.setTitle("Choose Article and Amount");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
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
        System.out.println("Customer: "+ customerComboBox.getValue());
    }

}
