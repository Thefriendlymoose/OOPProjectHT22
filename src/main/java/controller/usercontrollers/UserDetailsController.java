package controller.usercontrollers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.user.Role;
import model.user.User;
import model.user.Users;

import java.io.IOException;

/**
 * Controller viewing a users detalis
 */
public class UserDetailsController {

    @FXML
    private Button editButton,cancelButton;

    @FXML
    private TextField userIDTextField, firstNameField, lastNameField, userNameField, passwordField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label userIDLabel, firstNameLabel,lastNameLabel,userNameLabel,passwordLabel,StatusLabel, roleLabel;

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
     * Initializes the details form
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
            descriptionTextArea.setText(roleBox.getValue().getDescription());
        });

    }

    /**
     * Hanled for when user presses the edit button
     * Takes the user to userEditController
     * @param e when edit button is pressed
     * @throws IOException
     */

    public void edit(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/userViews/userEditor.fxml"));
        Stage stage = loader.load();
        UserEditFormController cont = loader.getController();
        cont.setUser(user);
        cont.setUsers(users);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Stage) ((Node)e.getSource()).getScene().getWindow()).getOwner());
        stage.show();

        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();
    }

    /**
     * Handler for when the user presses the cancel button
     * Close the window on action
     * @param e when cancel button is pressed
     * @throws IOException
     */
    public void cancel(ActionEvent e) throws IOException{
        ((Stage) ((Node) e.getSource()).getScene().getWindow()).close();

    }




}
