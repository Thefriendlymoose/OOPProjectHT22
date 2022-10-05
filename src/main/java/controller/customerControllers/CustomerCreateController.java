package controller.customerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class CustomerCreateController {

    @FXML
    Label titleLabel;

    @FXML
    TextField companyNameField, companyOrgNrField;

    @FXML
    Button saveButton, cancelButton, editShippingAddress, editBillingAddress, editContacts;

    public void addShippingAddressHandler(ActionEvent e) {
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/customerViews/addressCreate.fxml")));
        stage.setTitle("Create Address");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow());
        stage.show();
    }
}
