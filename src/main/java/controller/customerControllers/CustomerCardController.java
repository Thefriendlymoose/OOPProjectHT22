package controller.customerControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.customer.Customer;
import model.customer.CustomerModel;

public class CustomerCardController {

    private CustomerModel model;
    private Customer customer;

    @FXML
    private Label idLabel;

    @FXML
    private Label companyLabel;

    public void setCustomer(Customer c){
        customer = c;
    }

    public void initialize(){
        Platform.runLater(() -> {
            idLabel.setText(Long.toString(customer.getCustomerID()));
            companyLabel.setText(customer.getCompanyName());
        });
    }

    private void setModel(CustomerModel model){
        this.model = model;
    }

    public void openButtonHandler(ActionEvent actionEvent) {

    }
}
