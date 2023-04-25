package model.finance.financeModel;

import model.finance.accounts.AccountFactory;
import model.finance.accounts.FinancialAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Responsibility: Store all bookkeeping data of all the sites
 *
 * Uses: SteFinanceModel, AccountFactory, FinancialAccount, ArrayList, HashMap, List,
 *       Map, Predicate
 * Used by: WMS, Main
 *
 * @author Simon Porsgaard / doktorjevsky
 *
 * */
public class FinanceModel {

    private Map<Long, SiteFinanceModel> financeModels;

    public FinanceModel(Map<Long, SiteFinanceModel> financeModels){
        this.financeModels = financeModels;
    }

    public SiteFinanceModel getSiteFinanceModel(long id) throws Exception {
        if (!financeModels.containsKey(id)){
            throw new Exception("Finance model with site id " + id + " doesn't exist");
        }
        return financeModels.get(id);
    }

    /**
     * If the siteFinanceModel doesn't exist, the method will create a new SiteFinanceModel
     * with the BasicBASAccountPlan
     * */
    public void addNewSiteFinanceModel(long id) throws Exception {
        if (financeModels.containsKey(id)){
            throw new Exception("Finance model with site id " + id + " is already exists");
        }
        Map<Long, FinancialAccount> out = new HashMap<>();
        AccountFactory.getBasicSwedishBASAccountPlan().forEach(acc -> out.put(acc.getId(), acc));
        financeModels.put(id, new SiteFinanceModel(id, out, new ArrayList<>()));
    }

    /**
     * Precondition: -
     * Ensures: Returns a filtered by the predicate map of references to the SiteFinanceModels
     * */
    public Map<Long, SiteFinanceModel> getFilteredSiteFinanceModels(Predicate<SiteFinanceModel> predicate) {
        Map<Long, SiteFinanceModel> out = new HashMap<>();
        financeModels
                .values()
                .stream()
                .filter(predicate)
                .forEach(filtered -> out.put(filtered.getId(), filtered));
        return out;
    }

    /**
     * Get all SiteFinanceModels as a list
     * */
    public List<SiteFinanceModel> getAsList(){
        return financeModels.values().stream().toList();
    }


}
