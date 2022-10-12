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
import model.article.Article;
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

//    private List<Article> articles;

//    List<Order> orders = new ArrayList<>();
    private Site site;
    private Sites sites;
    private ObservableList<OrderRow> addedRows;
    private Orders orders;

    public void setSite(Site site){
        this.site = site;
    }

    private IPersistence<Customer> customers = CustomersDAO.getInstance();
    @FXML
    public void initialize(){
        addedRows = FXCollections.observableArrayList();
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

        Boolean [] priorities = {true,false};
        priorityComboBox.getItems().addAll(priorities);

        numberTextField.setText(Integer.toString((int) OrderDAO.getInstance().getNextId()));

        OrderStatus [] orderStatuses = {ACTIVE,OrderStatus.CANCELED,OrderStatus.FINISHED};
        statusComboBox.getItems().addAll(orderStatuses);

        customerComboBox.getItems().addAll(customers.getAll());
    }

    public void saveOrder(){

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
        DateFactory df = new DateFactory();

        int day = orderDeadlineDatePicker.getValue().getDayOfMonth();
        int month = orderDeadlineDatePicker.getValue().getMonthValue();
        int year = orderDeadlineDatePicker.getValue().getYear();

        LocalDateTime deadline = df.createDeadline(day,month,year);

        if(df.isValidDeadline(deadline)){
            System.out.println("Deadline: " + deadline.getDayOfMonth() + "-"+ deadline.getMonthValue() + "-" + deadline.getYear());
        }
    }

    public void onAddOrderRowButton(ActionEvent e) throws IOException {
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
