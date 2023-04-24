package controller.financecontrollers;

import com.sun.javafx.collections.ObservableListWrapper;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;
import model.observer.Observer;
import model.site.Site;
import model.user.Permission;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FinanceMainController implements Observer {

    @FXML
    private VBox cardBox;
    @FXML
    private Button newBookButton;
    @FXML
    private Button backButton;
    @FXML
    private ChoiceBox<Long> choiceBox;

    private WMS wms;

    public FinanceMainController(WMS wms) {
        this.wms = wms;
        wms.registerObserver(this);

    }

    public void initialize(){
        Platform.runLater(() -> {
            choiceBox.setDisable(true);
            choiceBox.setVisible(false);
            update();
        });
    }


    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void addBookHandler(ActionEvent actionEvent) throws Exception {
        loadChoiceBox();
        choiceBox.setVisible(true);
        choiceBox.setDisable(false);
        update();
        setButtonsToChoiceMenu();
    }

    public void confirmChoiceHandler(ActionEvent e) {
        Long choice = choiceBox.getValue();
        try {
            wms.addNewSiteFinanceModel(choice);
        } catch (Exception ignore) {}
        setButtonsToMainSetting();
    }

    public void cancelChoiceHandler(ActionEvent e){
        setButtonsToMainSetting();
    }

    private void setButtonsToChoiceMenu(){
        backButton.setOnAction(this::cancelChoiceHandler);
        backButton.setText("Cancel");
        newBookButton.setOnAction(this::confirmChoiceHandler);
        newBookButton.setText("Confirm");
    }

    private void setButtonsToMainSetting(){
        backButton.setOnAction(actionEvent -> {
            try {
                backButtonHandler(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        backButton.setText("Back");
        newBookButton.setOnAction(actionEvent -> {
            try {
                addBookHandler(actionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        newBookButton.setText("Add new Book");
        choiceBox.setDisable(true);
        choiceBox.setVisible(false);

    }

    private void paintCardBox() {
        try {
            cardBox.getChildren().clear();
            // existing financeModels for the user
            Map<Long, SiteFinanceModel> financeModels = wms.getFinanceModels();
            financeModels.values().forEach(fm -> {
                ParentDependencyInjection.addInjectionMethod(SiteFinanceCardController.class, p -> new SiteFinanceCardController(wms, fm));
                Parent pane = null;
                try {
                    pane = ParentDependencyInjection.load("fxml/financeViews/siteFinanceCard.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                cardBox.getChildren().add(pane);
                cardBox.setVisible(true);
            });
        } catch (Exception ignore) {}
    }

    /*
    * Loads the choiceBox after being triggered by the "addNewBook" button.
    * The choice box contains the names of the sites that has not yet had
    * their financial book created. If the session is an admin session, then
    * this box will contain all non-instantiated sites; in any other case, the
    * box will contain all non-instantiated sites that the financial manager is
    * employed at.
    * */
    private void loadChoiceBox() throws Exception {
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

    @Override
    public void update() {
        if (!choiceBox.isShowing()){
            try {
                loadChoiceBox();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        paintCardBox();

    }
}
