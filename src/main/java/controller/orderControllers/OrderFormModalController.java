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
import model.customer.Customer;
import model.order.*;
import model.site.Site;
import model.site.Sites;
import persistence.CustomersDAO;
import persistence.IPersistence;
import persistence.OrderDAO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;



import static model.order.OrderStatus.*;

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

    private Site site;
    private Sites sites;
    private ObservableList<OrderRow> addedRows;

    private DateFactory df = new DateFactory();
    private LocalDateTime deadline;
    private LocalDateTime orderDate;
    private Orders orders;

    public void setSite(Site site){
        this.site = site;
    }

    private IPersistence<Customer> customers = CustomersDAO.getInstance();

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

        Boolean [] priorities = {true,false};
        priorityComboBox.getItems().addAll(priorities);

        numberTextField.setText(Integer.toString((int) OrderDAO.getInstance().getNextId()));

        List<OrderStatus> orderStatusList = new ArrayList<>(EnumSet.allOf(OrderStatus.class));

        statusComboBox.getItems().addAll(orderStatusList);

        customerComboBox.getItems().addAll(customers.getAll());
    }

//    !amountTextField.getText().isEmpty()

    public void saveOrder(ActionEvent e){
        System.out.println("orderRowListView.getItems().size(): " + orderRowListView.getItems().size());
//        if (df.isValidDeadline(deadline) && !customerComboBox.getEditor().equals("Choose Customer")){
            if (df.isValidDeadline(deadline) && customerComboBox.getValue() != null && statusComboBox.getValue() != null && priorityComboBox.getValue() != null && orderRowListView.getItems().size() > 0){
                orders.addOrder(new Order(null, orders.getNextOrderNumber(), customerComboBox.getValue(), statusComboBox.getValue(), priorityComboBox.getValue(), orderDate, deadline, addedRows.stream().toList(), site));
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        orders.updateOrder();
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

    public void onAddOrderRowButtonOld(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderFormOrderRowModal.fxml")));
        Stage stage = loader.load();

        OrderFormOrderRowModalController controller = loader.getController();
        controller.setSiteArticles(site.getSiteArticles());
        controller.setObservableOrderRows(addedRows);

        stage.setTitle("Choose Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }

//    denna

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

    public void setSites(Sites sites) {
        this.sites = sites;
    }

    public void setOrders(Orders orders) {this.orders = orders;
    }
}
