package controller.buttonEventHandlers;


import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Responsibility: Opens the financeMainView
 * Uses: EventHandler, ActionEvent, Node, Parent, Scene, Stage
 * Used by: SiteFinanceController, MainMenuController
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class OpenFinanceMainController implements EventHandler<ActionEvent> {


    @Override
    public void handle(ActionEvent actionEvent) {
        Parent root = null;
        try {
            root = ParentDependencyInjection.load("fxml/financeViews/financeMainView.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage s = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        s.setScene(new Scene(root));
    }
}
