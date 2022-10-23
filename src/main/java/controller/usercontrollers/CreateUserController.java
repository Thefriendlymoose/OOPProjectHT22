package controller.usercontrollers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.user.Permission;
import model.user.Role;
import model.user.User;
import model.user.Users;
import persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private ComboBox <Role> roleBox;

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
    public void onCancel(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("All changes made will be cancelled");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL){
            System.out.println("Clicked Cancel");
        } else {
            ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
        }
    }


    public void initialize(){
        statusBox.getItems().setAll(true, false);
        roleBox.getItems().addAll(Role.getManager(),Role.getSalesPerson(),Role.getAdmin());

        userIDTextField.setText(Long.toString(UserDAO.getInstance().getNextId()));
    }






}
