package controller.customerControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.customer.Customer;
import model.customer.CustomerEditor;
import model.customer.Observer;

import java.io.IOException;
import java.util.Objects;

public class CustomerEditController implements Observer {

    public Label shippingAddressFlow;
    public Label billingAddressFlow;

    private CustomerEditor editor;

    @FXML
    TextField companyNameField, companyOrgNrField;

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
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/customerViews/contactEdit.fxml")));
        stage.setTitle("Add Contact");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }

    public void saveBtnHandler(ActionEvent e) {

    }

    @Override
    public void update() {
        Customer c = editor.getCustomer();
        billingAddressFlow.setText(c.getBillingAddress().toString());
        shippingAddressFlow.setText(c.getShippingAddress().toString());
    }
}
