package controller.financecontrollers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

}
