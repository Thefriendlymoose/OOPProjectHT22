package controller.financecontrollers;

import controller.dpi.StageDependencyInjection;
import controller.sitecontrollers.SiteDetailsController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.WMS;
import model.finance.financeModel.SiteFinanceModel;

public class SiteFinanceCardController {

    @FXML
    private Label nameLabel;
    private WMS wms;
    private SiteFinanceModel siteFinanceModel;

    public SiteFinanceCardController(WMS wms, SiteFinanceModel siteFinanceModel){
        this.wms = wms;
        this.siteFinanceModel = siteFinanceModel;
    }

    public void initialize(){
        Platform.runLater(this::initCard);
    }

    private void initCard(){
        nameLabel.setText(wms.getSites().getById(siteFinanceModel.getId()).getSiteName());
    }

    public void openSiteFinanceHandler(ActionEvent actionEvent) {

    }
}
