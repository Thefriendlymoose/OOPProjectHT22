package controller.customercontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.Customer;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;
import model.observer.Observer;

import java.io.IOException;
import java.util.List;

public class CustomerEditController implements Observer {

    @FXML
    private Label shippingAddressFlow, billingAddressFlow;

    @FXML
    private GridPane contactPane;


    private CustomerEditor editor;

    @FXML
    private TextField companyNameField, companyOrgNrField;

    public void initialize(){
        Platform.runLater(() -> {
            Customer c = editor.getCustomer();
            companyNameField.setText(c.getCompanyName());
            companyOrgNrField.setText(Long.toString(c.getCompanyOrgNumber()));
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
        //Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/customerViews/contactCreate.fxml")));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/contactEditorView.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Edit Contacts");

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        ContactEditorController cont = loader.getController();
        editor.registerObserver(cont);
        cont.setEditor(editor);
        stage.show();
    }

    // TODO validate fields
    public void saveBtnHandler(ActionEvent e) {
        editor.setCompanyName(companyNameField.getText());
        editor.setCompanyOrgNumber(Long.parseLong(companyOrgNrField.getText().strip()));
        editor.unregisterObserver(this);
        editor.save();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
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
        contactPane.getChildren().clear();
        contactPane.add(new Label("Name"),0,0);
        contactPane.add(new Label("Number"), 1,0);
        contactPane.add(new Label("Email"),2,0);
        for (int i = 0; i < contacts.size(); i++){
            CustomerContact c = contacts.get(i);
            Label person = new Label(c.getContactPerson());
            Label number = new Label(c.getPhoneNumber());
            Label email = new Label(c.getEmail());
            person.setText(c.getContactPerson());
            contactPane.add(person,0,i+1);
            contactPane.add(number,1,i+1);
            contactPane.add(email,2,i+1);
        }
    }

}
