package model.command.customerCommands;

import controller.customerControllers.ContactEditCardController;
import javafx.scene.control.TextField;
import model.command.ICommand;

import java.util.List;


public class SetEditable implements ICommand {

    private ContactEditCardController controller;

    public SetEditable(ContactEditCardController controller){
        this.controller = controller;
    }
    @Override
    public void execute() {
        List<TextField> fieldList = controller.getTextFields();
        for (TextField f : fieldList){
            f.setEditable(true);
        }
        controller.editSaveButton.setText("Save");
        controller.cancelButton.setVisible(true);
        controller.setCommand(new SaveFields(controller));
    }
}
