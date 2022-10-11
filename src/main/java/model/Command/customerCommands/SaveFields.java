package model.Command.customerCommands;

import controller.customerControllers.ContactEditCardController;
import javafx.scene.control.TextField;
import model.Command.ICommand;

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
        controller.setCommand(new SetEditable(controller));

    }
}
