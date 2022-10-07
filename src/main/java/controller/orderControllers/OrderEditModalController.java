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
import model.article.Article;
import model.order.DateFactory;
import model.order.Order;
import model.order.OrderRow;
import model.order.OrderStatus;
import model.site.Site;
import persistence.OrderDAO;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private DatePicker orderDeadlineDatePicker;
    @FXML
    private Button addOrderRowButton, saveButton, cancelButton;
    @FXML
    private ListView<OrderRow> orderRowListView;

    private Order order;
    private Site site;
    private ObservableList<OrderRow> addedRows;

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



}
