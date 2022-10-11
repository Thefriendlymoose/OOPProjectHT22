package controller.customerControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.command.ICommand;
import model.command.customerCommands.SetEditable;
import model.customer.CustomerContact;

import java.util.ArrayList;
import java.util.List;

public class ContactEditCardController {
    public TextField name;
    public TextField number;
    public TextField email;
    public Button editSaveButton;
    public Button cancelButton;
    private ICommand command;
    private CustomerContact contact;

    ContactEditCardController(){
        command = new SetEditable(this);
    }

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void setCustomerContact(CustomerContact contact){
        this.contact = contact;
    }

    public CustomerContact getCustomerContact(){
        return contact;
    }

    private void returnToSavedState(){
        name.setText(contact.getContactPerson());
        number.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
    }

    public List<TextField> getTextFields(){
        List<TextField> fields = new ArrayList<>();
        fields.add(name);
        fields.add(number);
        fields.add(email);
        return fields;
    }

    public void editContactHandler(ActionEvent actionEvent) {
        command.execute();
    }

    public void cancelHandler(ActionEvent actionEvent) {
        command = new SetEditable(this);
        editSaveButton.setText("Edit");
        cancelButton.setVisible(false);
        returnToSavedState();
    }
}
