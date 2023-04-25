package controller.financecontrollers;

import controller.buttonEventHandlers.OpenMainMenu;
import controller.dpi.ParentDependencyInjection;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;
import model.observer.Observer;
import model.site.Site;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Responsibility: provide functionality to the main window of the finance model
 * Uses: VBox, Button, Label, ChoiceBox, List, WMS, EventHandler
 * Used by: Main
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class FinanceMainController extends BorderPaneController implements Observer {

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

    public void initialize(){
        super.initialize();
        Platform.runLater(() -> {
            leftLabel = StyledComponentFactory.getStyledLabel("Finance");
            cardBox = StyledComponentFactory.getStyledVBox();
            newBookButton = StyledComponentFactory.getStyledButton("Add new Book");
            backButton = StyledComponentFactory.getStyledButton("Back");
            choiceBox = new ChoiceBox<>();
            nodes.add(leftLabel);
            nodes.add(newBookButton);
            nodes.add(backButton);
            nodes.add(choiceBox);
            initMenuButtons();
            update();
        });
    }


    /*
    * Reloades the nodes in "nodes" to the VBOX that is
    * located to the left in the parent's BorderPane
    * */
    private void reloadLeftBox(){
        VBox leftBox = getButtonBox();
        leftBox.getChildren().clear();
        leftBox.getChildren().addAll(nodes);
    }


    /*
    * Initializes the menu buttons, i.e., the default mode
    * is set.
    * */
    private void initMenuButtons(){
        leftLabel.setText("Finance");
        newBookButton.setText("Add new Book");
        backButton.setText("Back");
        choiceBox.setDisable(true);
        choiceBox.setVisible(false);


        backButton.setOnAction(e -> {wms.unregisterObserver(this); new OpenMainMenu().handle(e);});
        newBookButton.setOnAction(e -> {initChoiceMenuButtons(); loadChoiceBox();});
        loadLeft(nodes);
    }

    /*
    * Sets the behavior of the buttons to "Confirm (choice)" and "Cancel"
    * after "Add new Book" has been pressed.
    * */
    private void initChoiceMenuButtons(){
        newBookButton.setText("Confirm");
        backButton.setText("Cancel");
        choiceBox.setVisible(true);
        choiceBox.setDisable(false);
        backButton.setOnAction(e -> initMenuButtons());
        newBookButton.setOnAction(e -> {
            Long choice = choiceBox.getValue();
            try {
                wms.addNewSiteFinanceModel(choice);
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(ex.getMessage());
            }
            initMenuButtons();
            update();
        });
        loadLeft(nodes);
    }

    /*
     * Loads the choiceBox after being triggered by the "addNewBook" button.
     * The choice box contains the names of the sites that has not yet had
     * their financial book created. If the session is an admin session, then
     * this box will contain all non-instantiated sites; in any other case, the
     * box will contain all non-instantiated sites that the financial manager is
     * employed at.
     * */
    private void loadChoiceBox() {
        Map<Long, SiteFinanceModel> financeModels = wms.getFinanceModels();
        List<Long> userSiteIds = wms.isAdminSession()
                ? wms.getSites()
                .getInList()
                .stream()
                .map(Site::getSiteId)
                .toList()
                : wms.getUserSiteIDs();

        choiceBox.getItems().clear();
        userSiteIds.stream().filter(id -> !financeModels.containsKey(id)).forEach(choiceBox.getItems()::add);
    }



    /*
    * Paints a clickable icon for all siteFinanceModels that are available
    * to the user to the screen
    * */
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
        loadCenter(cardBox);

    }


    @Override
    public void update() {
       paintCardBox();
       reloadLeftBox();
    }
}
