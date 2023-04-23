package controller.financecontrollers;

import model.WMS;
import model.finance.financeModel.SiteFinanceModel;

public class SiteFinanceCardController {

    private WMS wms;
    private SiteFinanceModel siteFinanceModel;

    public SiteFinanceCardController(WMS wms, SiteFinanceModel siteFinanceModel){
        this.wms = wms;
        this.siteFinanceModel = siteFinanceModel;
    }

}
