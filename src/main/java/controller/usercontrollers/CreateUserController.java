package controller.usercontrollers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.user.Permission;
import model.user.User;
import model.user.Users;
import persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateUserController {

    @FXML
    private Button saveButton,cancelButton;

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label userIDLabel, firstNameLabel,lastNameLabel,userNameLabel,passwordLabel,StatusLabel, roleLabel;

    @FXML
    private ComboBox<Permission> roleBox;

    @FXML
    private ComboBox<Boolean> statusBox;


    private Users users;
    public void setUsers(Users users) {
        this.users = users;
    }
    public void onSave(ActionEvent e) throws IOException {
        // temp
        List<Permission> role = new ArrayList<Permission>();
        role.add(roleBox.getValue());

        User newUser = new User(users.getNextUserID(), userNameField.getText(), passwordField.getText(),firstNameField.getText() + " " + lastNameField.getText()
        , statusBox.getValue(),role);

        users.addUser(newUser);

    }


    public void initialize(){
        statusBox.getItems().setAll(true, false);
        //roleBox.getItems().setAll(users.getPermissions());
        userIDTextField.setText(Long.toString(UserDAO.getInstance().getNextId()));
    }






}
