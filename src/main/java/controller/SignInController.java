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
import model.authentication.AuthenticationStatus;
import model.authentication.UserAuthentication;
import model.WMS;
import model.user.Users;

/**
 * Controller for the view which shows the login screen
 *
 * @author David al Amiri
 */
public class SignInController {

    @FXML
    private Button btnSignIn;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private Label errorLabel;

    private Users users;
    private WMS wms;

    public SignInController(WMS wms) {
        this.wms = wms;
        this.users = wms.getUsers();
    }


    public void handleBtnSignIn() throws Exception {
        if (userNameField.getText().isEmpty() || passWordField.getText().equals("")){
            errorLabel.setText("Username or password field empty");
            errorLabel.setTextFill(Color.RED);
        } else {
            AuthenticationStatus status = wms.login(userNameField.getText(), passWordField.getText());

            if (status == AuthenticationStatus.SUCCESS){

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
