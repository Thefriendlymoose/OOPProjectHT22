package model.command.customerCommands;

import controller.customerControllers.ContactEditCardController;
import javafx.scene.control.TextField;
import model.command.ICommand;
import model.customer.Customer;
import model.customer.CustomerContact;

import java.util.List;


public class SaveFields implements ICommand {

    private ContactEditCardController controller;

    public SaveFields(ContactEditCardController controller){
        this.controller = controller;

    }
    @Override
    public void execute() {
        List<TextField> fieldList = controller.getTextFields();
        for (TextField field : fieldList){
            field.setEditable(false);
        }
        controller.editSaveButton.setText("Edit");
        controller.cancelButton.setVisible(false);
        save();
        controller.setCommand(new SetEditable(controller));

    }

    private void save(){
        CustomerContact c = controller.getCustomerContact();
        c.setPhoneNumber(controller.number.getText());
        c.setContactPerson(controller.name.getText());
        c.setEmail(controller.email.getText());
        controller.setCustomerContact(c);
    }
}
