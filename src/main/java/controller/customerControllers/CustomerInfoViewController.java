package controller.customerControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.customer.Customer;
import model.customer.CustomerContact;
import model.customer.CustomerModel;

import javax.swing.text.PlainDocument;

public class CustomerInfoViewController {

    @FXML
    private Label customerIdLabel, companyNameLabel, orgNrLabel, shippingAddressLabel, billingAddressLabel;

    @FXML
    private VBox contactBox;

    @FXML
    private Button editButton, closeButton;

    private Customer customer;

    public void initialize(){
        Platform.runLater(() -> {
            update();
        });
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void update(){
        customerIdLabel.setText(Long.toString(customer.getCustomerID()));
        companyNameLabel.setText(customer.getCompanyName());
        orgNrLabel.setText(Long.toString(customer.getCompanyOrgNumber()));
        shippingAddressLabel.setText(customer.getShippingAddress().toString());
        billingAddressLabel.setText(customer.getBillingAddress().toString());
        printContacts(customer);
    }

    private void printContacts(Customer c){
        contactBox.getChildren().clear();
        for (CustomerContact cc : c.getContacts()){
            Label contact = new Label(cc.toString());
            contactBox.getChildren().add(contact);
        }
    }
}
