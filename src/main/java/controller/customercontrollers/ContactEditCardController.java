package controller.customercontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import controller.customercontrollers.command.ICommand;
import controller.customercontrollers.command.customercommands.SetEditable;
import model.customer.CustomerContact;
import model.customer.CustomerEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsibility: controls the view for deleting and editing already made contacts
 * Used by: ContactEditorController
 * Uses: CustomerEditor, ICommand, CustomerContact
 * @author Simon Porsgaard / doktorjevksy
 */

public class ContactEditCardController {

    public TextField name;
    public TextField number;
    public TextField email;
    public Button editSaveButton;
    public Button cancelButton;


    private CustomerEditor editor;
    private ICommand command;
    private CustomerContact contact;

    public ContactEditCardController(){
        command = new SetEditable(this);
    }

    public void initialize(){
        Platform.runLater(() -> {
            returnToSavedState();
        });
    }

    public void setEditor(CustomerEditor editor){
        this.editor = editor;
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

    public void deleteContactHandler(ActionEvent actionEvent) {
        editor.removeContact(contact);
    }
}
