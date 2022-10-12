package controller.customerControllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;

public class ContactCreateController {

    private CustomerEditor editor;

    public TextField nameField;
    public TextField phoneField;
    public TextField emailField;

    public void setEditor(CustomerEditor editor){
        this.editor = editor;
    }

    public void addContactHandler(ActionEvent actionEvent) {
        CustomerContact newContact = new CustomerContact();
        newContact.setContactPerson(nameField.getText());
        newContact.setPhoneNumber(phoneField.getText());
        newContact.setEmail(emailField.getText());
        editor.addContact(newContact);
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }


    public void cancelHandler(ActionEvent actionEvent) {
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
