package controller.userControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.user.Permission;
import model.user.User;
import model.user.Users;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class UserEditFormController {

    @FXML
    private Button saveButton,cancelButton;

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label userIDLabel, firstNameLabel,lastNameLabel,userNameLabel,passwordLabel,StatusLabel, roleLabel;

    @FXML
    private ComboBox<List<Permission>> roleBox;

    @FXML
    private ComboBox<Boolean> statusBox;


    private Users users;
    private User user;
    public void setUsers(Users users) {
        this.users = users;
    }
    public void setUser(User user) {
        this.user = user;
    }





    @FXML
    public void initialize() {


        Platform.runLater(() -> {
            userIDTextField.setText(String.valueOf(user.getUserId()));
            firstNameField.setText(user.getFirstOrLastName(user.getName(),0));
            lastNameField.setText(user.getFirstOrLastName(user.getName(),1));
            userNameField.setText(user.getUserName());
            passwordField.setText(user.getPassword());
            statusBox.setValue(user.isStatus());
            statusBox.getItems().addAll(user.getAllStatus());
            roleBox.setValue(user.getPermissions());
            // tmp
            roleBox.getItems().addAll(user.managerPermissions(),user.normalPermissions(),user.adminPermissions());

        });

    }
    public void onSave(ActionEvent e) throws IOException {
        // temp

        user.setName(firstNameField.getText() + " " + lastNameField.getText());
        user.setUserName((userNameField.getText()));
        user.setPassword(passwordField.getText());
        user.setStatus(statusBox.getValue());
        user.setPermissions(roleBox.getValue());

        users.updateUser();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }



}
