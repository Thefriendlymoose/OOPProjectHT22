package controller.financecontrollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.WMS;

import java.util.List;

/**
 * Responsibility: provide basic functionality for controllers that alter the same BorderPane
 * Uses: WMS, BorderPane, ScrollPane, VBox, AnchorPane
 * Used by: FinanceMainController, SiteFinanceCardController, SiteFinanceController
 *
 * @author Simon Porsgaard / doktorjevksy
 * */
public class BorderPaneController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane centerPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox buttonBox;

    private WMS wms;

    public BorderPaneController(WMS wms){
        this.wms = wms;
    }

    public void initialize(){
        Platform.runLater(() -> {
            buttonBox.getStyleClass().add("sub-menu-box");
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
        });

    }

    // TODO make method accept Node only

    /**
     * Loads a list of nodes to a VBox, located to the left in the BorderPane
     * @param nodes list of nodes
     * */
    public void loadLeft(List<Node> nodes){
        buttonBox.getChildren().clear();
        buttonBox.getChildren().addAll(nodes);
    }

    /**
     * Loads a node to the center of the BorderPane
     * @param n the node
     * */
    public void loadCenter(Node n){
        scrollPane.setContent(n);
    }


    // TODO refactor such that only loadLeft is used
    public VBox getButtonBox(){
        return buttonBox;
    }

    public WMS getWms(){
        return wms;
    }


}
