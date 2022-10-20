package controller.customercontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.customer.Address;
import model.customer.CustomerEditor;

/**
 * Responsibility: controls the view for editing addresses
 * Used by: CustomerEditController
 * Uses: CustomerEditor
 * @author Simon Porsgaard / doktorjevksy
 */

public class AddressCreateController {

    public TextField streetNameField;
    public TextField streetNumberField;
    public TextField postalCodeField;
    public TextField cityField;
    public TextField countryField;
    private CustomerEditor editor;

    public void initialize(){
        Platform.runLater(() -> {
            Address address = editor.getAddress();
            streetNameField.setText(address.getStreetName());
            streetNumberField.setText(address.getStreetNumber());
            postalCodeField.setText(address.getPostalCode());
            cityField.setText(address.getCityName());
            countryField.setText(address.getCountry());
        });
    }


    public void setEditor(CustomerEditor editor){ this.editor = editor; }

    public void saveBtnHandler(ActionEvent actionEvent) {
        Address address = new Address();
        address.setCountry(countryField.getText());
        address.setCityName(cityField.getText());
        address.setPostalCode(postalCodeField.getText());
        address.setStreetNumber(streetNumberField.getText());
        address.setStreetName(streetNameField.getText());
        editor.setAddress(address);

        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();

    }

    public void cancelBtnHandler(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
