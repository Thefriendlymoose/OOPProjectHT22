package controller.customerControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.Customer;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CustomerEditController implements Observer {

    public Label shippingAddressFlow;
    public Label billingAddressFlow;
    public GridPane contactPane;


    private CustomerEditor editor;

    @FXML
    TextField companyNameField, companyOrgNrField;

    public void initialize(){
        Platform.runLater(() -> {
            Customer c = editor.getCustomer();
            companyNameField.setText(c.getCompanyName());
            companyOrgNrField.setText(Long.toString(c.getCompanyOrgNumber()));
            editor.addContact(new CustomerContact("Bert", "223", "snopp.com"));
            editor.addContact(new CustomerContact("kuken", "1234", "bert.se"));
            update();
        });
    }

    public void setEditor(CustomerEditor editor){
        this.editor = editor;
    }


    public void addShippingAddressHandler(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/addressEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Create Shipping Address");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        AddressCreateController cont = loader.getController();
        editor.setShippingStrategy();
        cont.setEditor(editor);
        stage.show();
    }

    public void addBillingAddressHandler(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/addressEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Create Billing Address");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        AddressCreateController cont = loader.getController();
        editor.setBillingStrategy();
        cont.setEditor(editor);
        stage.show();
    }

    public void addContactHandler(ActionEvent e) throws IOException{
        //Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/customerViews/contactEdit.fxml")));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/contactEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Add Contact");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void saveBtnHandler(ActionEvent e) {
        editor.unregisterObserver(this);
    }

    @Override
    public void update() {
        Customer c = editor.getCustomer();
        billingAddressFlow.setText(c.getBillingAddress().toString());
        shippingAddressFlow.setText(c.getShippingAddress().toString());
        printContacts();
    }

    private void printContacts(){
        List<CustomerContact> contacts = editor.getCustomer().getContacts();
        for (int i = 0; i < contacts.size(); i++){
            CustomerContact c = contacts.get(i);
            Label person = new Label(c.getContactPerson());
            Label number = new Label(c.getPhoneNumber());
            Label email = new Label(c.getEmail());
            person.setText(c.getContactPerson());
            contactPane.add(person,0,i);
            contactPane.add(number,1,i);
            contactPane.add(email,2,i);
        }
    }
}
