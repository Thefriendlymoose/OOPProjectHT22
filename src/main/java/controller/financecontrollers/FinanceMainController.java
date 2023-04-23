package controller.financecontrollers;

import controller.dpi.ParentDependencyInjection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;
import model.observer.Observer;
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
    private WMS wms;

    public FinanceMainController(WMS wms){
        this.wms = wms;
        wms.registerObserver(this);
    }

    public void initialize(){
        Platform.runLater(this::update);
    }


    public void backButtonHandler(ActionEvent actionEvent) throws IOException {
        Parent root = ParentDependencyInjection.load("fxml/mainMenu.fxml");

        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void addBookHandler(ActionEvent actionEvent) {
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
            });
        } catch (Exception ignore) {}
    }


    @Override
    public void update() {
        if (wms.getSession().getUser().getRole().getPermissions().stream().noneMatch(p -> p.equals(Permission.ALL))){
            newBookButton.setDisable(true);
            newBookButton.setVisible(false);
        }
        paintCardBox();
    }


}
