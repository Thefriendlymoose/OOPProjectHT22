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

    private long customerId;

    private CustomerModel model;

    public void initialize(){
        Platform.runLater(() -> {
        });
    }

    public void setCustomerId(Long id){
        this.customerId = id;
    }

    public void setModel(CustomerModel model){
        this.model = model;
    }

    public void update(){
        Customer c = model.getCustomerById(customerId);
        customerIdLabel.setText(Long.toString(customerId));
        companyNameLabel.setText(c.getCompanyName());
        orgNrLabel.setText(Long.toString(c.getCompanyOrgNumber()));
        shippingAddressLabel.setText(c.getShippingAddress().toString());
        billingAddressLabel.setText(c.getBillingAddress().toString());
        printContacts(c);
    }

    private void printContacts(Customer c){
        contactBox.getChildren().clear();
        for (CustomerContact cc : c.getContacts()){
            Label contact = new Label(cc.getContactPerson());
            contactBox.getChildren().add(contact);
        }
    }
}
