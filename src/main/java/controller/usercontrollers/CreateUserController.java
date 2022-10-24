package controller.usercontrollers;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.user.Role;
import model.user.User;
import model.user.Users;

import java.io.IOException;
import java.util.Optional;

public class CreateUserController {

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ComboBox <Role> roleBox;

    @FXML
    private ComboBox<Boolean> statusBox;


    private Users users;
    public void setUsers(Users users) {
        this.users = users;
    }

    public void initialize(){
        Platform.runLater(() -> {

            statusBox.getItems().setAll(true, false);
            roleBox.getItems().addAll(Role.getManager(), Role.getSalesPerson(), Role.getAdmin());
            roleBox.valueProperty().addListener(new ChangeListener<Role>() {
                @Override
                public void changed(ObservableValue<? extends Role> observableValue, Role role, Role t1) {
                    if (!(t1 == null)) {
                        descriptionTextArea.setText(roleBox.getValue().getDescription());
                    }

                }
            });

            userIDTextField.setText(Long.toString(users.getNextUserID()));
        }
        );}




    public void onSave(ActionEvent e) throws IOException {
        User newUser = new User(users.getNextUserID(), userNameField.getText(), passwordField.getText(),firstNameField.getText() + " " + lastNameField.getText()
        , statusBox.getValue(), roleBox.getValue());
        users.addUser(newUser);
        users.updateUser();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

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










}
