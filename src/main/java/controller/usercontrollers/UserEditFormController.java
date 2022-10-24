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

/**
 * Controller for editing user details
 */
public class UserEditFormController {

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private ComboBox<Role> roleBox;

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


    /**
     * initializes the editing form
     */
    @FXML
    public void initialize() {


        Platform.runLater(() -> {

            userIDTextField.setText(String.valueOf(user.getUserId()));
            firstNameField.setText(user.getFirstName(user.getName()));
            lastNameField.setText(user.getLastName(user.getName()));
            userNameField.setText(user.getUserName());
            passwordField.setText(user.getPassword());
            statusBox.setValue(user.isStatus());
            statusBox.getItems().addAll(user.getAllStatus());
            roleBox.setValue(user.getRole());
            roleBox.getItems().addAll(
                    user.getRole().getAdmin(),
                    user.getRole().getManager(),
                    user.getRole().getSalesPerson()
                    );
            descriptionTextArea.setText(roleBox.getValue().getDescription());
            roleBox.valueProperty().addListener(new ChangeListener<Role>() {
                @Override
                public void changed(ObservableValue<? extends Role> observableValue, Role role, Role t1) {
                    if (!(t1 == null)){
                        descriptionTextArea.setText(roleBox.getValue().getDescription());
                    }

                }
            });

        });

    }

    /**
     * Handler for when the save button is pressed
     * Saves the changes made to user
     * @param e save button pressed
     * @throws IOException
     */
    public void onSave(ActionEvent e) throws IOException {
        // temp

        user.setName(firstNameField.getText() + " " + lastNameField.getText());
        user.setUserName((userNameField.getText()));
        user.setPassword(passwordField.getText());
        user.setStatus(statusBox.getValue());
        user.setRole(roleBox.getValue());

        users.updateUser();
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }

    /**
     * Handler for when the cancel button is pressed
     * Prompts the user first if they are sure
     * If yes closes the edit user controller window
     * @param e when cancle button is pressed
     */
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
