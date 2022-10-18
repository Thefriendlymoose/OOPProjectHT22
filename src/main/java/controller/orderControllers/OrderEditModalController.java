package controller.orderControllers;

import javafx.application.Platform;
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
import model.order.*;
import persistence.CustomersDAO;
import persistence.IPersistence;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

//import static model.order.OrderStatus.ACTIVE;

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

    private IPersistence<Customer> dao = CustomersDAO.getInstance();

    private Order order;
    private Orders orders;
    private ObservableList<OrderRow> addedRows;

    public OrderEditModalController(Orders orders, Order order) {
        this.orders = orders;
        this.order = order;
    }


    @FXML
    public void initialize(){
        titleLabel.setText("Order: " + order.getOrderNumber());
        numberTextField.setText(Long.toString(order.getOrderNumber()));
        priorityComboBox.setValue(order.isPriority());
        statusComboBox.setValue(order.getOrderStatus());
        customerComboBox.setValue(order.getCustomer());
        customerComboBox.getItems().addAll(dao.getAll());

        addedRows = FXCollections.observableArrayList(order.getOrderRows());

        orderRowListView.setItems(addedRows);
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
        System.out.println("Customer: "+ customerComboBox.getValue());
    }


    public void onAddOrderRowButton(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/orderViews/orderDetailsAddModal.fxml"));
        Stage stage = loader.load();
        OrderDetailsAddModalController controller = loader.getController();
        controller.setSite(order.getSite());
        controller.setSiteArticles(order.getSite().getSiteArticles());
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
            orders.updateOrder();
        }
    }

    public void deadlineDatePicker(){


        int day = orderDeadlineDatePicker.getValue().getDayOfMonth();
        int month = orderDeadlineDatePicker.getValue().getMonthValue();
        int year = orderDeadlineDatePicker.getValue().getYear();
        
        deadline = df.createDeadline(day,month,year);

//        if(df.isValidDeadline(deadline)){
//            System.out.println("today: " + LocalDateTime.now().getDayOfMonth() + "-"+ LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getYear());
//            System.out.println("deadline: " + deadline.getDayOfMonth() + "-"+ deadline.getMonthValue() + "-" + deadline.getYear());
//        }
    }

    public void saveOrder(ActionEvent e){
        System.out.println("before: " + orders);

        if (df.isValidDeadline(deadline)){
            order.setUser(null);
            order.setCustomer(customerComboBox.getValue());
            order.setOrderStatus(statusComboBox.getValue());
            order.setPriority(priorityComboBox.getValue());
            order.setDeadline(deadline);
            order.setOrderRows(addedRows.stream().toList());
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
            orders.updateOrder();
        } else {
            System.out.println("after: ");
        }
    }

}
