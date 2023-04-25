package controller.financecontrollers;

import controller.buttonEventHandlers.OpenFinanceMainController;
import javafx.scene.control.Alert;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;

public class SiteFinanceController {

    private WMS wms;
    private SiteFinanceModel siteFinanceModel;
    private BorderPaneController mainController;

    public SiteFinanceController(WMS wms, long siteID, BorderPaneController financeMainController) {
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


}
