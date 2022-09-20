package controller.menuControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class OrderMenuController {
    @FXML
    Button openButton, createButton, listButton, backButton;

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void openButton() throws Exception{
        System.out.println("OPEN");

    }

    public void createButton() throws Exception{
        System.out.println("CREATE");
    }

    public void listButton() throws Exception{
        System.out.println("LIST");
    }
}
