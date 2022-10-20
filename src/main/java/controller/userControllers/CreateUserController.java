package controller.userControllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.user.Permission;
import model.user.Role;
import model.user.User;
import model.user.Users;
import persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
    private ComboBox <List<Permission>> roleBox;

    @FXML
    private ComboBox<Boolean> statusBox;


    private Users users;
    public void setUsers(Users users) {
        this.users = users;
    }
    public void onSave(ActionEvent e) throws IOException {
        User newUser = new User(users.getNextUserID(), userNameField.getText(), passwordField.getText(),firstNameField.getText() + " " + lastNameField.getText()
        , statusBox.getValue(), roleBox.getValue());

        users.addUser(newUser);
        users.updateUser();

    }


    public void initialize(){
        statusBox.getItems().setAll(true, false);
        List<Permission> perms = new ArrayList<>(){
            {add(Permission.ARTICLE);add(Permission.SITE);}};
        roleBox.getItems().addAll(perms);
        userIDTextField.setText(Long.toString(UserDAO.getInstance().getNextId()));
    }






}
