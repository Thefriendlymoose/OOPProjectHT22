package controller.financecontrollers;

import controller.dpi.StageDependencyInjection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.WMS;
import model.finance.financeModel.FinanceModel;
import model.finance.financeModel.SiteFinanceModel;

import java.io.IOException;

public class SiteFinanceCardController {

    @FXML
    private Button openButton;
    @FXML
    private Label nameLabel;
    private WMS wms;
    private SiteFinanceModel siteFinanceModel;
    private FinanceMainController financeMainController;

    public SiteFinanceCardController(WMS wms, SiteFinanceModel siteFinanceModel, FinanceMainController mainController){
        this.wms = wms;
        this.siteFinanceModel = siteFinanceModel;
        this.financeMainController = mainController;
    }

    public void initialize(){
        Platform.runLater(this::initCard);
    }

    private void initCard(){
        nameLabel.setText(wms.getSites().getById(siteFinanceModel.getId()).getSiteName());
    }

    public void openSiteFinanceHandler(ActionEvent actionEvent) throws IOException {
        StageDependencyInjection.addInjectionMethod(
                SiteFinanceController.class, params -> new SiteFinanceController(wms, siteFinanceModel.getId(), financeMainController)
        );

        Stage s = StageDependencyInjection.load("fxml/financeViews/siteFinanceView.fxml");
        Scene ss = s.getScene();
        Pane p = (Pane) ss.getRoot();
        financeMainController.getBorderPane().setCenter(p);


    }

}
