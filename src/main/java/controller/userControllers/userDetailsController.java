package controller.userControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.article.ArticleCategory;
import model.article.ArticleStatus;
import model.user.Permission;
import model.user.User;
import model.user.Users;
import persistence.UserDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class userDetailsController {

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
            userIDLabel.setText(userIDLabel.getText() + user.getUserId());


        });

    }


}
