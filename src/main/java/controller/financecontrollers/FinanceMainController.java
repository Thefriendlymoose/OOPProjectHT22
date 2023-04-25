package controller.financecontrollers;

import com.sun.javafx.collections.ObservableListWrapper;
import controller.buttonEventHandlers.OpenFinanceMainController;
import controller.buttonEventHandlers.OpenMainMenu;
import controller.dpi.ParentDependencyInjection;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;
import model.observer.Observer;
import model.site.Site;
import model.user.Permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinanceMainController extends BorderPaneController implements Observer {

    //private VBox buttonBox;
    private VBox cardBox;
    private Button newBookButton;
    private Button backButton;
    private Label leftLabel;

    private ChoiceBox<Long> choiceBox;

    private List<Node> nodes = new ArrayList<>();

    private WMS wms;


    public FinanceMainController(WMS wms) {
        super(wms);
        this.wms = wms;
        wms.registerObserver(this);

    }

    private void reloadLeftBox(){
        VBox leftBox = getButtonBox();
        leftBox.getChildren().clear();
        leftBox.getChildren().addAll(nodes);
    }


    private void initMenuButtons(){
        leftLabel.setText("Finance");
        newBookButton.setText("Add new Book");
        backButton.setText("Back");
        choiceBox.setDisable(true);
        choiceBox.setVisible(false);


        backButton.setOnAction(new OpenMainMenu());
        newBookButton.setOnAction(e -> initChoiceMenuButtons());
        reloadLeftBox();
    }

    private void initChoiceMenuButtons(){
        newBookButton.setText("Confirm");
        backButton.setText("Cancel");
        choiceBox.setVisible(true);
        choiceBox.setDisable(false);
        backButton.setOnAction(e -> initMenuButtons());
        reloadLeftBox();
    }


    public void initialize(){
        super.initialize();
        Platform.runLater(() -> {
            leftLabel = new Label("Finance");
            cardBox = new VBox();
            newBookButton = new Button("Add new Book");
            backButton = new Button("Back");
            choiceBox = new ChoiceBox<>();
            nodes.add(leftLabel);
            nodes.add(newBookButton);
            nodes.add(backButton);
            nodes.add(choiceBox);
            initMenuButtons();
            update();
        });
    }


    private void paintCardBox() {
        try {
            cardBox.getChildren().clear();
            // existing financeModels for the user
            Map<Long, SiteFinanceModel> financeModels = wms.getFinanceModels();
            financeModels.values().forEach(fm -> {
                ParentDependencyInjection.addInjectionMethod(SiteFinanceCardController.class, p -> new SiteFinanceCardController(wms, fm, this));
                Parent pane = null;
                try {
                    pane = ParentDependencyInjection.load("fxml/financeViews/siteFinanceCard.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cardBox.getChildren().add(pane);
            });
        } catch (Exception ignore) {}

        cardBox.setVisible(true);
        AnchorPane centerPane = getCenter();
        centerPane.getChildren().clear();
        centerPane.getChildren().add(cardBox);
        centerPane.setVisible(true);
    }


    @Override
    public void update() {
       paintCardBox();
    }
}
