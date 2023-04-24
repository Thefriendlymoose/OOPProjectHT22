package controller.financecontrollers;

import controller.buttonEventHandlers.OpenFinanceMainController;
import javafx.scene.control.Alert;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;

import java.awt.event.ActionEvent;

public class SiteFinanceController {

    private WMS wms;
    private SiteFinanceModel siteFinanceModel;
    private FinanceMainController mainController;

    public SiteFinanceController(WMS wms, long siteID, FinanceMainController financeMainController) {
        this.wms = wms;
        this.mainController = financeMainController;
        try {
            siteFinanceModel = wms.getSiteFinanceModel(siteID);
        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    public void initialize(){
        mainController.getBackButton().setOnAction(new OpenFinanceMainController());
        mainController.getNewBookButton().setText("New transaction");
        mainController.getNewBookButton().setOnAction(ae -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("CPU GONNA EKSPLOED");
            a.showAndWait();
        });
    }
}
