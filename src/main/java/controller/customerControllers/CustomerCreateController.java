package controller.customerControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CustomerCreateController {

    @FXML
    Label titleLabel;

    @FXML
    TextField companyNameField, companyOrgNrField;

    @FXML
    Button saveButton, cancelButton, editShippingAddress, editBillingAddress, editContacts;
}
