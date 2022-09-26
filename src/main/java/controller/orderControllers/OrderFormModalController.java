package controller.orderControllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.order.OrderStatus;
import model.orderV2.OrderRow;
import model.site.Site;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class OrderFormModalController {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField numberTextField;

    @FXML
    private ComboBox priorityComboBox;

    @FXML
    private ComboBox<OrderStatus> statusComboBox;

    @FXML
    private DatePicker orderDeadlineDatePicker;

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

}
