package controller.userControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WMS;

import model.user.Permission;
import model.user.User;
import model.user.Users;
import persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class userDetailsController {

    @FXML
    private Button editButton,cancelButton;

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




}
