package controller.customercontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.Customer;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;
import model.customer.CustomerModel;
import model.observer.Observer;

import java.io.IOException;

/**
 * Responsibility: controls the customer info view
 * Used by:
 * Uses: Customer, CustomerModel
 * @author Simon Porsgaard / doktorjevksy
 */

public class CustomerInfoViewController implements Observer {

    @FXML
    private Label customerIdLabel, companyNameLabel, orgNrLabel, shippingAddressLabel, billingAddressLabel;

    @FXML
    private VBox contactBox;

    private Customer customer;
    private CustomerModel model;

    public void initialize(){
        Platform.runLater(() -> {
            update();
        });
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public void setModel(CustomerModel model){
        this.model = model;
    }

    @Override
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

    public void editButtonHandler(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/customerEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Edit Customer");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
        CustomerEditController cont = loader.getController();

        CustomerEditor editor = model.editCustomer(customer);
        editor.registerObserver(cont);
        cont.setEditor(editor);
        stage.show();
    }

    public void closeButtonHandler(ActionEvent actionEvent) {
        model.unregisterObserver(this);
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void deleteCustomerHandler(ActionEvent actionEvent) {
        model.removeCustomer(customer);
        model.unregisterObserver(this);
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
