package controller;

import database.DataBaseAdapter;
import database.DataBaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class SignInController {
    DataBaseAdapter dba = new DataBaseAdapter(DataBaseConnection.getInstance());
    @FXML
    Button btnSignIn;

    @FXML
    TextField userNameField;

    @FXML
    PasswordField passWordField;

    @FXML
    Label errorLabel;

    public void handleBtnSignIn() throws Exception {
        if (dba.signIn(userNameField.getText(), passWordField.getText())){
            //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/mainMenu.fxml")));
            //Stage window = (Stage) btnSignIn.getScene().getWindow();
            //window.setScene(new Scene(root));
        } else {
            errorLabel.setText("No Such User");
        }



    }
}
