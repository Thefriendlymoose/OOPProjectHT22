package controller;

import controller.dpi.ParentDependencyInjection;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Authentication.AuthenticationStatus;
import model.Authentication.UserAuthentication;
import model.WMS;
import model.user.User;
import persistence.IPersistence;
import persistence.UserDAO;

public class SignInController {

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private Label errorLabel;

    private IPersistence<User> users = UserDAO.getInstance();
    private WMS wms;

    public SignInController(WMS wms) {
        this.wms = wms;
    }


    public void handleBtnSignIn() throws Exception {
        if (userNameField.getText().isEmpty() || passWordField.getText().equals("")){
            errorLabel.setText("Username or password field empty");
            errorLabel.setTextFill(Color.RED);
        } else {
            UserAuthentication uAuth = new UserAuthentication();
            AuthenticationStatus status = uAuth.logIn(userNameField.getText(), passWordField.getText(), users.getAll());

            if (status == AuthenticationStatus.SUCCESS){
                wms.setSession(uAuth.getSession());

                Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

                Stage window = (Stage) btnSignIn.getScene().getWindow();
                window.setScene(new Scene(root));
                errorLabel.setText("Logging in...");
                errorLabel.setTextFill(Color.GREEN);
            } else {
                errorLabel.setText("Username or password incorrect");
                errorLabel.setTextFill(Color.RED);
            }
        }
    }


}
