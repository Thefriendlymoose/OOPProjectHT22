package controller.customerControllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Command.ICommand;
import model.Command.customerCommands.SetEditable;
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

    ContactEditCardController(){
        command = new SetEditable(this);
    }

    public void setCommand(ICommand command){
        this.command = command;
    }

    public void setFields(CustomerContact contact){
        name.setText(contact.getContactPerson());
        number.setText(contact.getPhoneNumber());
        email.setText(contact.getEmail());
    }

    public CustomerContact getCustomerContact(){
        CustomerContact c = new CustomerContact();
        c.setEmail(email.getText());
        c.setContactPerson(name.getText());
        c.setPhoneNumber(number.getText());
        return c;
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
    }
}
