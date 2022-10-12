package controller.orderControllers;

import controller.siteControllers.siteDetailsSiteArticleAddModalController;
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
import model.customer.Customer;
import model.order.*;
import model.site.Site;
import model.site.SiteArticle;
import model.site.Sites;
import persistence.CustomersDAO;
import persistence.IPersistence;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static model.order.OrderStatus.ACTIVE;

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
    @FXML
    private Button addOrderRowButton, saveButton, cancelButton;
    @FXML
    private ListView<OrderRow> orderRowListView;

    private IPersistence<Customer> dao = CustomersDAO.getInstance();

    private Order order;
    private Orders orders;
    private Site site;
    private Sites sites;
    private ObservableList<OrderRow> addedRows;
    private List<SiteArticle> siteArticles;

    public void setOrder(Order order){
        this.order = order;
    }

    public void setSite(Site site){
        this.site = site;
    }

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            numberTextField.setText(Long.toString(order.getOrderNumber()));
            priorityComboBox.setValue(order.isPriority());
            statusComboBox.setValue(order.getOrderStatus());
            customerComboBox.setValue(order.getCustomer());

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

            Boolean [] priorities = {true,false};
            priorityComboBox.getItems().addAll(priorities);


            OrderStatus [] orderStatuses = {ACTIVE,OrderStatus.CANCELED,OrderStatus.FINISHED};
            statusComboBox.getItems().addAll(orderStatuses);

            orderDeadlineDatePicker.setValue(order.getDeadline().toLocalDate());

        });
    }

    public void saveOrder(){}

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

    public void onAddOrderRowButtonNew(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/orderViews/orderDetailsAddModal.fxml"));
        Stage stage = loader.load();
        OrderEditModalController controller = loader.getController();
        controller.setSite(site);
        controller.setSites(sites);
        controller.setSiteArticles(site.getSiteArticles());


        stage.setTitle("Add Stock Item");
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

    public void setSites(Sites sites) {
        this.sites = sites;
    }
    public void setSiteArticles(List<SiteArticle> siteArticles){
        this.siteArticles = siteArticles;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

}
