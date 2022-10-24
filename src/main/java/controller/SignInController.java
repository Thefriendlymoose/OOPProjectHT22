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
import model.WMS;

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
    private WMS wms;

    public SignInController(WMS wms) {
        this.wms = wms;
    }


    public void handleBtnSignIn() throws Exception {
        if (userNameField.getText().isEmpty() || passWordField.getText().equals("")){
            setLabel("Username or password field empty", Color.RED);
        } else {
            AuthenticationStatus status = wms.login(userNameField.getText(), passWordField.getText());
            if (status == AuthenticationStatus.SUCCESS){
                Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

                Stage window = (Stage) btnSignIn.getScene().getWindow();
                window.setScene(new Scene(root));
                setLabel("Logging in...", Color.GREEN);
            } else {
                setLabel("Username or password incorrect", Color.RED);
            }
        }
    }

    private void setLabel(String text, Color color) {
        errorLabel.setText(text);
        errorLabel.setTextFill(color);
    }


}
