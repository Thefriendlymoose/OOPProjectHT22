package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class SignInController {

    @FXML
    Button btnSignIn;

    public void handleBtnSignIn() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../fxml/mainMenu.fxml")));
        Stage window = (Stage) btnSignIn.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
