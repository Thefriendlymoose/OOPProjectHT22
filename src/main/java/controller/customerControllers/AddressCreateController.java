package controller.customerControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.customer.Address;

public class AddressCreateController {

    public TextField streetNameField;
    public TextField streetNumberField;
    public TextField postalCodeField;
    public TextField cityField;
    public TextField countryField;
    private Address address;

    public void initialize(){
        Platform.runLater(() -> {
            streetNameField.setText(address.getStreetName());
            streetNumberField.setText(address.getStreetNumber());
            postalCodeField.setText(address.getPostalCode());
            cityField.setText(address.getCityName());
            countryField.setText(address.getCountry());
        });
    }


    public void setAddress(Address address){ this.address = address; }

    public void saveBtnHandler(ActionEvent actionEvent) {
        address.setCountry(countryField.getText());
        address.setCityName(cityField.getText());
        address.setPostalCode(postalCodeField.getText());
        address.setStreetNumber(streetNumberField.getText());
        address.setStreetName(streetNameField.getText());

        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();

    }

    public void cancelBtnHandler(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
