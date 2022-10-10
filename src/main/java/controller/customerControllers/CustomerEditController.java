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
import model.customer.CustomerEditor;

import java.io.IOException;
import java.util.Objects;

public class CustomerEditController {

    public Label shippingAddressFlow;
    public Label billingAddressFlow;

    private CustomerEditor editor;

    @FXML
    TextField companyNameField, companyOrgNrField;

    public void initialize(){}

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
        cont.setAddress(editor.getShippingAddress());
        stage.show();
    }

    public void addBillingAddressHandler(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/customerViews/addressEdit.fxml"));
        Stage stage = loader.load();
        stage.setTitle("Create Billing Address");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        AddressCreateController cont = loader.getController();
        cont.setAddress(editor.getBillingAddress());
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
}
