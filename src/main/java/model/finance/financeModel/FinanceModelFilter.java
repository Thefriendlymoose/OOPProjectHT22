package model.finance.financeModel;

import model.WMS;
import model.site.Sites;
import model.user.Permission;
import model.user.User;

import java.util.function.Predicate;

/**
 * Responsibility: Used to filter SiteFinanceModels such that only an authorized user gets them
 * Uses: Sites, WMS
 * Used by: FinanceModel
 *
 * @author Simon Porsgaard / doktorjevsky
 * */
public class FinanceModelFilter implements Predicate<SiteFinanceModel> {


    private Sites sites;
    private WMS wms;

    public FinanceModelFilter(Sites sites, WMS wms){
        this.sites = sites;
        this.wms = wms;
    }

    @Override
    public boolean test(SiteFinanceModel siteFinanceModel) {
        return isAuthorized() && userIsEmployedAtSite(siteFinanceModel.getId());
    }

    private boolean isAuthorized(){
        return wms.getSession().hasAccess(Permission.FINANCE) || wms.isAdminSession();
    }

    private boolean userIsEmployedAtSite(long siteID){
        User user = wms.getSession().getUser();
        return wms.isAdminSession() || sites.getById(siteID).checkEmployeeInSite(user);
    }
}
