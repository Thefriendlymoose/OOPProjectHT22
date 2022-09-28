package controller.orderControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.order.DateFactory;
import model.order.OrderStatus;
import model.orderV2.OrderRow;
import model.site.Site;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Optional;

import static model.order.Order.CURRENTORDER;

public class OrderFormModalController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField numberTextField;

    @FXML
    private ComboBox<Boolean> priorityComboBox;


    public void setPriorityComboBox(){
        System.out.println("Priority: "+ priorityComboBox.getValue());
    }

    @FXML
    private ComboBox<OrderStatus> statusComboBox;

    public void setStatusComboBox(){
        System.out.println("Status: "+ statusComboBox.getValue());
    }

    @FXML
    private DatePicker orderDeadlineDatePicker;

    public void deadlineDatePicker(){
//        public void deadlineDatePicker(ActionEvent event){
//        Controller ska anropa detta, men inte utföra. Flyttar sen.

        int year = orderDeadlineDatePicker.getValue().getYear();
        int month = orderDeadlineDatePicker.getValue().getMonthValue();
        int day = orderDeadlineDatePicker.getValue().getDayOfMonth();
        DateFactory dateFactory = new DateFactory();

        GregorianCalendar orderDate = dateFactory.createDate();

        System.out.println("OrderDate: " + orderDate.get(Calendar.YEAR) +"-"+ orderDate.get(Calendar.MONTH) +"-"+orderDate.get(Calendar.DATE) );

        GregorianCalendar deadline = dateFactory.createDeadline(year,month,day);

        System.out.println("Deadline: " + deadline.get(Calendar.YEAR) +"-"+ deadline.get(Calendar.MONTH) +"-"+deadline.get(Calendar.DATE) );
    }

    @FXML
    private Button addOrderRowButton, saveButton, cancelButton;

    @FXML
    private TableView<OrderRow> addedOrderRowTableView;


    private Site site;
    private ObservableList<OrderRow> addedRows;


    public void setSite(Site site){
        this.site = site;
    }

    public void onAddOrderRowButton(ActionEvent e) throws IOException {
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderFormOrderRowModal.fxml")));
        stage.setTitle("Choose Article");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
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

    @FXML
    public void initialize(){

        Boolean [] priorities = {true,false};
        priorityComboBox.getItems().addAll(priorities);

        numberTextField.setText(Integer.toString(CURRENTORDER));

//        hard coded, should iterate over all enums in enum class
        OrderStatus [] orderStatuses = {OrderStatus.ACTIVE,OrderStatus.CANCELED,OrderStatus.FINISHED};
        statusComboBox.getItems().addAll(orderStatuses);
    }

}
