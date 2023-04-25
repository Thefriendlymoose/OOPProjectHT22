package controller.financecontrollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.WMS;

public class BorderPaneController {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane centerPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private VBox buttonBox;

    private Stage stage;

    private Scene scene;


    private WMS wms;

    public BorderPaneController(WMS wms){
        this.wms = wms;
    }

    public void initialize(){
        Platform.runLater(() -> {
            stage = (Stage) borderPane.getScene().getWindow();
            scene = stage.getScene();
            buttonBox.getStyleClass().add("sub-menu-box");
           // scene.getStylesheets().add("fxml/stylesheet.css");
        });

    }

    public VBox getButtonBox(){
        return buttonBox;
    }

    public BorderPane getBorderPane(){
        return borderPane;
    }

    public WMS getWms(){
        return wms;
    }

    public AnchorPane getCenter(){
        return centerPane;
    }

    public Stage getStage(){
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

}
