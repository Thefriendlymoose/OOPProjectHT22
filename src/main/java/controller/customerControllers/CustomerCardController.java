package controller.customerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.customer.CustomerModel;

public class CustomerCardController {

    private CustomerModel model;

    @FXML
    private Label idLabel;

    @FXML
    private Label companyLabel;

    private void setModel(CustomerModel model){
        this.model = model;
    }

    public void openButtonHandler(ActionEvent actionEvent) {
    }
}
