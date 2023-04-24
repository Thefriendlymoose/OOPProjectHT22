package controller.buttonEventHandlers;

import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenMainMenu implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = ParentDependencyInjection.load("fxml/mainMenu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
