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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.User;
import persistence.IPersistence;
import persistence.UserDAO;

import java.util.Objects;

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

    public void handleBtnSignIn() throws Exception {
        if (userNameField.getText().isEmpty() || passWordField.getText().equals("")){
            errorLabel.setText("Username or password field empty");
            errorLabel.setTextFill(Color.RED);



        } else {

            boolean ok = false;

            for (User user : users.getAll()){
                if (userNameField.getText().equals(user.getUserName()) && passWordField.getText().equals(user.getPassword())){
                    ok = true;
                    break;
                }
            }

            if (ok){
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/mainMenu.fxml")));
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
