package controller.userControllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.user.Permission;
import model.user.User;
import model.user.Users;

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
    private ComboBox<Permission> roleBox;

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
            firstNameField.setText(user.getFirtName(user.getName(),0));
            lastNameField.setText(user.getFirtName(user.getName(),1));
            userNameField.setText(user.getUserName());
            passwordField.setText(user.getPassword());
            statusBox.setValue(user.isStatus());
            statusBox.getItems().setAll(user.isStatus());

        });

    }



}
