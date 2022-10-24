package controller.usercontrollers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.user.Permission;
import model.user.User;
import model.user.Users;
import persistence.dataaccessobjects.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateUserController {

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

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
        List<Permission> perms = new ArrayList<>(){
            {add(Permission.ARTICLE);add(Permission.SITE);}};
        roleBox.getItems().addAll(perms);
        userIDTextField.setText(Long.toString(users.getNextUserID()));
    }






}
