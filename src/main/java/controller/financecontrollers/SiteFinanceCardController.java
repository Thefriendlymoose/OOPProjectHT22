package controller.financecontrollers;

import controller.dpi.ParentDependencyInjection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;

import java.io.IOException;

/**
 * Responsibility: provide functionality to open a selected SiteFinanceModel
 * Uses: Label, WMS, SiteFinanceModel, BorderPaneController, ParentDependencyInjection
 * Used by: FinanceMainController
 *
 * @author Simon Porsgaard / doktorjevsky
 * */

public class SiteFinanceCardController {

    @FXML
    private Button openButton;
    @FXML
    private Label nameLabel;
    private WMS wms;
    private SiteFinanceModel siteFinanceModel;
    private BorderPaneController financeMainController;

    public SiteFinanceCardController(WMS wms, SiteFinanceModel siteFinanceModel, BorderPaneController mainController){
        this.wms = wms;
        this.siteFinanceModel = siteFinanceModel;
        this.financeMainController = mainController;
    }

    public void initialize(){
        nameLabel.setText(wms.getSites().getById(siteFinanceModel.getId()).getSiteName());
    }


    public void openSiteFinanceHandler(ActionEvent actionEvent) throws IOException {
        ParentDependencyInjection.addInjectionMethod(SiteFinanceController.class,
                p -> new SiteFinanceController(wms, siteFinanceModel.getId(), financeMainController));


        ParentDependencyInjection.load("fxml/financeViews/siteFinanceView.fxml");



    }

}
