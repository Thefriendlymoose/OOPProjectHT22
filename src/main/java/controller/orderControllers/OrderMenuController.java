package controller.orderControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class OrderMenuController {
    @FXML
    private Button openButton, createButton, listButton, backButton;

    public void backBtnHandler() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/mainMenu.fxml")));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void openButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderOpenModal.fxml")));
        stage.setTitle("Open Order");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)e.getSource()).getScene().getWindow() );
        stage.show();

    }

    public void createButton(ActionEvent e) throws Exception{
        Stage stage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../fxml/orderViews/orderCreateModal.fxml")));
        stage.setTitle("Create Order: Choose Site");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) e.getSource()).getScene().getWindow());
        stage.show();
    }



    public void listButton() throws Exception{
        System.out.println("LIST");
    }
}
